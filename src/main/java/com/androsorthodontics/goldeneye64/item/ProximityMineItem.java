package com.androsorthodontics.goldeneye64.item;

/**
 * GoldenEye Proximity Mine — placed at your feet, slow detonation by default.
 *
 * v1 caveat: actual proximity detection (auto-trigger when an enemy is near)
 * requires a custom entity tick. For now this acts as a long-fuse trip mine
 * (5 seconds) that you place behind you for the chasing player to find.
 * Proximity auto-trigger is in the TODO list for v2.
 */
public class ProximityMineItem extends ThrownExplosiveItem {
    public ProximityMineItem(Settings settings) {
        super(settings, /* fuseTicks */ 100, /* power */ 4.0f, /* tossForward */ false);
    }
}
