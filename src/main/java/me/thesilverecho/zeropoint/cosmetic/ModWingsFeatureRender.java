package me.thesilverecho.zeropoint.cosmetic;

import com.mojang.blaze3d.systems.RenderSystem;
import me.thesilverecho.zeropoint.config.Settings;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.util.math.Vector3f;
import net.minecraft.util.Identifier;
import org.lwjgl.opengl.GL11;

public class ModWingsFeatureRender extends FeatureRenderer<AbstractClientPlayerEntity, PlayerEntityModel<AbstractClientPlayerEntity>>
{

	private final ModelPart wing;
	private final ModelPart wingTip;
	private final Identifier identifier = new Identifier("tweaks", "textures/ui/text.png");

	public ModWingsFeatureRender(FeatureRendererContext<AbstractClientPlayerEntity, PlayerEntityModel<AbstractClientPlayerEntity>> context)
	{
		super(context);
		this.wing = new ModelPart(30, 30, 0, 0);
		this.wingTip = new ModelPart(30, 30, 0, 5);
		ModelPart wingSkin = new ModelPart(30, 30, -10, 8);
		ModelPart wingTipSkin = new ModelPart(30, 30, -10, 18);

		this.wing.setPivot(-2, 0, 0);
		this.wing.addCuboid(-10.0F, -1.0F, -1.0F, 10, 2, 2);
		//skin
		wingSkin.addCuboid(-10.0F, 0.0F, 0.5F, 10, 0, 10);

		this.wingTip.setPivot(-10, 0, 0);
		this.wingTip.addCuboid(-10.0F, -0.5F, -0.5F, 10, 1, 1);
		//skin
		wingTipSkin.addCuboid(-10.0F, 0.0F, 0.5F, 10, 0, 10);

		this.wingTip.addChild(wingTipSkin);
		this.wing.addChild(wingSkin);

		this.wing.addChild(wingTip);
	}

	@Override
	public void render(MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i,
	                   AbstractClientPlayerEntity abstractClientPlayerEntity, float limbAngle, float limbDistance, float tickDelta, float customAngle,
	                   float headYaw, float headPitch)
	{
		renderWings(abstractClientPlayerEntity, i, matrixStack, vertexConsumerProvider);
	}

	private void renderWings3(AbstractClientPlayerEntity player, float partialTicks, int light, MatrixStack matrixStack,
	                          VertexConsumerProvider vertexConsumerProvider)
	{
		Identifier identifier = new Identifier("tweaks", "textures/ui/text.png");

		// Wings scale as defined in the settings.
		float scale = 1.5F;
		//		double rotate = CosmeticsUtil.interpolate(player.prevRenderYawOffset, player.renderYawOffset, partialTicks);

		RenderSystem.pushMatrix();
		// Displaces the wings by a custom value.
		//		double customOffset = Settings.WINGS_OFFSET / 50;
		RenderSystem.translatef(0, 1, 0);
		//		RenderSystem.translatef(x, y, z);

		RenderSystem.scalef(-scale, -scale, scale);
		RenderSystem.rotatef((float) (180.0F), 0.0F, 1.0F, 0.0F);

		// Height of the player.
		float scaledPlayerHeight = (float) (1.85F / scale);

		// Height of the wings from the feet.
		float scaledHeight = (float) (1.25 / scale);

		// Moves the wings to the top of the player's head then backward slightly (away from the centre).
		RenderSystem.translatef(0.0F, -scaledHeight, 0.0F);
		RenderSystem.translatef(0.0F, 0.0F, 0.15F / scale);

		if (player.isSneaking())
		{
			RenderSystem.translatef(0.0F, (float) (0.125 / scale), 0.0F);
		}

		// Spinning rotate mode.
	/*	if (rotateState == 2)
		{
			// Translate to centre of the player.
			float difference = scaledHeight - (scaledPlayerHeight / 2);
			GlStateManager.translate(0.0F, difference, 0.0F);
			// Rotate.
			double l = System.currentTimeMillis() % (360 * 1.75) / 1.75;
			GlStateManager.rotate((float) -l, 0.1F, 0.0F, 0.0F);
			//Translate back up to the correct position.
			GlStateManager.translate(0.0F, -difference, 0.0F);
		} else if (rotateState == 1)
		{
			// Flip rotate mode.
			float difference = scaledPlayerHeight - scaledHeight;
			GlStateManager.rotate(180.0F, 0.0F, 0.0F, 1.0F);
			GlStateManager.translate(0.0F, -scaledHeight + difference, 0.0F);
		}*/

		RenderSystem.color3f(1.0F, 1.0F, 1.0F);

		MinecraftClient.getInstance().getTextureManager().bindTexture(identifier);
		//		RenderSystem.enableCull();
		//		RenderSystem.disableCull();
		//		RenderSystem.matrixMode(5890);
		//		RenderSystem.loadIdentity();

		for (int j = 0; j < 2; j++)
		{
			//			GL11.glEnable(GL11.GL_CULL_FACE);
			//			RenderSystem.disableCull();

			float f11 = System.currentTimeMillis() % 1000L / 1000.0f * 3.1415927f * 2.0F;
			//			wing.rotateAngleX = (float) Math.toRadians(-80.0) - (float) Math.cos(f11) * 0.2F;
			//			wing.rotateAngleY = (float) Math.toRadians(20.0) + (float) Math.sin(f11) * 0.4F;
			//			wing.rotateAngleZ = (float) Math.toRadians(20.0);
			//			wingTip.rotateAngleZ = -(float) (Math.sin(f11 + 2.0F) + 0.5) * 0.75F;
			VertexConsumer buffer = vertexConsumerProvider.getBuffer(RenderLayer.getEntityTranslucent(identifier));
			wing.render(matrixStack, buffer, light, OverlayTexture.DEFAULT_UV);
			RenderSystem.scalef(-1.0F, 1.0F, 1.0F);
			//			if (j == 0)
			//				GL11.glCullFace(GL11.GL_FRONT);
		}
		RenderSystem.color3f(0.0F, 1.0F, 1.0F);

		//		GL11.glCullFace(GL11.GL_BACK);
		//		GL11.glDisable(GL11.GL_CULL_FACE);

		//		RenderSystem.matrixMode(5888);
		RenderSystem.popMatrix();

	}

	private void renderWings2(AbstractClientPlayerEntity player, float partialTicks, int light, MatrixStack matrixStack,
	                          VertexConsumerProvider vertexConsumerProvider)
	{
		Identifier identifier = new Identifier("tweaks", "textures/ui/text.png");
		double rotate = (double) this.interpolate(player.headYaw, player.prevYaw, partialTicks);
		double scale = 0.75f;
		GL11.glPushMatrix();
		GL11.glScaled(-scale, -scale, -scale);
		GL11.glRotated(180 + rotate, 0, 1, 0);
		GL11.glTranslated(0, -1.45 / scale, 0);
		GL11.glTranslated(0.0D, 0.0D, 0.2D / scale);
		MinecraftClient.getInstance().getTextureManager().bindTexture(identifier);

		VertexConsumer buffer = vertexConsumerProvider.getBuffer(RenderLayer.getEntitySolid(identifier));

		for (int i = 0; i < 2; i++)
		{
			GL11.glEnable(/*GL11.GL_CULL_FACE*/2884);
			float f11 = System.currentTimeMillis() % 1000L / 1000.0f * 3.1415927f * 2.0F;
			this.wing.pivotX = (float) Math.toRadians(-80.0) - (float) Math.cos(f11) * 0.2F;
			this.wing.pivotY = (float) Math.toRadians(20.0) + (float) Math.sin(f11) * 0.4F;
			this.wing.pivotZ = (float) Math.toRadians(20.0);
			this.wingTip.pivotZ = -(float) (Math.sin(f11 + 2.0F) + 0.5) * 0.75F;
			this.wing.render(matrixStack, buffer, light, OverlayTexture.DEFAULT_UV);
			GL11.glScalef(-1, 1, 1);
			if (i == 0)
				GL11.glCullFace(/*GL11.GL_FRONT*/1028);
		}
		GL11.glCullFace(/*GL11.GL_BACK*/1029);
		GL11.glDisable(/*GL11.GL_CULL_FACE*/2884);
		GL11.glColor3f(255.0F, 255.0F, 255.0F);
		GL11.glPopMatrix();

	}

	private float interpolate(float bodyYaw, float prevBodyYaw, float partialTicks)
	{
		float f = (bodyYaw + (prevBodyYaw - bodyYaw) * partialTicks) % 360.0F;
		if (f < 0.0F)
		{
			f += 360.0F;
		}

		return f;
	}

	private void renderWings(AbstractClientPlayerEntity abstractClientPlayerEntity, int light, MatrixStack matrixStack,
	                         VertexConsumerProvider vertexConsumerProvider)
	{
		matrixStack.push();
		float scale = 1f;
		matrixStack.translate(0, 0.25, 0);

		matrixStack.scale(-scale, -scale, -scale);
		float scaledHeight = (float) (1.25 / scale);

		matrixStack.translate(0.0F, -scaledHeight / 2, 0.0F);
		matrixStack.translate(0.0F, 0.0F, -0.15F / scale);

		if (abstractClientPlayerEntity.isSneaking())
		{
			matrixStack.translate(0.0F, (float) (0.0125 / scale), 0.0F);
		}


		float difference = 1.85F / scale - scaledHeight;
		matrixStack.multiply(Vector3f.NEGATIVE_X.getRadialQuaternion(9.5F));

		matrixStack.translate(0.0F, -scaledHeight + difference, 0.0F);

		MinecraftClient.getInstance().getTextureManager().bindTexture(identifier);
		VertexConsumer buffer = vertexConsumerProvider.getBuffer(RenderLayer.getEntityTranslucent(identifier));
		//		if (abstractClientPlayerEntity.prevY != abstractClientPlayerEntity.getY())
		for (int j = 0; j < 2; j++)
		{
			float time = 1000;
			if (!abstractClientPlayerEntity.isOnGround())
				time = 800;

			float f11 = System.currentTimeMillis() % ((long) time) / time * 3.1415927f * 2.0F;
			//			if (abstractClientPlayerEntity.getX()!=abstractClientPlayerEntity.prevX)
			{

				wing.pitch = (float) Math.toRadians(-80.0) - (float) Math.cos(f11) * 0.2F;
				wing.yaw = (float) Math.toRadians(20.0) + (float) Math.sin(f11) * 0.4F;
				wing.roll = (float) Math.toRadians(20.0);
				wingTip.roll = -(float) (Math.sin(f11 + 2.0F) + 0.5) * 0.75F;
			}
			matrixStack.scale(-1.0F, 1.0F, 1.0F);
//			if (Config.isWingChroma())
//				wing.render(matrixStack, buffer, light, OverlayTexture.DEFAULT_UV, ColourHelper.rgb().getRed() / 255f,
//						ColourHelper.rgb().getGreen() / 255f, ColourHelper.rgb().getBlue() / 255f, 1);
//			else
			wing.render(matrixStack, buffer, light, OverlayTexture.DEFAULT_UV, Settings.wingRed / 255f, Settings.wingGreen / 255f,
					Settings.wingBlue / 255f, 1);

		}
		matrixStack.pop();

	}
}
