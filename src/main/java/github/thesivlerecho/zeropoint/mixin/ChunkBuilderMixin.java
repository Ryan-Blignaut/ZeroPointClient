package github.thesivlerecho.zeropoint.mixin;

import net.minecraft.client.render.chunk.ChunkBuilder;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ChunkBuilder.BuiltChunk.class)
public abstract class ChunkBuilderMixin
{
/*	@Shadow
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
	private void setOrigin(int x, int y, int z, CallbackInfo callbackInfo)
	{
		if (x != this.origin.getX() || y != this.origin.getY() || z != this.origin.getZ())
		{

			this.clear();
			this.origin.set(x, y, z);
			this.boundingBox = new Box(x, y, z, x + 16, y + 16, z + 16);
			for (Direction direction : Direction.values())
				this.neighborPositions[direction.ordinal()].set(this.origin).move(direction, 16);

		}
	}*/
}
