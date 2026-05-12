package com.androsorthodontics.goldeneye64.item;

import com.androsorthodontics.goldeneye64.GoldenEyeMod;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public final class ModItems {
    private ModItems() {}

    // GoldenEye Pistols
    public static final Item PP7 = register("pp7", new GunItem(new Item.Settings().maxCount(1), GunStats.PP7));
    public static final Item PP7_SILENCED = register("pp7_silenced", new GunItem(new Item.Settings().maxCount(1), GunStats.PP7_SILENCED));
    public static final Item DD44 = register("dd44", new GunItem(new Item.Settings().maxCount(1), GunStats.DD44));
    public static final Item COUGAR_MAGNUM = register("cougar_magnum", new GunItem(new Item.Settings().maxCount(1), GunStats.COUGAR_MAGNUM));

    // GoldenEye SMGs
    public static final Item KLOBB = register("klobb", new GunItem(new Item.Settings().maxCount(1), GunStats.KLOBB));
    public static final Item ZMG = register("zmg", new GunItem(new Item.Settings().maxCount(1), GunStats.ZMG));
    public static final Item D5K = register("d5k", new GunItem(new Item.Settings().maxCount(1), GunStats.D5K));
    public static final Item RCP90 = register("rcp90", new GunItem(new Item.Settings().maxCount(1), GunStats.RCP90));

    // GoldenEye Rifles
    public static final Item KF7_SOVIET = register("kf7_soviet", new GunItem(new Item.Settings().maxCount(1), GunStats.KF7_SOVIET));
    public static final Item AR33 = register("ar33", new GunItem(new Item.Settings().maxCount(1), GunStats.AR33));
    public static final Item SNIPER_RIFLE = register("sniper_rifle", new GunItem(new Item.Settings().maxCount(1), GunStats.SNIPER_RIFLE));

    // GoldenEye Shotguns
    public static final Item SHOTGUN = register("shotgun", new GunItem(new Item.Settings().maxCount(1), GunStats.SHOTGUN));
    public static final Item AUTO_SHOTGUN = register("auto_shotgun", new GunItem(new Item.Settings().maxCount(1), GunStats.AUTO_SHOTGUN));

    public static final Item GOLDEN_GUN = register("golden_gun", new GunItem(new Item.Settings().maxCount(1).rarity(net.minecraft.util.Rarity.EPIC), GunStats.GOLDEN_GUN));

    // Real-life Pistols
    public static final Item GLOCK17 = register("glock_17", new GunItem(new Item.Settings().maxCount(1), GunStats.GLOCK17));
    public static final Item M1911 = register("m1911", new GunItem(new Item.Settings().maxCount(1), GunStats.M1911));
    public static final Item DESERT_EAGLE = register("desert_eagle", new GunItem(new Item.Settings().maxCount(1).rarity(net.minecraft.util.Rarity.RARE), GunStats.DESERT_EAGLE));
    public static final Item BERETTA_M9 = register("beretta_m9", new GunItem(new Item.Settings().maxCount(1), GunStats.BERETTA_M9));

    // Real-life SMGs
    public static final Item MP5 = register("mp5", new GunItem(new Item.Settings().maxCount(1), GunStats.MP5));
    public static final Item UZI = register("uzi", new GunItem(new Item.Settings().maxCount(1), GunStats.UZI));
    public static final Item MAC10 = register("mac10", new GunItem(new Item.Settings().maxCount(1), GunStats.MAC10));
    public static final Item THOMPSON = register("thompson", new GunItem(new Item.Settings().maxCount(1), GunStats.THOMPSON));

    // Real-life Rifles
    public static final Item AK47 = register("ak47", new GunItem(new Item.Settings().maxCount(1), GunStats.AK47));
    public static final Item M4A1 = register("m4a1", new GunItem(new Item.Settings().maxCount(1), GunStats.M4A1));
    public static final Item AUG = register("aug", new GunItem(new Item.Settings().maxCount(1), GunStats.AUG));
    public static final Item SCAR_H = register("scar_h", new GunItem(new Item.Settings().maxCount(1).rarity(net.minecraft.util.Rarity.RARE), GunStats.SCAR_H));

    // Real-life Snipers
    public static final Item AWP = register("awp", new GunItem(new Item.Settings().maxCount(1).rarity(net.minecraft.util.Rarity.RARE), GunStats.AWP));
    public static final Item BARRETT_50CAL = register("barrett_50cal", new GunItem(new Item.Settings().maxCount(1).rarity(net.minecraft.util.Rarity.EPIC), GunStats.BARRETT_50CAL));

    // Real-life Shotguns
    public static final Item BENELLI_M4 = register("benelli_m4", new GunItem(new Item.Settings().maxCount(1), GunStats.BENELLI_M4));
    public static final Item AA12 = register("aa12", new GunItem(new Item.Settings().maxCount(1).rarity(net.minecraft.util.Rarity.EPIC), GunStats.AA12));

    public static final Item M249 = register("m249", new GunItem(new Item.Settings().maxCount(1).rarity(net.minecraft.util.Rarity.RARE), GunStats.M249));

    // Ammo
    public static final Item AMMO_9MM = register("ammo_9mm", new AmmoItem(new Item.Settings().maxCount(64)));
    public static final Item AMMO_RIFLE = register("ammo_rifle", new AmmoItem(new Item.Settings().maxCount(64)));
    public static final Item AMMO_MAGNUM = register("ammo_magnum", new AmmoItem(new Item.Settings().maxCount(64)));
    public static final Item AMMO_SHELLS = register("ammo_shells", new AmmoItem(new Item.Settings().maxCount(64)));
    public static final Item AMMO_SNIPER = register("ammo_sniper", new AmmoItem(new Item.Settings().maxCount(64)));
    public static final Item AMMO_GOLDEN = register("ammo_golden", new AmmoItem(new Item.Settings().maxCount(16)));

    // Gadgets
    public static final Item PROXIMITY_MINE = register("proximity_mine", new ProximityMineItem(new Item.Settings().maxCount(8)));
    public static final Item REMOTE_MINE = register("remote_mine", new RemoteMineItem(new Item.Settings().maxCount(8)));
    public static final Item TIMED_MINE = register("timed_mine", new TimedMineItem(new Item.Settings().maxCount(8)));
    public static final Item AGENT_SELECTOR = register("agent_selector", new AgentSelectorItem(new Item.Settings().maxCount(1)));

    public static final RegistryKey<ItemGroup> GROUP_KEY = RegistryKey.of(
        RegistryKeys.ITEM_GROUP,
        new Identifier(GoldenEyeMod.MOD_ID, "main")
    );

    public static final ItemGroup GROUP = FabricItemGroup.builder()
        .icon(() -> new ItemStack(GOLDEN_GUN))
        .displayName(Text.literal("GoldenEye 64"))
        .build();

    public static void register() {
        Registry.register(Registries.ITEM_GROUP, GROUP_KEY, GROUP);

        ItemGroupEvents.modifyEntriesEvent(GROUP_KEY).register(entries -> {
            entries.add(PP7); entries.add(PP7_SILENCED); entries.add(DD44); entries.add(COUGAR_MAGNUM);
            entries.add(KLOBB); entries.add(ZMG); entries.add(D5K); entries.add(RCP90);
            entries.add(KF7_SOVIET); entries.add(AR33); entries.add(SNIPER_RIFLE);
            entries.add(SHOTGUN); entries.add(AUTO_SHOTGUN);
            entries.add(GOLDEN_GUN);
            entries.add(GLOCK17); entries.add(BERETTA_M9); entries.add(M1911); entries.add(DESERT_EAGLE);
            entries.add(MAC10); entries.add(UZI); entries.add(MP5); entries.add(THOMPSON);
            entries.add(AK47); entries.add(M4A1); entries.add(AUG); entries.add(SCAR_H);
            entries.add(AWP); entries.add(BARRETT_50CAL);
            entries.add(BENELLI_M4); entries.add(AA12);
            entries.add(M249);
            entries.add(AMMO_9MM); entries.add(AMMO_RIFLE); entries.add(AMMO_MAGNUM);
            entries.add(AMMO_SHELLS); entries.add(AMMO_SNIPER); entries.add(AMMO_GOLDEN);
            entries.add(PROXIMITY_MINE); entries.add(REMOTE_MINE); entries.add(TIMED_MINE);
            entries.add(AGENT_SELECTOR);
        });
    }

    private static <T extends Item> T register(String name, T item) {
        return Registry.register(Registries.ITEM, new Identifier(GoldenEyeMod.MOD_ID, name), item);
    }
}
