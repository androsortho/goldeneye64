package com.androsorthodontics.goldeneye64.game;

import com.androsorthodontics.goldeneye64.item.ModItems;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.List;

public final class Agent {
    public final String codename;
    public final String realName;
    public final Formatting nameColor;
    private final Loadout loadout;

    public Agent(String codename, String realName, Formatting nameColor, Loadout loadout) {
        this.codename = codename;
        this.realName = realName;
        this.nameColor = nameColor;
        this.loadout = loadout;
    }

    public void giveKit(PlayerEntity player) {
        for (KitEntry e : loadout.entries) {
            ItemStack stack = new ItemStack(e.item, e.count);
            if (!player.getInventory().insertStack(stack)) {
                player.dropItem(stack, false);
            }
        }
        player.sendMessage(Text.literal("Loadout: " + codename).formatted(nameColor), false);
    }

    public Text displayLine() {
        return Text.literal("[" + codename + "] ").formatted(nameColor)
            .append(Text.literal(realName).formatted(Formatting.GRAY));
    }

    public static final class KitEntry {
        public final Item item;
        public final int count;
        public KitEntry(Item item, int count) { this.item = item; this.count = count; }
    }

    public static final class Loadout {
        public final List<KitEntry> entries;
        public Loadout(List<KitEntry> entries) { this.entries = entries; }
        public static Loadout of(KitEntry... entries) { return new Loadout(List.of(entries)); }
    }

    private static KitEntry kit(Item item, int count) { return new KitEntry(item, count); }

    public static List<Agent> roster() {
        return List.of(
            new Agent("007", "James Bond", Formatting.AQUA,
                Loadout.of(kit(ModItems.PP7_SILENCED, 1), kit(ModItems.AMMO_9MM, 32), kit(ModItems.PROXIMITY_MINE, 2))),
            new Agent("006", "Alec Trevelyan", Formatting.DARK_RED,
                Loadout.of(kit(ModItems.KF7_SOVIET, 1), kit(ModItems.AMMO_RIFLE, 60), kit(ModItems.REMOTE_MINE, 3))),
            new Agent("Natalya", "Natalya Simonova", Formatting.YELLOW,
                Loadout.of(kit(ModItems.ZMG, 1), kit(ModItems.AMMO_9MM, 80))),
            new Agent("Xenia", "Xenia Onatopp", Formatting.LIGHT_PURPLE,
                Loadout.of(kit(ModItems.RCP90, 1), kit(ModItems.AMMO_9MM, 100))),
            new Agent("Boris", "Boris Grishenko", Formatting.GREEN,
                Loadout.of(kit(ModItems.KLOBB, 1), kit(ModItems.AMMO_9MM, 64), kit(ModItems.TIMED_MINE, 4))),
            new Agent("Ouromov", "General Ouromov", Formatting.DARK_GREEN,
                Loadout.of(kit(ModItems.DD44, 1), kit(ModItems.AMMO_9MM, 32), kit(ModItems.AR33, 1), kit(ModItems.AMMO_RIFLE, 60))),
            new Agent("Jaws", "Jaws", Formatting.GRAY,
                Loadout.of(kit(ModItems.SHOTGUN, 1), kit(ModItems.AMMO_SHELLS, 32))),
            new Agent("Oddjob", "Oddjob", Formatting.DARK_GRAY,
                Loadout.of(kit(ModItems.PP7_SILENCED, 1), kit(ModItems.AMMO_9MM, 40))),
            new Agent("Mayday", "May Day", Formatting.RED,
                Loadout.of(kit(ModItems.AUTO_SHOTGUN, 1), kit(ModItems.AMMO_SHELLS, 32))),
            new Agent("Baron", "Baron Samedi", Formatting.DARK_PURPLE,
                Loadout.of(kit(ModItems.COUGAR_MAGNUM, 1), kit(ModItems.AMMO_MAGNUM, 32))),
            new Agent("Dr. No", "Julius No", Formatting.GOLD,
                Loadout.of(kit(ModItems.GOLDEN_GUN, 1), kit(ModItems.AMMO_GOLDEN, 6)))
        );
    }

    public static Agent byCodename(String name) {
        String n = name.toLowerCase();
        for (Agent a : roster()) {
            if (a.codename.toLowerCase().equals(n) || a.realName.toLowerCase().contains(n)) {
                return a;
            }
        }
        return null;
    }
}
