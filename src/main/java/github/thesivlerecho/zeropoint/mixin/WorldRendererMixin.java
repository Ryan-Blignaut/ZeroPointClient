package github.thesivlerecho.zeropoint.mixin;

import github.thesivlerecho.zeropoint.event.EventManager;
import github.thesivlerecho.zeropoint.event.events.BlockOutlineEvent;
import github.thesivlerecho.zeropoint.event.events.Render3dEvent;
import net.minecraft.client.render.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.math.Matrix4f;
import net.minecraft.util.shape.VoxelShape;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WorldRenderer.class)
public abstract class WorldRendererMixin
{

	@Shadow private ClientWorld world;

	@Shadow @Final private BufferBuilderStorage bufferBuilders;

	@Inject(method = "drawShapeOutline", at = @At("HEAD"), cancellable = true)
	private static void drawBlockOutline(MatrixStack matrixStack, VertexConsumer vertexConsumer, VoxelShape voxelShape, double d, double e, double f, float g, float h, float i, float j, CallbackInfo ci)
	{
		EventManager.call(new BlockOutlineEvent(matrixStack, voxelShape, d, e, f, ci));
	}

	@Inject(
			method = "renderSky",
			slice = @Slice(from = @At(ordinal = 0, value = "INVOKE", target = "Lnet/minecraft/client/world/ClientWorld;getRainGradient(F)F")),
			at = @At(shift = At.Shift.AFTER, ordinal = 0, value = "INVOKE", target = "Lnet/minecraft/client/util/math/MatrixStack;multiply(Lnet/minecraft/util/math/Quaternion;)V"))
	private void renderExtras(MatrixStack matrices, Matrix4f matrix4f, float f, Runnable runnable, CallbackInfo ci)
	{
//		if (isGogSky())
//		{
//		Skybox.renderExtra(ms, world, partialTicks, 0);
//		}
	}

	@Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/WorldRenderer;checkEmpty(Lnet/minecraft/client/util/math/MatrixStack;)V", ordinal = 0))
	private void render(MatrixStack matrices, float tickDelta, long limitTime, boolean renderBlockOutline, Camera camera, GameRenderer gameRenderer, LightmapTextureManager lightmapTextureManager, Matrix4f matrix4f, CallbackInfo ci)
	{

		final Render3dEvent event = new Render3dEvent(matrices);
		EventManager.call(event);

//		StreamSupport.stream(world.getEntities().spliterator(), false).filter(entity -> entity instanceof PlayerEntity && entity.isAlive()).map(
//				PlayerEntity.class::cast).forEach(playerEntity -> Esp.renderEsp(playerEntity, matrices, tickDelta, camera));

		/*world.blockEntities.stream().filter(blockEntity -> blockEntity instanceof ChestBlockEntity).map(blockEntity -> ((ChestBlockEntity) blockEntity)).forEach(chestBlockEntity ->
		{
//			esp(chestBlockEntity, chestBlockEntity.getPos().getX(), chestBlockEntity.getPos().getY(), chestBlockEntity.getPos().getZ());
		});*/

	}

	/*private static void esp(ChestBlockEntity chestBlockEntity, double x, double y, double z)
	{
//if (chestBlockEntity.)
		GL11.glPushMatrix();
		GL11.glPushMatrix();
		GL11.glEnable(0xbe2);
		GL11.glBlendFunc(770, 771);
		GL11.glShadeModel(0x1d01);
		GL11.glDisable(0xde1);
		GL11.glEnable(0xb20);
		GL11.glDisable(0xb71);
		GL11.glDisable(0xb50);
		GL11.glDepthMask(false);
		GL11.glHint(0xc52, 0x1102);
//		GlStateManager.Method1786();
//		this.Field12213.Field10321.Method8450(this.Field12213.Field10359.Field8184, 2);
//		GL11.glColor4f(Field12207.Method10888() / 255.0F, Field12211.Method10888() / 255.0F, Field12209.Method10888() / 255.0F, 0.3F);
		Vec3d var9 = new Vec3d(x + 0.0625D, y, z + 0.0625D);
		Vec3d var10 = new Vec3d(x + 0.9375D, y + 0.875D, z + 0.9375D);

		GL11.glColor4f(1, 0.0F, 0.0F, 0.3F);
		EntityRenderDispatcher renderManager = MinecraftClient.getInstance().getEntityRenderDispatcher();
		render(new Box(var9.x *//*- renderManager.camera.getPos().x*//*, var9.y *//*- renderManager.camera.getPos().y*//*, var9.z *//*- renderManager.camera.getPos().z*//*, var10.x *//*- renderManager.camera.getPos().x*//*, var10.y *//*- renderManager.camera.getPos().y*//*, var10.z *//*- renderManager.camera.getPos().z*//*));
		GL11.glColor4f(0.0F, 0.0F, 0.0F, 1.0F);
//		GlStateManager.Method1837();

		GL11.glDepthMask(true);
		GL11.glEnable(2929);
		GL11.glDisable(2848);
		GL11.glEnable(3553);
		GL11.glDisable(3042);
		GL11.glPopMatrix();
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glPopMatrix();

	}*/

/*
	private static void render(Box box)
	{
		Tessellator var1 = Tessellator.getInstance();
		final BufferBuilder buffer = var1.getBuffer();
// minZ minX,minX minY,minY minZ
// minY maxX,minZ maxY,minX maxZ
//

		buffer.begin(7, VertexFormats.POSITION);
		buffer.vertex(box.minZ, box.minY, box.minX).next();
		buffer.vertex(box.minZ, box.minX, box.minX).next();
		buffer.vertex(box.minY, box.minY, box.minX).next();
		buffer.vertex(box.minY, box.minX, box.minX).next();
		buffer.vertex(box.minY, box.minY, box.minZ).next();
		buffer.vertex(box.minY, box.minX, box.minZ).next();
		buffer.vertex(box.minZ, box.minY, box.minZ).next();
		buffer.vertex(box.minZ, box.minX, box.minZ).next();
		var1.draw();

		buffer.begin(7, VertexFormats.POSITION);
		buffer.vertex(box.minY, box.minX, box.minX).next();
		buffer.vertex(box.minY, box.minY, box.minX).next();
		buffer.vertex(box.minZ, box.minX, box.minX).next();
		buffer.vertex(box.minZ, box.minY, box.minX).next();
		buffer.vertex(box.minZ, box.minX, box.minZ).next();
		buffer.vertex(box.minZ, box.minY, box.minZ).next();
		buffer.vertex(box.minY, box.minX, box.minZ).next();
		buffer.vertex(box.minY, box.minY, box.minZ).next();
		var1.draw();

		buffer.begin(7, VertexFormats.POSITION);
		buffer.vertex(box.minZ, box.minX, box.minX).next();
		buffer.vertex(box.minY, box.minX, box.minX).next();
		buffer.vertex(box.minY, box.minX, box.minZ).next();
		buffer.vertex(box.minZ, box.minX, box.minZ).next();
		buffer.vertex(box.minZ, box.minX, box.minX).next();
		buffer.vertex(box.minZ, box.minX, box.minZ).next();
		buffer.vertex(box.minY, box.minX, box.minZ).next();
		buffer.vertex(box.minY, box.minX, box.minX).next();
		var1.draw();

		buffer.begin(7, VertexFormats.POSITION);
		buffer.vertex(box.minZ, box.minY, box.minX).next();
		buffer.vertex(box.minY, box.minY, box.minX).next();
		buffer.vertex(box.minY, box.minY, box.minZ).next();
		buffer.vertex(box.minZ, box.minY, box.minZ).next();
		buffer.vertex(box.minZ, box.minY, box.minX).next();
		buffer.vertex(box.minZ, box.minY, box.minZ).next();
		buffer.vertex(box.minY, box.minY, box.minZ).next();
		buffer.vertex(box.minY, box.minY, box.minX).next();
		var1.draw();

		buffer.begin(7, VertexFormats.POSITION);
		buffer.vertex(box.minZ, box.minY, box.minX).next();
		buffer.vertex(box.minZ, box.minX, box.minX).next();
		buffer.vertex(box.minZ, box.minY, box.minZ).next();
		buffer.vertex(box.minZ, box.minX, box.minZ).next();
		buffer.vertex(box.minY, box.minY, box.minZ).next();
		buffer.vertex(box.minY, box.minX, box.minZ).next();
		buffer.vertex(box.minY, box.minY, box.minX).next();
		buffer.vertex(box.minY, box.minX, box.minX).next();
		var1.draw();

		buffer.begin(7, VertexFormats.POSITION);
		buffer.vertex(box.minZ, box.minX, box.minZ).next();
		buffer.vertex(box.minZ, box.minY, box.minZ).next();
		buffer.vertex(box.minZ, box.minX, box.minX).next();
		buffer.vertex(box.minZ, box.minY, box.minX).next();
		buffer.vertex(box.minY, box.minX, box.minX).next();
		buffer.vertex(box.minY, box.minY, box.minX).next();
		buffer.vertex(box.minY, box.minX, box.minZ).next();
		buffer.vertex(box.minY, box.minY, box.minZ).next();
		var1.draw();
	}
*/

}
