package me.thesilverecho.zeropoint.mixin;

import me.thesilverecho.zeropoint.module.sky.Skybox;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.item.Items;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Matrix4f;
import net.minecraft.util.shape.VoxelShape;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WorldRenderer.class)
public abstract class WorldRendererMixin
{

	@Shadow
	private ClientWorld world;

	@Inject(
			method = "renderSky",
			slice = @Slice(from = @At(ordinal = 0, value = "INVOKE", target = "Lnet/minecraft/client/world/ClientWorld;getRainGradient(F)F")),
			at = @At(shift = At.Shift.AFTER, ordinal = 0, value = "INVOKE", target = "Lnet/minecraft/client/util/math/MatrixStack;multiply(Lnet/minecraft/util/math/Quaternion;)V"))
	private void renderExtras(MatrixStack ms, float partialTicks, CallbackInfo ci)
	{
//		if (isGogSky())
//		{
		Skybox.renderExtra(ms, world, partialTicks, 0);
//		}
	}


	@Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/WorldRenderer;checkEmpty(Lnet/minecraft/client/util/math/MatrixStack;)V", ordinal = 0))
	private void render(
			MatrixStack matrices, float tickDelta, long limitTime, boolean renderBlockOutline, Camera camera, GameRenderer gameRenderer,
			LightmapTextureManager lightmapTextureManager, Matrix4f matrix4f, CallbackInfo ci)
	{
//		StreamSupport.stream(world.getEntities().spliterator(), false).filter(entity -> entity instanceof PlayerEntity && entity.isAlive()).map(
//				PlayerEntity.class::cast).forEach(playerEntity -> Esp.renderEsp(playerEntity, matrices, tickDelta, camera));
	}

	@Inject(method = "drawShapeOutline", at = @At("INVOKE"), cancellable = true)
	private static void drawBlockOutline(MatrixStack matrixStack,
	                                     VertexConsumer vertexConsumer, VoxelShape voxelShape, double d, double e, double f, float g, float h, float i, float j, CallbackInfo ci)
	{

		ClientPlayerEntity player = MinecraftClient.getInstance().player;
		if (player.getMainHandStack().getItem() == Items.STICK)
		{
			ci.cancel();
			HitResult hitResult = player.raycast(10, MinecraftClient.getInstance().getTickDelta(), false);
			if (hitResult.getType() == HitResult.Type.BLOCK)
			{

				Matrix4f matrix4f = matrixStack.peek().getModel();

				voxelShape.forEachEdge((k, l, m, n, o, p) ->
				{
					vertexConsumer.vertex(matrix4f, (float) (k + d), (float) (l + e), (float) (m + f)).color(g, h, i, j).next();
					vertexConsumer.vertex(matrix4f, (float) (n + d), (float) (o + e), (float) (p + f)).color(g, h, i, j).next();
				});
			}

		}

	}

}
