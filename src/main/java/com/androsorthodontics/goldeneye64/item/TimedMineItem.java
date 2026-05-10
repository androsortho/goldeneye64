package com.androsorthodontics.goldeneye64.item;

/** GoldenEye Timed Mine — lobbed explosive with a 3-second fuse. */
public class TimedMineItem extends ThrownExplosiveItem {
    public TimedMineItem(Settings settings) {
        super(settings, /* fuseTicks */ 60, /* power */ 4.0f, /* tossForward */ true);
    }
}
