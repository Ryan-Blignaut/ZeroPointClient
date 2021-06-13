package github.thesivlerecho.zeropoint.mod;


import com.google.common.collect.ImmutableList;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;

import java.awt.*;
import java.util.List;

public class GreenerGrass
{
	private static final List<Block> GRASS = ImmutableList.of(Blocks.LARGE_FERN, Blocks.FERN, Blocks.TALL_GRASS, Blocks.GRASS_BLOCK, Blocks.GRASS, Blocks.POTTED_FERN, Blocks.SUGAR_CANE);
	private static final List<Block> LEAVES = ImmutableList.of(Blocks.OAK_LEAVES, Blocks.SPRUCE_LEAVES, Blocks.BIRCH_LEAVES, Blocks.ACACIA_LEAVES, Blocks.DARK_OAK_LEAVES, Blocks.JUNGLE_LEAVES, Blocks.VINE);

	public static void registerGreenerColors()
	{
		register(GRASS);
		register(LEAVES);
	}

	private static void register(List<Block> blocks)
	{
//		blocks.forEach(block -> ColorProviderRegistryImpl.BLOCK.register((state, world, pos, tintIndex) -> getGreenerColour(block), block));
	}

	private static int getGreenerColour(Block block)
	{
		int color = block.getDefaultMapColor().color;
		int r = color >> 16 & 0xFF, g = color >> 8 & 0xFF, b = color & 0xFF;
		return Color.HSBtoRGB((float) ((System.currentTimeMillis()) % 2000) / 2000.0F, 0.8F, 0.8F);

		//(Math.max(0, Math.min(0xFF, r - 30)) << 16) + Math.max(0, Math.min(0xFF, g + 30) << 8) + Math.max(0, Math.min(0xFF, b - 30));
	}
}
