package com.androsorthodontics.goldeneye64.item;

import com.androsorthodontics.goldeneye64.GoldenEyeMod;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;

public final class GunStats {
    public final String displayName;
    public final float damage;
    public final int cooldownTicks;
    public final double maxRange;
    public final float spread;
    public final int pelletsPerShot;
    public final Identifier ammoId;
    public final boolean silenced;
    public final SoundEvent fireSound;
    public final float volume;
    public final float pitch;

    public GunStats(String displayName, float damage, int cooldownTicks, double maxRange,
                    float spread, int pelletsPerShot, Identifier ammoId, boolean silenced,
                    SoundEvent fireSound, float volume, float pitch) {
        this.displayName = displayName;
        this.damage = damage;
        this.cooldownTicks = cooldownTicks;
        this.maxRange = maxRange;
        this.spread = spread;
        this.pelletsPerShot = pelletsPerShot;
        this.ammoId = ammoId;
        this.silenced = silenced;
        this.fireSound = fireSound;
        this.volume = volume;
        this.pitch = pitch;
    }

    private static Identifier ammo(String name) {
        return new Identifier(GoldenEyeMod.MOD_ID, name);
    }

    // ---------- GoldenEye Pistols ----------
    public static final GunStats PP7 = new GunStats("PP7", 5.0f, 8, 60.0, 0.01f, 1, ammo("ammo_9mm"), false, SoundEvents.BLOCK_DISPENSER_LAUNCH, 1.0f, 1.4f);
    public static final GunStats PP7_SILENCED = new GunStats("PP7 (Silenced)", 5.0f, 8, 60.0, 0.01f, 1, ammo("ammo_9mm"), true, SoundEvents.BLOCK_WOOL_HIT, 0.4f, 1.6f);
    public static final GunStats DD44 = new GunStats("DD44 Dostovei", 6.0f, 7, 55.0, 0.02f, 1, ammo("ammo_9mm"), false, SoundEvents.BLOCK_DISPENSER_LAUNCH, 1.0f, 1.2f);
    public static final GunStats COUGAR_MAGNUM = new GunStats("Cougar Magnum", 12.0f, 18, 70.0, 0.005f, 1, ammo("ammo_magnum"), false, SoundEvents.ITEM_FIRECHARGE_USE, 1.4f, 0.8f);

    // ---------- GoldenEye SMGs ----------
    public static final GunStats KLOBB = new GunStats("Klobb", 2.0f, 2, 35.0, 0.10f, 1, ammo("ammo_9mm"), false, SoundEvents.BLOCK_DISPENSER_LAUNCH, 0.6f, 1.8f);
    public static final GunStats ZMG = new GunStats("ZMG (9mm)", 3.0f, 3, 40.0, 0.05f, 1, ammo("ammo_9mm"), false, SoundEvents.BLOCK_DISPENSER_LAUNCH, 0.8f, 1.6f);
    public static final GunStats D5K = new GunStats("D5K Deutsche", 3.5f, 3, 40.0, 0.04f, 1, ammo("ammo_9mm"), true, SoundEvents.BLOCK_WOOL_HIT, 0.3f, 1.7f);
    public static final GunStats RCP90 = new GunStats("RC-P90", 3.5f, 2, 50.0, 0.025f, 1, ammo("ammo_9mm"), false, SoundEvents.BLOCK_DISPENSER_LAUNCH, 0.8f, 1.7f);

    // ---------- GoldenEye Rifles ----------
    public static final GunStats KF7_SOVIET = new GunStats("KF7 Soviet", 5.5f, 4, 70.0, 0.03f, 1, ammo("ammo_rifle"), false, SoundEvents.BLOCK_DISPENSER_LAUNCH, 1.1f, 1.0f);
    public static final GunStats AR33 = new GunStats("AR33", 6.0f, 4, 75.0, 0.025f, 1, ammo("ammo_rifle"), false, SoundEvents.BLOCK_DISPENSER_LAUNCH, 1.1f, 0.95f);
    public static final GunStats SNIPER_RIFLE = new GunStats("Sniper Rifle", 30.0f, 30, 200.0, 0.0f, 1, ammo("ammo_sniper"), false, SoundEvents.ENTITY_GENERIC_EXPLODE, 0.6f, 1.5f);

    // ---------- GoldenEye Shotguns ----------
    public static final GunStats SHOTGUN = new GunStats("Shotgun", 3.0f, 18, 25.0, 0.18f, 6, ammo("ammo_shells"), false, SoundEvents.BLOCK_DISPENSER_LAUNCH, 1.3f, 0.7f);
    public static final GunStats AUTO_SHOTGUN = new GunStats("Automatic Shotgun", 2.5f, 6, 25.0, 0.18f, 5, ammo("ammo_shells"), false, SoundEvents.BLOCK_DISPENSER_LAUNCH, 1.2f, 0.8f);

    public static final GunStats GOLDEN_GUN = new GunStats("Golden Gun", 1000.0f, 25, 200.0, 0.0f, 1, ammo("ammo_golden"), false, SoundEvents.ITEM_TOTEM_USE, 1.5f, 1.3f);

    // ---------- Real-life Pistols ----------
    public static final GunStats GLOCK17 = new GunStats("Glock 17", 4.5f, 6, 50.0, 0.015f, 1, ammo("ammo_9mm"), false, SoundEvents.BLOCK_DISPENSER_LAUNCH, 1.0f, 1.5f);
    public static final GunStats M1911 = new GunStats("M1911", 6.5f, 10, 55.0, 0.01f, 1, ammo("ammo_magnum"), false, SoundEvents.BLOCK_DISPENSER_LAUNCH, 1.1f, 1.2f);
    public static final GunStats DESERT_EAGLE = new GunStats("Desert Eagle", 15.0f, 22, 60.0, 0.005f, 1, ammo("ammo_magnum"), false, SoundEvents.ITEM_FIRECHARGE_USE, 1.5f, 0.7f);
    public static final GunStats BERETTA_M9 = new GunStats("Beretta M9", 5.5f, 7, 55.0, 0.012f, 1, ammo("ammo_9mm"), false, SoundEvents.BLOCK_DISPENSER_LAUNCH, 1.0f, 1.4f);

    // ---------- Real-life SMGs ----------
    public static final GunStats MP5 = new GunStats("MP5", 4.0f, 3, 50.0, 0.025f, 1, ammo("ammo_9mm"), false, SoundEvents.BLOCK_DISPENSER_LAUNCH, 0.9f, 1.6f);
    public static final GunStats UZI = new GunStats("Uzi", 2.5f, 2, 35.0, 0.07f, 1, ammo("ammo_9mm"), false, SoundEvents.BLOCK_DISPENSER_LAUNCH, 0.7f, 1.7f);
    public static final GunStats MAC10 = new GunStats("MAC-10", 2.0f, 1, 25.0, 0.10f, 1, ammo("ammo_9mm"), false, SoundEvents.BLOCK_DISPENSER_LAUNCH, 0.6f, 1.8f);
    public static final GunStats THOMPSON = new GunStats("Thompson", 5.0f, 3, 45.0, 0.035f, 1, ammo("ammo_9mm"), false, SoundEvents.BLOCK_DISPENSER_LAUNCH, 1.0f, 1.3f);

    // ---------- Real-life Rifles ----------
    public static final GunStats AK47 = new GunStats("AK-47", 7.0f, 5, 70.0, 0.035f, 1, ammo("ammo_rifle"), false, SoundEvents.BLOCK_DISPENSER_LAUNCH, 1.2f, 0.9f);
    public static final GunStats M4A1 = new GunStats("M4A1", 5.0f, 3, 75.0, 0.02f, 1, ammo("ammo_rifle"), false, SoundEvents.BLOCK_DISPENSER_LAUNCH, 1.1f, 1.0f);
    public static final GunStats AUG = new GunStats("AUG", 5.5f, 4, 75.0, 0.015f, 1, ammo("ammo_rifle"), false, SoundEvents.BLOCK_DISPENSER_LAUNCH, 1.1f, 1.05f);
    public static final GunStats SCAR_H = new GunStats("SCAR-H", 8.0f, 5, 80.0, 0.02f, 1, ammo("ammo_rifle"), false, SoundEvents.BLOCK_DISPENSER_LAUNCH, 1.2f, 0.95f);

    // ---------- Real-life Snipers ----------
    public static final GunStats AWP = new GunStats("AWP", 35.0f, 35, 200.0, 0.0f, 1, ammo("ammo_sniper"), false, SoundEvents.ENTITY_GENERIC_EXPLODE, 0.7f, 1.4f);
    public static final GunStats BARRETT_50CAL = new GunStats("Barrett .50 cal", 50.0f, 50, 250.0, 0.0f, 1, ammo("ammo_sniper"), false, SoundEvents.ENTITY_GENERIC_EXPLODE, 1.0f, 0.6f);

    // ---------- Real-life Shotguns ----------
    public static final GunStats BENELLI_M4 = new GunStats("Benelli M4", 3.5f, 12, 25.0, 0.18f, 6, ammo("ammo_shells"), false, SoundEvents.BLOCK_DISPENSER_LAUNCH, 1.2f, 0.8f);
    public static final GunStats AA12 = new GunStats("AA-12", 2.5f, 4, 25.0, 0.20f, 5, ammo("ammo_shells"), false, SoundEvents.BLOCK_DISPENSER_LAUNCH, 1.3f, 0.7f);

    // ---------- Real-life LMG ----------
    public static final GunStats M249 = new GunStats("M249 SAW", 4.0f, 2, 70.0, 0.04f, 1, ammo("ammo_rifle"), false, SoundEvents.BLOCK_DISPENSER_LAUNCH, 1.3f, 1.1f);
}
