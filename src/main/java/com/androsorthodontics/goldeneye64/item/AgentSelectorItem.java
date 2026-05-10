package com.androsorthodontics.goldeneye64.item;

import com.androsorthodontics.goldeneye64.game.Agent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.List;

/**
 * Right-click cycles through the agent roster and announces the selection
 * in chat. Sneak + right-click delivers the chosen agent's loadout.
 *
 * The current selection is stored in the stack's NBT under "AgentIndex".
 */
public class AgentSelectorItem extends Item {
    private static final String NBT_INDEX = "AgentIndex";

    public AgentSelectorItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);

        if (!world.isClient) {
            List<Agent> roster = Agent.roster();
            int index = readIndex(stack);

            if (user.isSneaking()) {
                // Apply the kit
                Agent current = roster.get(index % roster.size());
                current.giveKit(user);
            } else {
                // Cycle to next agent
                index = (index + 1) % roster.size();
                writeIndex(stack, index);
                Agent current = roster.get(index);
                user.sendMessage(Text.literal("Selected: ").formatted(Formatting.GRAY)
                    .append(current.displayLine()), true);
                world.playSound(null, user.getX(), user.getY(), user.getZ(),
                    SoundEvents.UI_BUTTON_CLICK.value(), SoundCategory.PLAYERS, 0.5f, 1.4f);
            }
        }

        return TypedActionResult.success(stack, world.isClient);
    }

    private int readIndex(ItemStack stack) {
        NbtCompound nbt = stack.getNbt();
        return nbt != null && nbt.contains(NBT_INDEX) ? nbt.getInt(NBT_INDEX) : 0;
    }

    private void writeIndex(ItemStack stack, int index) {
        stack.getOrCreateNbt().putInt(NBT_INDEX, index);
    }
}
