package com.androsorthodontics.goldeneye64.item;

import net.minecraft.entity.TntEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

/**
 * Base class for the three GoldenEye-style mine items. The actual mine is
 * implemented as a primed TntEntity with a configurable fuse — that gives
 * us real Minecraft-engine explosion physics without registering a custom
 * entity. Right-click throws/places it at the player's feet with a small
 * forward toss.
 *
 * Subclasses choose the fuse length and whether the toss is forward (thrown)
 * or under-foot (placed mine).
 */
public abstract class ThrownExplosiveItem extends Item {
    private final int fuseTicks;
    private final float power; // explosion power; vanilla TNT = 4.0
    private final boolean tossForward;

    protected ThrownExplosiveItem(Settings settings, int fuseTicks, float power, boolean tossForward) {
        super(settings);
        this.fuseTicks = fuseTicks;
        this.power = power;
        this.tossForward = tossForward;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);

        if (!world.isClient) {
            Vec3d origin = user.getPos().add(0, user.getStandingEyeHeight() * 0.5, 0);
            TntEntity tnt = new TntEntity(world, origin.x, origin.y, origin.z, user);
            tnt.setFuse(fuseTicks);

            if (tossForward) {
                Vec3d look = user.getRotationVec(1.0f);
                tnt.setVelocity(look.x * 0.7, 0.3, look.z * 0.7);
            } else {
                tnt.setVelocity(0, 0.1, 0);
            }

            // The vanilla TntEntity has a fixed explosion power (4.0). To respect
            // our `power` field we'd need a mixin or custom entity — we keep the
            // field for future expansion and rely on the vanilla explosion for now.
            world.spawnEntity(tnt);

            world.playSound(null, user.getX(), user.getY(), user.getZ(),
                SoundEvents.ENTITY_TNT_PRIMED, SoundCategory.PLAYERS, 0.8f, 1.4f);
        }

        if (!user.getAbilities().creativeMode) {
            stack.decrement(1);
        }

        user.getItemCooldownManager().set(this, 10);
        return TypedActionResult.success(stack, world.isClient);
    }

    public int getFuseTicks() { return fuseTicks; }
    public float getPower() { return power; }
}
