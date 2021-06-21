package github.thesivlerecho.zeropoint.util;

import net.minecraft.world.chunk.BlockEntityTickInvoker;

import java.util.List;

public interface IWorld
{
	List<BlockEntityTickInvoker> getTileEntity();
}
