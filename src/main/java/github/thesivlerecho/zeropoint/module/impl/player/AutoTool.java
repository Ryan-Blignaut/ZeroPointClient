package github.thesivlerecho.zeropoint.module.impl.player;


import github.thesivlerecho.zeropoint.config.selector.ConfigOption;
import github.thesivlerecho.zeropoint.event.EventListener;
import github.thesivlerecho.zeropoint.event.events.BlockBreakEvent;
import github.thesivlerecho.zeropoint.event.events.TickEvent;
import github.thesivlerecho.zeropoint.module.BaseModule;
import github.thesivlerecho.zeropoint.module.ModCategory;
import github.thesivlerecho.zeropoint.module.ZPModule;
import net.minecraft.block.BlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;

import java.util.Objects;

@ZPModule(name = "Auto tool", category = ModCategory.PLAYER)
public class AutoTool extends BaseModule
{
	@ConfigOption
	public boolean swapBack = true;

	private int previousSlot = -1;

	private BlockPos blockPos;

	@EventListener
	public void blockBreak(BlockBreakEvent event)
	{
		final BlockPos pos = event.pos();

		if (blockPos != pos)
		{
			final BlockState blockState = Objects.requireNonNull(MINECRAFT_CLIENT.world).getBlockState(pos);
			equipBestTool(blockState);
			blockPos = pos;
		}
	}

	private void equipBestTool(BlockState blockState)
	{
		double maxSpeed = -1;
		int slot = -1;

		for (int i = 0; i < 9; i++)
		{
			assert MINECRAFT_CLIENT.player != null;
			final ItemStack stack = MINECRAFT_CLIENT.player.getInventory().getStack(i);
			if (stack.isEmpty()) continue;
			final float speed = stack.getMiningSpeedMultiplier(blockState);
			if (speed > 1)
			{
				final double totalSpeed = speed + Math.pow(EnchantmentHelper.getLevel(Enchantments.EFFICIENCY, stack), 2);
				if (maxSpeed < totalSpeed)
				{
					maxSpeed = totalSpeed;
					slot = i;
				}
			}

		}
		if (slot != -1)
		{
			previousSlot = MINECRAFT_CLIENT.player.getInventory().selectedSlot;
			MINECRAFT_CLIENT.player.getInventory().selectedSlot = slot;
//			MINECRAFT_CLIENT.interactionManager.
		}
	}


	@EventListener
	public void tick(TickEvent event)
	{
		if (!MINECRAFT_CLIENT.options.keyAttack.isPressed() && previousSlot != -1)
		{
			Objects.requireNonNull(MINECRAFT_CLIENT.player).getInventory().selectedSlot = previousSlot;
			System.out.println("test");
			previousSlot = -1;
		}
	}
}
