package com.androsorthodontics.goldeneye64.item;

import net.minecraft.item.Item;

/**
 * Plain stackable ammo item. Guns look up the matching ammo type
 * by registry id and decrement one per shot.
 */
public class AmmoItem extends Item {
    public AmmoItem(Settings settings) {
        super(settings);
    }
}
