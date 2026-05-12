package com.androsorthodontics.goldeneye64.game;

import com.androsorthodontics.goldeneye64.item.ModItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.Vec3d;

public final class VillainSpawner {

    public enum Villain {
        BORIS("Boris Grishenko", EntityType.ZOMBIE, 24f, () -> ModItems.KLOBB, Formatting.GREEN, null, null),
        OUROMOV("General Ouromov", EntityType.ZOMBIE_VILLAGER, 30f, () -> ModItems.AR33, Formatting.DARK_GREEN, () -> Items.IRON_HELMET, () -> Items.IRON_CHESTPLATE),
        JAWS("Jaws", EntityType.HUSK, 60f, () -> ModItems.SHOTGUN, Formatting.GRAY, () -> Items.IRON_HELMET, () -> Items.CHAINMAIL_CHESTPLATE),
        ODDJOB("Oddjob", EntityType.VINDICATOR, 30f, () -> ModItems.PP7_SILENCED, Formatting.DARK_GRAY, () -> Items.LEATHER_HELMET, () -> Items.LEATHER_CHESTPLATE),
        XENIA("Xenia Onatopp", EntityType.WITCH, 36f, () -> ModItems.RCP90, Formatting.LIGHT_PURPLE, null, null),
        TREVELYAN("006 Trevelyan", EntityType.PILLAGER, 50f, () -> ModItems.KF7_SOVIET, Formatting.DARK_RED, () -> Items.IRON_HELMET, () -> Items.LEATHER_CHESTPLATE),
        MAYDAY("May Day", EntityType.PILLAGER, 40f, () -> ModItems.AUTO_SHOTGUN, Formatting.RED, null, null),
        BARON("Baron Samedi", EntityType.ZOMBIE, 50f, () -> ModItems.COUGAR_MAGNUM, Formatting.DARK_PURPLE, () -> Items.LEATHER_HELMET, null),
        DR_NO("Dr. Julius No", EntityType.WITHER_SKELETON, 80f, () -> ModItems.GOLDEN_GUN, Formatting.GOLD, null, () -> Items.GOLDEN_CHESTPLATE);

        public final String displayName;
        public final EntityType<? extends HostileEntity> type;
        public final float health;
        public final java.util.function.Supplier<Item> mainHandItem;
        public final Formatting nameColor;
        public final java.util.function.Supplier<Item> helmet;
        public final java.util.function.Supplier<Item> chestplate;

        @SuppressWarnings("unchecked")
        Villain(String displayName, EntityType<?> type, float health,
                java.util.function.Supplier<Item> mainHandItem, Formatting nameColor,
                java.util.function.Supplier<Item> helmet,
                java.util.function.Supplier<Item> chestplate) {
            this.displayName = displayName;
            this.type = (EntityType<? extends HostileEntity>) type;
            this.health = health;
            this.mainHandItem = mainHandItem;
            this.nameColor = nameColor;
            this.helmet = helmet;
            this.chestplate = chestplate;
        }

        public static Villain byName(String input) {
            String n = input.toUpperCase().replace(" ", "_").replace(".", "").replace("__", "_");
            for (Villain v : values()) {
                if (v.name().equals(n)) return v;
                if (v.displayName.toLowerCase().contains(input.toLowerCase())) return v;
            }
            return null;
        }
    }

    private VillainSpawner() {}

    public static HostileEntity spawn(ServerWorld world, Vec3d pos, Villain villain) {
        HostileEntity mob = villain.type.create(world);
        if (mob == null) return null;

        mob.setPosition(pos.x, pos.y, pos.z);
        mob.setCustomName(Text.literal(villain.displayName).formatted(villain.nameColor));
        mob.setCustomNameVisible(true);
        mob.setPersistent();

        EntityAttributeInstance maxHealth = mob.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH);
        if (maxHealth != null) {
            maxHealth.setBaseValue(villain.health);
            mob.setHealth(villain.health);
        }

        if (villain.mainHandItem != null) {
            ItemStack weapon = new ItemStack(villain.mainHandItem.get());
            mob.equipStack(EquipmentSlot.MAINHAND, weapon);
            mob.setEquipmentDropChance(EquipmentSlot.MAINHAND, 1.0f);
        }

        if (villain.helmet != null) {
            mob.equipStack(EquipmentSlot.HEAD, new ItemStack(villain.helmet.get()));
            mob.setEquipmentDropChance(EquipmentSlot.HEAD, 0.5f);
        }
        if (villain.chestplate != null) {
            mob.equipStack(EquipmentSlot.CHEST, new ItemStack(villain.chestplate.get()));
            mob.setEquipmentDropChance(EquipmentSlot.CHEST, 0.5f);
        }

        mob.initialize(world, world.getLocalDifficulty(mob.getBlockPos()),
            SpawnReason.COMMAND, null, null);

        if (maxHealth != null) {
            maxHealth.setBaseValue(villain.health);
            mob.setHealth(villain.health);
        }
        mob.setCustomName(Text.literal(villain.displayName).formatted(villain.nameColor));
        mob.setCustomNameVisible(true);
        if (villain.mainHandItem != null) {
            mob.equipStack(EquipmentSlot.MAINHAND, new ItemStack(villain.mainHandItem.get()));
        }

        world.spawnEntity(mob);
        return mob;
    }
}
