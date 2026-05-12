package com.androsorthodontics.goldeneye64.command;

import com.androsorthodontics.goldeneye64.game.Agent;
import com.androsorthodontics.goldeneye64.game.VillainSpawner;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.border.WorldBorder;

import java.util.List;
import java.util.Random;

public final class BattleRoyaleCommand {

    private static volatile boolean villainsEnabled = false;

    public static boolean areVillainsEnabled() { return villainsEnabled; }

    private BattleRoyaleCommand() {}

    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(
            CommandManager.literal("br")
                .requires(src -> src.hasPermissionLevel(0))
                .executes(BattleRoyaleCommand::help)
                .then(CommandManager.literal("help").executes(BattleRoyaleCommand::help))
                .then(CommandManager.literal("start")
                    .requires(src -> src.hasPermissionLevel(2))
                    .executes(ctx -> startMatch(ctx.getSource(), 500, 600))
                    .then(CommandManager.argument("radius", IntegerArgumentType.integer(50, 5000))
                        .executes(ctx -> startMatch(ctx.getSource(),
                            IntegerArgumentType.getInteger(ctx, "radius"), 600))
                        .then(CommandManager.argument("shrinkSeconds", IntegerArgumentType.integer(30, 3600))
                            .executes(ctx -> startMatch(ctx.getSource(),
                                IntegerArgumentType.getInteger(ctx, "radius"),
                                IntegerArgumentType.getInteger(ctx, "shrinkSeconds"))))))
                .then(CommandManager.literal("stop")
                    .requires(src -> src.hasPermissionLevel(2))
                    .executes(ctx -> stopMatch(ctx.getSource())))
                .then(CommandManager.literal("kit")
                    .then(CommandManager.argument("agent", StringArgumentType.greedyString())
                        .executes(ctx -> giveKit(ctx.getSource(),
                            StringArgumentType.getString(ctx, "agent")))))
                .then(CommandManager.literal("agent")
                    .then(CommandManager.argument("agent", StringArgumentType.greedyString())
                        .executes(ctx -> setAgent(ctx.getSource(),
                            StringArgumentType.getString(ctx, "agent")))))
                .then(CommandManager.literal("agents").executes(BattleRoyaleCommand::listAgents))
                .then(CommandManager.literal("spawn")
                    .requires(src -> src.hasPermissionLevel(2))
                    .then(CommandManager.argument("villain", StringArgumentType.word())
                        .executes(ctx -> spawnVillain(ctx.getSource(),
                            StringArgumentType.getString(ctx, "villain")))))
                .then(CommandManager.literal("villains")
                    .executes(BattleRoyaleCommand::villainsStatus)
                    .then(CommandManager.literal("on")
                        .requires(src -> src.hasPermissionLevel(2))
                        .executes(ctx -> setVillains(ctx.getSource(), true)))
                    .then(CommandManager.literal("off")
                        .requires(src -> src.hasPermissionLevel(2))
                        .executes(ctx -> setVillains(ctx.getSource(), false)))
                    .then(CommandManager.literal("list")
                        .executes(BattleRoyaleCommand::listVillains)))
        );
    }

    private static int setVillains(ServerCommandSource src, boolean on) {
        villainsEnabled = on;
        broadcast(src.getServer(), Text.literal("§6[GoldenEye] §rVillain NPCs are now §"
            + (on ? "a§lON" : "c§lOFF") + "§r."));
        return 1;
    }

    private static int villainsStatus(com.mojang.brigadier.context.CommandContext<ServerCommandSource> ctx) {
        send(ctx.getSource(), "Villain NPCs: " + (villainsEnabled ? "§a§lON" : "§c§lOFF"));
        send(ctx.getSource(), "§7/br villains on  — enable, /br villains off — disable, /br villains list — show roster");
        return 1;
    }

    private static int help(com.mojang.brigadier.context.CommandContext<ServerCommandSource> ctx) {
        return help(ctx.getSource());
    }

    private static int help(ServerCommandSource src) {
        send(src, "§6==== GoldenEye 64 Battle Royale ====");
        send(src, "§e/br start [radius] [shrinkSec] §7— start a match (op only)");
        send(src, "§e/br stop §7— end the match (op only)");
        send(src, "§e/br kit <agent> §7— get an agent's loadout");
        send(src, "§e/br agent <agent> §7— set your codename");
        send(src, "§e/br agents §7— list all agents");
        send(src, "§e/br spawn <villain> §7— spawn an NPC villain (op only)");
        send(src, "§e/br villains on|off §7— enable/disable villain NPCs (default: §c§lOFF§7)");
        send(src, "§e/br villains list §7— show villain roster");
        return 1;
    }

    private static int startMatch(ServerCommandSource src, int radius, int shrinkSeconds) {
        MinecraftServer server = src.getServer();
        ServerWorld world = src.getWorld();
        Vec3d center = src.getPosition();

        WorldBorder border = world.getWorldBorder();
        border.setCenter(center.x, center.z);
        border.setSize(radius * 2.0);
        border.interpolateSize(radius * 2.0, 50.0, shrinkSeconds * 1000L);
        border.setDamagePerBlock(0.5);
        border.setSafeZone(0.0);
        border.setWarningBlocks(10);

        List<Agent> roster = Agent.roster();
        Random rng = new Random();
        for (ServerPlayerEntity p : server.getPlayerManager().getPlayerList()) {
            Agent a = roster.get(rng.nextInt(roster.size()));
            a.giveKit(p);
            p.setHealth(p.getMaxHealth());
            p.getHungerManager().setFoodLevel(20);
        }

        broadcast(server, Text.literal("§6§l[GoldenEye] §rMatch started! Border shrinks over §e"
            + shrinkSeconds + "s§r, radius §e" + radius + "§r."));

        if (villainsEnabled) {
            VillainSpawner.Villain[] all = VillainSpawner.Villain.values();
            int count = Math.min(all.length, 6);
            for (int i = 0; i < count; i++) {
                double angle = rng.nextDouble() * Math.PI * 2;
                double dist = radius * (0.4 + rng.nextDouble() * 0.5);
                double sx = center.x + Math.cos(angle) * dist;
                double sz = center.z + Math.sin(angle) * dist;
                double sy = world.getTopY(net.minecraft.world.Heightmap.Type.WORLD_SURFACE,
                    (int) sx, (int) sz);
                VillainSpawner.spawn(world, new Vec3d(sx, sy, sz), all[rng.nextInt(all.length)]);
            }
            broadcast(server, Text.literal("§6[GoldenEye] §c§lVILLAINS ARE LOOSE.§r §76 enemies scattered around the border."));
        }
        return 1;
    }

    private static int stopMatch(ServerCommandSource src) {
        ServerWorld world = src.getWorld();
        WorldBorder border = world.getWorldBorder();
        border.setSize(border.getMaxRadius());
        border.setDamagePerBlock(0.0);
        broadcast(src.getServer(), Text.literal("§6§l[GoldenEye] §rMatch ended. Border reset."));
        return 1;
    }

    private static int giveKit(ServerCommandSource src, String name) {
        ServerPlayerEntity player;
        try { player = src.getPlayerOrThrow(); } catch (Exception e) {
            send(src, "§cMust be run by a player."); return 0;
        }
        Agent agent = Agent.byCodename(name);
        if (agent == null) {
            send(src, "§cNo agent found: " + name);
            return 0;
        }
        agent.giveKit(player);
        return 1;
    }

    private static int setAgent(ServerCommandSource src, String name) {
        ServerPlayerEntity player;
        try { player = src.getPlayerOrThrow(); } catch (Exception e) {
            send(src, "§cMust be run by a player."); return 0;
        }
        Agent agent = Agent.byCodename(name);
        if (agent == null) {
            send(src, "§cNo agent found: " + name);
            return 0;
        }
        player.setCustomName(Text.literal(agent.codename).formatted(agent.nameColor));
        player.setCustomNameVisible(true);
        send(src, "§aYou are now: " + agent.codename);
        return 1;
    }

    private static int listAgents(com.mojang.brigadier.context.CommandContext<ServerCommandSource> ctx) {
        ServerCommandSource src = ctx.getSource();
        send(src, "§6Agents:");
        for (Agent a : Agent.roster()) {
            src.sendFeedback(() -> a.displayLine(), false);
        }
        return 1;
    }

    private static int spawnVillain(ServerCommandSource src, String name) {
        ServerPlayerEntity player;
        try { player = src.getPlayerOrThrow(); } catch (Exception e) {
            send(src, "§cMust be run by a player."); return 0;
        }
        VillainSpawner.Villain villain = VillainSpawner.Villain.byName(name);
        if (villain == null) {
            send(src, "§cNo villain found: " + name + ". Try /br villains list");
            return 0;
        }
        Vec3d look = player.getRotationVec(1.0f);
        Vec3d spawnPos = player.getPos().add(look.multiply(3.0));
        VillainSpawner.spawn((ServerWorld) player.getWorld(), spawnPos, villain);
        send(src, "§aSpawned " + villain.displayName);
        return 1;
    }

    private static int listVillains(com.mojang.brigadier.context.CommandContext<ServerCommandSource> ctx) {
        ServerCommandSource src = ctx.getSource();
        send(src, "§6Villains:");
        for (VillainSpawner.Villain v : VillainSpawner.Villain.values()) {
            send(src, "  §f" + v.name().toLowerCase() + " §7→ " + v.displayName);
        }
        return 1;
    }

    private static void send(ServerCommandSource src, String msg) {
        src.sendFeedback(() -> Text.literal(msg), false);
    }

    private static void broadcast(MinecraftServer server, Text msg) {
        server.getPlayerManager().broadcast(msg, false);
    }
}
