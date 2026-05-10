package com.androsorthodontics.goldeneye64.item;

/**
 * GoldenEye Remote Mine — placed and waits a long time. v1 uses a 30-second fuse
 * (proper remote-detonation requires a separate detonator item + entity tracking,
 * coming in v2).
 */
public class RemoteMineItem extends ThrownExplosiveItem {
    public RemoteMineItem(Settings settings) {
        super(settings, /* fuseTicks */ 600, /* power */ 4.0f, /* tossForward */ false);
    }
}
