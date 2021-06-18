package github.thesivlerecho.zeropoint.mixin;

import github.thesivlerecho.zeropoint.event.EventManager;
import github.thesivlerecho.zeropoint.event.events.RenderTileEntityEvent;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BlockEntityRenderDispatcher.class)
public abstract class BlockEntityRenderDispatcherMixin
{
	@Shadow
	@Nullable
	public abstract <E extends BlockEntity> BlockEntityRenderer<E> get(E blockEntity);

	@Shadow
	private static void runReported(BlockEntity blockEntity, Runnable runnable) { }

	@Shadow public Camera camera;

	@Inject(method = "render(Lnet/minecraft/block/entity/BlockEntity;FLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;)V", at = @At(value = "HEAD", ordinal = 0), cancellable = true)
	private <E extends BlockEntity> void injectRenderEvent(E blockEntity, float tickDelta, MatrixStack matrix, VertexConsumerProvider vertexConsumerProvider, CallbackInfo ci)
	{
		BlockEntityRenderer<BlockEntity> blockEntityRenderer = this.get(blockEntity);
		if (blockEntityRenderer != null && blockEntity.hasWorld() && blockEntity.getType().supports(blockEntity.getCachedState()))
			if (blockEntityRenderer.method_33892(blockEntity, this.camera.getPos()))
				runReported(blockEntity, () ->
						EventManager.call(new RenderTileEntityEvent(blockEntityRenderer, blockEntity, tickDelta, matrix, vertexConsumerProvider, ci)));
	}

}
