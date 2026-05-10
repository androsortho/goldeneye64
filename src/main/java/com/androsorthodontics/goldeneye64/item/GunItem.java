package com.androsorthodontics.goldeneye64.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.Registries;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;

import java.util.Random;

/**
 * Generic hitscan firearm. Right-click to fire — performs an instant ray
 * from the shooter's eyes along their look vector, applies damage to the
 * first entity / stops at the first block, draws a particle trail, plays
 * a fire sound, and consumes one ammo item from the player's inventory.
 *
 * Behavior is config-driven via {@link GunStats}.
 */
public class GunItem extends Item {
    private final GunStats stats;
    private static final Random RNG = new Random();

    public GunItem(Settings settings, GunStats stats) {
        super(settings);
        this.stats = stats;
    }

    public GunStats getStats() {
        return stats;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);

        // Cooldown gate
        if (user.getItemCooldownManager().isCoolingDown(this)) {
            return TypedActionResult.fail(stack);
        }

        // Server-authoritative shot
        if (!world.isClient) {
            if (!consumeAmmo(user)) {
                // Out of ammo: dry click
                world.playSound(null, user.getX(), user.getY(), user.getZ(),
                    SoundEvents.BLOCK_LEVER_CLICK, SoundCategory.PLAYERS, 0.5f, 1.6f);
                user.sendMessage(Text.literal("§7Out of ammo for §f" + stats.displayName), true);
                user.getItemCooldownManager().set(this, 10);
                return TypedActionResult.fail(stack);
            }

            fire((ServerWorld) world, user);
        }

        user.getItemCooldownManager().set(this, stats.cooldownTicks);
        return TypedActionResult.success(stack, world.isClient);
    }

    private boolean consumeAmmo(PlayerEntity user) {
        if (user.getAbilities().creativeMode) return true;

        Item ammoItem = Registries.ITEM.get(stats.ammoId);
        if (ammoItem == null) return true; // misconfigured — fail open

        PlayerInventory inv = user.getInventory();
        for (int i = 0; i < inv.size(); i++) {
            ItemStack s = inv.getStack(i);
            if (s.getItem() == ammoItem && !s.isEmpty()) {
                s.decrement(1);
                return true;
            }
        }
        return false;
    }

    private void fire(ServerWorld world, PlayerEntity shooter) {
        Vec3d eye = shooter.getCameraPosVec(1.0f);
        Vec3d look = shooter.getRotationVec(1.0f);

        for (int p = 0; p < stats.pelletsPerShot; p++) {
            Vec3d dir = applySpread(look, stats.spread);
            Vec3d end = eye.add(dir.multiply(stats.maxRange));

            HitResult hit = raycast(world, shooter, eye, end);

            // Particle trail (smoke for the first pellet only — keeps things visible without spam)
            if (p == 0) {
                spawnTrail(world, eye, hit.getPos());
            }

            if (hit instanceof EntityHitResult ehr) {
                Entity target = ehr.getEntity();
                target.damage(world.getDamageSources().playerAttack(shooter), stats.damage);
                // Hit feedback
                world.spawnParticles(ParticleTypes.CRIT, ehr.getPos().x, ehr.getPos().y, ehr.getPos().z,
                    8, 0.1, 0.1, 0.1, 0.1);
            } else if (hit instanceof BlockHitResult bhr && hit.getType() != HitResult.Type.MISS) {
                world.spawnParticles(ParticleTypes.SMOKE,
                    bhr.getPos().x, bhr.getPos().y, bhr.getPos().z,
                    4, 0.05, 0.05, 0.05, 0.02);
            }
        }

        // Fire sound — silenced guns don't broadcast as far / loud
        world.playSound(null, shooter.getX(), shooter.getY(), shooter.getZ(),
            stats.fireSound, SoundCategory.PLAYERS,
            stats.volume, stats.pitch + (RNG.nextFloat() - 0.5f) * 0.05f);

        // Slight knockback / kick on the shooter (pushed back by recoil) — disabled for now to keep it casual
    }

    private Vec3d applySpread(Vec3d look, float spread) {
        if (spread <= 0.0001f) return look;
        // Sample inside a small cone around the look vector
        double dx = (RNG.nextGaussian()) * spread;
        double dy = (RNG.nextGaussian()) * spread;
        double dz = (RNG.nextGaussian()) * spread;
        return look.add(dx, dy, dz).normalize();
    }

    private HitResult raycast(World world, PlayerEntity shooter, Vec3d start, Vec3d end) {
        BlockHitResult blockHit = world.raycast(new RaycastContext(
            start, end,
            RaycastContext.ShapeType.COLLIDER,
            RaycastContext.FluidHandling.NONE,
            shooter
        ));

        Vec3d effectiveEnd = blockHit.getType() == HitResult.Type.MISS ? end : blockHit.getPos();
        double maxDistSq = start.squaredDistanceTo(effectiveEnd);

        Box searchBox = new Box(start, effectiveEnd).expand(1.0);
        EntityHitResult entityHit = ProjectileUtil.raycast(
            shooter,
            start,
            effectiveEnd,
            searchBox,
            (e) -> !e.isSpectator() && e.canHit() && e != shooter,
            maxDistSq
        );

        if (entityHit != null) return entityHit;
        return blockHit;
    }

    private void spawnTrail(ServerWorld world, Vec3d from, Vec3d to) {
        Vec3d step = to.subtract(from);
        double length = step.length();
        int points = (int) Math.min(40, Math.max(2, length));
        Vec3d unit = step.normalize();

        for (int i = 1; i < points; i++) {
            Vec3d p = from.add(unit.multiply(i * (length / points)));
            world.spawnParticles(ParticleTypes.END_ROD, p.x, p.y, p.z, 1, 0, 0, 0, 0);
        }
    }

    @Override
    public boolean canMine(net.minecraft.block.BlockState state, World world, net.minecraft.util.math.BlockPos pos, PlayerEntity miner) {
        // Don't break blocks when shooting them
        return false;
    }
}
