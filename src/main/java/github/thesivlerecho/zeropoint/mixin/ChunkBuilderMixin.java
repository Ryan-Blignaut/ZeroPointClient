package github.thesivlerecho.zeropoint.mixin;

import net.minecraft.client.render.chunk.ChunkBuilder;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ChunkBuilder.BuiltChunk.class)
public abstract class ChunkBuilderMixin
{
	@Shadow
	protected abstract void clear();

	@Shadow
	@Final
	private BlockPos.Mutable origin;

	@Shadow
	public Box boundingBox;

	@Shadow
	@Final
	private BlockPos.Mutable[] neighborPositions;

	@Inject(method = "setOrigin", at = @At("HEAD"), cancellable = true)
	private void setOrigin(int x, int y, int z, CallbackInfo ci)
	{
		if (x != this.origin.getX() || y != this.origin.getY() || z != this.origin.getZ())
		{

			this.clear();
			this.origin.set(x, y, z);
			this.boundingBox = new Box(x, y, z, x + 16, y + 16, z + 16);
			for (Direction direction : Direction.values())
				this.neighborPositions[direction.ordinal()].set(this.origin).move(direction, 16);

		}
	}
}
