package me.thesilverecho.zeropoint;

import jchroma.JChroma;
import jchroma.effects.CustomKeyboardEffect;
import jchroma.utils.ColorRef;
import me.thesilverecho.zeropoint.gui.DrawingHelper;
import me.thesilverecho.zeropoint.gui.overlay.watermark.WatermarkType;
import me.thesilverecho.zeropoint.gui.screen.SlideOut;
import me.thesilverecho.zeropoint.module.Module;
import me.thesilverecho.zeropoint.module.ModuleManager;
import me.thesilverecho.zeropoint.registration.KeyBinds;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.block.BlockState;
import net.minecraft.block.ChestBlock;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Environment(EnvType.CLIENT) public class ZeroPointClient implements ClientModInitializer
{
	public final static CustomKeyboardEffect effect = new CustomKeyboardEffect();

	public final static List<BlockPos> BLOCK_POS = new ArrayList<>();
	public final static HashMap<BlockPos, BlockState> BLOCK_POS_HASH = new HashMap<>();

	public static final Logger LOGGER = LogManager.getLogger("Zero Point");
	public static final DrawingHelper DRAWING_HELPER = new DrawingHelper();
	public static final WatermarkType WATERMARK_TYPE = WatermarkType.TOP;

	@Override public void onInitializeClient()
	{

		JChroma.getInstance().init();
		LOGGER.info("Hello From ZeroPoint Client");
		KeyBinds.registerKeys();
		ClientTickEvents.START_CLIENT_TICK.register(minecraftClient ->
		{
			ClientPlayerEntity player = minecraftClient.player;
			ClientWorld world = minecraftClient.world;

			if (player == null || world == null)
				return;

			ModuleManager.ACTIVE_MODULES.forEach(Module::onTick);
			ModuleManager.ACTIVE_MODULES.forEach(Module::keyPressed);

			for (int i = 0; i < 9; i++)
			{
				ItemStack stack = player.inventory.getStack(i);
				if (stack != null && stack.getItem() != Items.AIR)
				{
					effect.setKeyColor(258 + i, ColorRef.fromRGB(Color.CYAN.getRGB()));
				} else
					effect.setKeyColor(258 + i, ColorRef.fromRGB(Color.BLACK.getRGB()));

			}
			JChroma.getInstance().createKeyboardEffect(effect);

			if (KeyBinds.MENU.wasPressed())
			{
				MinecraftClient.getInstance().openScreen(new SlideOut());
			}
			if (minecraftClient.currentScreen != null)
			{
				int length = 4;
				BlockPos blockPos = player.getBlockPos();
				for (int x = -length; x <= length; x++)
					for (int y = -length; y <= length; y++)
						for (int z = -length; z <= length; z++)
						{
							BlockPos pos = blockPos.add(x, y, z);
							BlockState blockState = world.getBlockState(pos);
							if (blockState.getBlock() instanceof ChestBlock)
							{
								BLOCK_POS.add(pos);
								BLOCK_POS_HASH.put(blockPos, blockState);
							}

						}
			}

		});

	}
}
