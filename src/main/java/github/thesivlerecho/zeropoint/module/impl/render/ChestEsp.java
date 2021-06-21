package github.thesivlerecho.zeropoint.module.impl.render;

import github.thesivlerecho.zeropoint.event.TargetEvent;
import github.thesivlerecho.zeropoint.event.events.Render2dEvent;
import github.thesivlerecho.zeropoint.event.events.RenderTileEntityEvent;
import github.thesivlerecho.zeropoint.module.BaseModule;
import github.thesivlerecho.zeropoint.module.ModCategory;
import github.thesivlerecho.zeropoint.render.shader.ShaderManager;
import github.thesivlerecho.zeropoint.render.shader.ZeroPointShader;
import github.thesivlerecho.zeropoint.render.shader.programs.post.OutlinePostprocessShader;
import github.thesivlerecho.zeropoint.util.IWorld;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.ChestBlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gl.Framebuffer;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.Window;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import org.lwjgl.opengl.EXTFramebufferObject;
import org.lwjgl.opengl.GL11;

public class ChestEsp extends BaseModule
{

	public ChestEsp()
	{
		super("Chest ESP", ModCategory.RENDER);
	}

	@TargetEvent
	public void renderEvent(RenderTileEntityEvent event)
	{
		final BlockEntity entity = event.blockEntity();
		if (!(entity instanceof ChestBlockEntity))
			return;
//		final BlockEntityRenderer<BlockEntity> a = event.a();
//		checkSetupFBO();
//		event.a().render(entity, event.tickDelta(), event.matrices(), event.vertexConsumers(), 15728880, OverlayTexture.DEFAULT_UV);
//		outlineOne();
//		event.a().render(entity, event.tickDelta(), event.matrices(), event.vertexConsumers(), 15728880, OverlayTexture.DEFAULT_UV);
//		outlineTwo();
//		event.a().render(entity, event.tickDelta(), event.matrices(), event.vertexConsumers(), 15728880, OverlayTexture.DEFAULT_UV);
//		outlineThree();
//		outlineFour();
//		GL11.glLineWidth(2.0F);
////		Color rainbow = Gui.rainbow(System.nanoTime(), 1.0F, 1.0F);
////		Color color = ((Boolean) this.rainbowcol.getValueState()).booleanValue() ? new Color((float) rainbow.getRed() / 255.0F, (float) rainbow.getGreen() / 255.0F, (float) rainbow.getBlue() / 255.0F, ((Double) CustomHUDTabGui.alphaSlider.getValueState()).floatValue()) : new Color(FlatColors.ORANGE.c);
////		RenderUtil.color(((Boolean) this.rainbowcol.getValueState()).booleanValue() ? color.getRGB() : (new Color(((Double) this.red.getValueState()).floatValue() / 255.0F, ((Double) this.green.getValueState()).floatValue() / 255.0F, ((Double) this.blue.getValueState()).floatValue() / 255.0F)).getRGB());
//		event.a().render(entity, event.tickDelta(), event.matrices(), event.vertexConsumers(), 15728880, OverlayTexture.DEFAULT_UV);
//		outlineFive();
//
//		event.callbackInfo().cancel();

	}


//	@TargetEvent
	public void renderEvent(Render2dEvent event)
	{
		final OutlinePostprocessShader shader = ShaderManager.getShader(OutlinePostprocessShader.class, ZeroPointShader.OUTLINE);

		final MatrixStack matrixStack = new MatrixStack();//event.matrixStack();
		matrixStack.loadIdentity();
		final ClientWorld clientWorld = MinecraftClient.getInstance().world;
		final IWorld world = (IWorld) clientWorld;
		world.getTileEntity().stream().map(bti -> clientWorld.getBlockEntity(bti.getPos())).forEach(blockEntity ->
		{
			if (blockEntity instanceof ChestBlockEntity)
			{


				final Camera camera = MinecraftClient.getInstance().getBlockEntityRenderDispatcher().camera;
				final Vec3d pos = camera.getPos();
				double d = pos.getX();
				double e = pos.getY();
				double f = pos.getZ();
				BlockPos blockPos = blockEntity.getPos();
				shader.bind();
				matrixStack.push();

//				matrixStack.
				matrixStack.translate((double) blockPos.getX() - d, (double) blockPos.getY() - e+1, (double) blockPos.getZ() - f);
//				matrixStack.multiply(new Quaternion(1, 0, 0, (camera.getPitch())));
//				matrixStack.multiply(new Quaternion(0, 1, 0, (float) (camera.getYaw() + 180.0)));
//				matrixStack.translate((double) blockPos.getX(), (double) blockPos.getY(), (double) blockPos.getZ());
//				matrixStack.translate(0, 0, 0);

				VertexConsumerProvider.Immediate provider = MinecraftClient.getInstance().getBufferBuilders().getEntityVertexConsumers();
				MinecraftClient.getInstance().getBlockEntityRenderDispatcher().render(blockEntity, 1, matrixStack, provider);
				shader.unBind();
				matrixStack.pop();

			}
		});
	}


	public static void checkSetupFBO()
	{
		Framebuffer fbo = MinecraftClient.getInstance().getFramebuffer();
		final Window w = MinecraftClient.getInstance().getWindow();
		if (fbo != null && fbo.getDepthAttachment() > -1)
		{
			EXTFramebufferObject.glDeleteRenderbuffersEXT(fbo.getDepthAttachment());
			int stencil_depth_buffer_ID = EXTFramebufferObject.glGenRenderbuffersEXT();
			EXTFramebufferObject.glBindRenderbufferEXT(36161, stencil_depth_buffer_ID);
			EXTFramebufferObject.glRenderbufferStorageEXT(36161, 34041, w.getScaledWidth(), w.getScaledHeight());
			EXTFramebufferObject.glFramebufferRenderbufferEXT(36160, 36128, 36161, stencil_depth_buffer_ID);
			EXTFramebufferObject.glFramebufferRenderbufferEXT(36160, 36096, 36161, stencil_depth_buffer_ID);
//			fbo.depuseDepthAttachment = false;
		}
	}


	public static void outlineOne()
	{
		GL11.glPushAttrib(0xfffff);
		GL11.glDisable(3008);
		GL11.glDisable(3553);
		GL11.glDisable(2896);
		GL11.glEnable(3042);
		GL11.glBlendFunc(770, 771);
		GL11.glLineWidth(4.0f);
		GL11.glEnable(2848);
		GL11.glEnable(2960);
		GL11.glClear(1024);
		GL11.glClearStencil(15);
		GL11.glStencilFunc(512, 1, 15);
		GL11.glStencilOp(7681, 7681, 7681);
		GL11.glPolygonMode(1032, 6913);
	}

	public static void outlineTwo()
	{
		GL11.glStencilFunc(512, 0, 15);
		GL11.glStencilOp(7681, 7681, 7681);
		GL11.glPolygonMode(1032, 6914);
	}

	public static void outlineThree()
	{
		GL11.glStencilFunc(514, 1, 15);
		GL11.glStencilOp(7680, 7680, 7680);
		GL11.glPolygonMode(1032, 6913);
	}

	public static void outlineFour()
	{
		GL11.glDepthMask(false);
		GL11.glDisable(2929);
		GL11.glEnable(10754);
		GL11.glPolygonOffset(1.0f, -2000000.0f);
		GL11.glColor4f(0.9529412f, 0.6117647f, 0.07058824f, 1.0f);
//		OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240.0f, 240.0f);
	}

	public static void outlineFive()
	{
		GL11.glPolygonOffset(1.0f, 2000000.0f);
		GL11.glDisable(10754);
		GL11.glEnable(2929);
		GL11.glDepthMask(true);
		GL11.glDisable(2960);
		GL11.glDisable(2848);
		GL11.glHint(3154, 4352);
		GL11.glEnable(3042);
		GL11.glEnable(2896);
		GL11.glEnable(3553);
		GL11.glEnable(3008);
		GL11.glPopAttrib();
	}


}
