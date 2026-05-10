package com.androsorthodontics.goldeneye64.item;

import com.androsorthodontics.goldeneye64.GoldenEyeMod;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;

/**
 * Tuning data for every gun in the mod. Values are tuned to feel like
 * the GoldenEye 007 (N64) originals: PP7 weak/accurate, Klobb fast/inaccurate,
 * RC-P90 high-fire-rate beast, Sniper one-shot at range, Golden Gun one-shot anywhere.
 *
 * Damage is in Minecraft hearts × 2 (so 6.0f = 3 hearts).
 * Cooldown is in ticks (20 ticks = 1 second).
 * Spread is roughly the half-angle of the bullet cone, in radians.
 */
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

    // ---------- Pistols ----------
    public static final GunStats PP7 = new GunStats(
        "PP7", 5.0f, 8, 60.0, 0.01f, 1, ammo("ammo_9mm"), false,
        SoundEvents.BLOCK_DISPENSER_LAUNCH, 1.0f, 1.4f);

    public static final GunStats PP7_SILENCED = new GunStats(
        "PP7 (Silenced)", 5.0f, 8, 60.0, 0.01f, 1, ammo("ammo_9mm"), true,
        SoundEvents.BLOCK_WOOL_HIT, 0.4f, 1.6f);

    public static final GunStats DD44 = new GunStats(
        "DD44 Dostovei", 6.0f, 7, 55.0, 0.02f, 1, ammo("ammo_9mm"), false,
        SoundEvents.BLOCK_DISPENSER_LAUNCH, 1.0f, 1.2f);

    public static final GunStats COUGAR_MAGNUM = new GunStats(
        "Cougar Magnum", 12.0f, 18, 70.0, 0.005f, 1, ammo("ammo_magnum"), false,
        SoundEvents.ITEM_FIRECHARGE_USE, 1.4f, 0.8f);

    // ---------- SMGs ----------
    public static final GunStats KLOBB = new GunStats(
        "Klobb", 2.0f, 2, 35.0, 0.10f, 1, ammo("ammo_9mm"), false,
        SoundEvents.BLOCK_DISPENSER_LAUNCH, 0.6f, 1.8f);

    public static final GunStats ZMG = new GunStats(
        "ZMG (9mm)", 3.0f, 3, 40.0, 0.05f, 1, ammo("ammo_9mm"), false,
        SoundEvents.BLOCK_DISPENSER_LAUNCH, 0.8f, 1.6f);

    public static final GunStats D5K = new GunStats(
        "D5K Deutsche", 3.5f, 3, 40.0, 0.04f, 1, ammo("ammo_9mm"), true,
        SoundEvents.BLOCK_WOOL_HIT, 0.3f, 1.7f);

    public static final GunStats RCP90 = new GunStats(
        "RC-P90", 3.5f, 2, 50.0, 0.025f, 1, ammo("ammo_9mm"), false,
        SoundEvents.BLOCK_DISPENSER_LAUNCH, 0.8f, 1.7f);

    // ---------- Rifles ----------
    public static final GunStats KF7_SOVIET = new GunStats(
        "KF7 Soviet", 5.5f, 4, 70.0, 0.03f, 1, ammo("ammo_rifle"), false,
        SoundEvents.BLOCK_DISPENSER_LAUNCH, 1.1f, 1.0f);

    public static final GunStats AR33 = new GunStats(
        "AR33", 6.0f, 4, 75.0, 0.025f, 1, ammo("ammo_rifle"), false,
        SoundEvents.BLOCK_DISPENSER_LAUNCH, 1.1f, 0.95f);

    public static final GunStats SNIPER_RIFLE = new GunStats(
        "Sniper Rifle", 30.0f, 30, 200.0, 0.0f, 1, ammo("ammo_sniper"), false,
        SoundEvents.ENTITY_GENERIC_EXPLODE, 0.6f, 1.5f);

    // ---------- Shotguns ----------
    public static final GunStats SHOTGUN = new GunStats(
        "Shotgun", 3.0f, 18, 25.0, 0.18f, 6, ammo("ammo_shells"), false,
        SoundEvents.BLOCK_DISPENSER_LAUNCH, 1.3f, 0.7f);

    public static final GunStats AUTO_SHOTGUN = new GunStats(
        "Automatic Shotgun", 2.5f, 6, 25.0, 0.18f, 5, ammo("ammo_shells"), false,
        SoundEvents.BLOCK_DISPENSER_LAUNCH, 1.2f, 0.8f);

    // ---------- Special ----------
    /** One shot, one kill — anywhere on the body, any range. */
    public static final GunStats GOLDEN_GUN = new GunStats(
        "Golden Gun", 1000.0f, 25, 200.0, 0.0f, 1, ammo("ammo_golden"), false,
        SoundEvents.ITEM_TOTEM_USE, 1.5f, 1.3f);
}
