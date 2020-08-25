package me.thesilverecho.zeropoint.cosmetic;

// Made with Blockbench 3.6.5
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class DragonWings extends FeatureRenderer<AbstractClientPlayerEntity, PlayerEntityModel<AbstractClientPlayerEntity>>
{
	private final ModelPart bodyUpper;
	private final ModelPart armR2;
	private final ModelPart armL2;

	public DragonWings(FeatureRendererContext<AbstractClientPlayerEntity, PlayerEntityModel<AbstractClientPlayerEntity>> context)
	{
		super(context);
		int textureWidth = 256;
		int textureHeight = 128;

		bodyUpper = new ModelPart(textureWidth, textureHeight, 0, 0);
		bodyUpper.setPivot(0.0F, 11.6F, 2F);
		setRotationAngle(bodyUpper, -1.5708F, 0.0F, 0.0F);

		ModelPart spike5 = new ModelPart(textureWidth, textureHeight, 0, 0);
		spike5.setPivot(0.0F, -1.5F, 1.0F);
		bodyUpper.addChild(spike5);
		setRotationAngle(spike5, 0.5918F, 0.0F, 0.0F);
		spike5.setTextureOffset(34, 34).addCuboid(-0.5F, -0.5F, 0.0F, 1.0F, 1.0F, 2.0F, 0.0F, true);

		ModelPart spike6 = new ModelPart(textureWidth, textureHeight, 0, 0);
		spike6.setPivot(0.0F, -1.1F, 3.5F);
		bodyUpper.addChild(spike6);
		setRotationAngle(spike6, 0.6374F, 0.0F, 0.0F);
		spike6.setTextureOffset(22, 34).addCuboid(-0.5F, -0.5F, 0.0F, 1.0F, 1.0F, 3.0F, 0.0F, true);

		ModelPart spike7 = new ModelPart(textureWidth, textureHeight, 0, 0);
		spike7.setPivot(0.0F, -1.0F, 6.5F);
		bodyUpper.addChild(spike7);
		setRotationAngle(spike7, 0.5463F, 0.0F, 0.0F);
		spike7.setTextureOffset(22, 34).addCuboid(-0.5F, -0.5F, 0.0F, 1.0F, 1.0F, 3.0F, 0.0F, false);

		ModelPart armL1 = new ModelPart(textureWidth, textureHeight, 0, 0);
		armL1.setPivot(-3.7F, -0.7F, 3.0F);
		bodyUpper.addChild(armL1);
		setRotationAngle(armL1, 0.1745F, 0.0456F, -1.5359F);
		armL1.setTextureOffset(170, 31).addCuboid(-1.0F, -1.5F, -2.0F, 2.0F, 13.0F, 4.0F, 0.0F, false);

		armL2 = new ModelPart(textureWidth, textureHeight, 0, 0);
		armL2.setPivot(0.1F, 11.3F, -0.1F);
		armL1.addChild(armL2);
		setRotationAngle(armL2, -0.7741F, 0.0F, 0.0F);
		armL2.setTextureOffset(189, 27).addCuboid(-1.0F, -1.5F, -1.4F, 2.0F, 21.0F, 3.0F, 0.0F, false);

		ModelPart fingerL11 = new ModelPart(textureWidth, textureHeight, 0, 0);
		fingerL11.setPivot(0.5F, 19.3F, -0.1F);
		armL2.addChild(fingerL11);
		setRotationAngle(fingerL11, 0.576F, 0.0F, 0.0F);
		fingerL11.setTextureOffset(145, 23).addCuboid(-0.5F, -0.5F, -1.0F, 1.0F, 18.0F, 2.0F, 0.0F, false);

		ModelPart fingerL12 = new ModelPart(textureWidth, textureHeight, 0, 0);
		fingerL12.setPivot(0.0F, 17.4F, 0.1F);
		fingerL11.addChild(fingerL12);
		setRotationAngle(fingerL12, 0.1745F, 0.0F, 0.0F);
		fingerL12.setTextureOffset(152, 23).addCuboid(-0.48F, -0.5F, -1.0F, 1.0F, 17.0F, 2.0F, 0.0F, false);

		ModelPart membraneL1 = new ModelPart(textureWidth, textureHeight, 0, 0);
		membraneL1.setPivot(0.5F, 0.4F, -0.5F);
		fingerL11.addChild(membraneL1);
		setRotationAngle(membraneL1, 0.0911F, 0.0F, 0.0F);
		membraneL1.setTextureOffset(54, 73).addCuboid(-0.5F, -1.0F, 0.0F, 0.0F, 33.0F, 18.0F, 0.0F, false);

		ModelPart fingerL21 = new ModelPart(textureWidth, textureHeight, 0, 0);
		fingerL21.setPivot(0.3F, 18.6F, -0.1F);
		armL2.addChild(fingerL21);
		setRotationAngle(fingerL21, 1.1383F, 0.0F, 0.0F);
		fingerL21.setTextureOffset(145, 23).addCuboid(-0.5F, -0.5F, -1.0F, 1.0F, 17.0F, 2.0F, 0.0F, false);

		ModelPart fingerL22 = new ModelPart(textureWidth, textureHeight, 0, 0);
		fingerL22.setPivot(0.0F, 16.4F, 0.1F);
		fingerL21.addChild(fingerL22);
		setRotationAngle(fingerL22, 0.1745F, 0.0F, 0.0F);
		fingerL22.setTextureOffset(160, 23).addCuboid(-0.48F, -0.5F, -1.0F, 1.0F, 16.0F, 2.0F, 0.0F, false);

		ModelPart membraneL2 = new ModelPart(textureWidth, textureHeight, 0, 0);
		membraneL2.setPivot(0.5F, 0.4F, -0.9F);
		fingerL21.addChild(membraneL2);
		setRotationAngle(membraneL2, 0.0911F, 0.0F, 0.0F);
		membraneL2.setTextureOffset(54, 75).addCuboid(-0.5F, -0.5F, 0.0F, 0.0F, 32.0F, 18.0F, 0.0F, false);

		ModelPart fingerL31 = new ModelPart(textureWidth, textureHeight, 0, 0);
		fingerL31.setPivot(0.2F, 18.3F, -0.1F);
		armL2.addChild(fingerL31);
		setRotationAngle(fingerL31, 1.7301F, 0.0F, 0.0F);
		fingerL31.setTextureOffset(145, 23).addCuboid(-0.5F, -0.5F, -1.0F, 1.0F, 16.0F, 2.0F, 0.0F, false);

		ModelPart fingerL32 = new ModelPart(textureWidth, textureHeight, 0, 0);
		fingerL32.setPivot(0.0F, 15.4F, 0.1F);
		fingerL31.addChild(fingerL32);
		setRotationAngle(fingerL32, 0.1745F, 0.0F, 0.0F);
		fingerL32.setTextureOffset(151, 52).addCuboid(-0.48F, -0.5F, -1.0F, 1.0F, 15.0F, 2.0F, 0.0F, false);

		ModelPart membraneL3 = new ModelPart(textureWidth, textureHeight, 0, 0);
		membraneL3.setPivot(0.5F, 0.2F, -1.1F);
		fingerL31.addChild(membraneL3);
		setRotationAngle(membraneL3, 0.0911F, 0.0F, 0.0F);
		membraneL3.setTextureOffset(54, 77).addCuboid(-0.5F, -0.5F, 0.0F, 0.0F, 30.0F, 18.0F, 0.0F, false);

		ModelPart fingerL41 = new ModelPart(textureWidth, textureHeight, 0, 0);
		fingerL41.setPivot(0.1F, 18.3F, -0.1F);
		armL2.addChild(fingerL41);
		setRotationAngle(fingerL41, 2.322F, 0.0F, 0.0F);
		fingerL41.setTextureOffset(145, 23).addCuboid(-0.5F, -0.5F, -1.0F, 1.0F, 16.0F, 2.0F, 0.0F, false);

		ModelPart fingerL42 = new ModelPart(textureWidth, textureHeight, 0, 0);
		fingerL42.setPivot(0.0F, 15.4F, 0.1F);
		fingerL41.addChild(fingerL42);
		setRotationAngle(fingerL42, 0.1745F, 0.0F, 0.0F);
		fingerL42.setTextureOffset(151, 52).addCuboid(-0.48F, -0.5F, -1.0F, 1.0F, 15.0F, 2.0F, 0.0F, false);

		ModelPart membraneL4 = new ModelPart(textureWidth, textureHeight, 0, 0);
		membraneL4.setPivot(0.5F, 0.2F, -1.1F);
		fingerL41.addChild(membraneL4);
		setRotationAngle(membraneL4, 0.0873F, 0.0F, 0.0F);
		membraneL4.setTextureOffset(54, 77).addCuboid(-0.5F, -0.5F, 0.0F, 0.0F, 30.0F, 18.0F, 0.0F, false);

		ModelPart membraneL5 = new ModelPart(textureWidth, textureHeight, 0, 0);
		membraneL5.setPivot(0.5F, 17.8F, 2.0F);
		armL2.addChild(membraneL5);
		setRotationAngle(membraneL5, 2.5042F, 0.0F, 0.0F);
		membraneL5.setTextureOffset(106, 73).addCuboid(-0.5F, -0.5F, 0.0F, 0.0F, 27.0F, 18.0F, 0.0F, false);

		ModelPart clawL = new ModelPart(textureWidth, textureHeight, 0, 0);
		clawL.setPivot(-0.1F, 19.0F, -0.1F);
		armL2.addChild(clawL);
		setRotationAngle(clawL, 0.0F, -0.6981F, 1.5708F);
		clawL.setTextureOffset(128, 44).addCuboid(-1.7F, -0.8F, -0.5F, 2.0F, 2.0F, 1.0F, 0.0F, false);

		ModelPart clawL2 = new ModelPart(textureWidth, textureHeight, 0, 0);
		clawL2.setPivot(-2.0F, 0.0F, 0.0F);
		clawL.addChild(clawL2);
		clawL2.setTextureOffset(128, 52).addCuboid(-1.7F, -0.8F, -0.5F, 2.0F, 2.0F, 1.0F, 0.0F, false);

		ModelPart clawL3 = new ModelPart(textureWidth, textureHeight, 0, 0);
		clawL3.setPivot(0.0F, 0.0F, 0.0F);
		clawL2.addChild(clawL3);
		clawL3.setTextureOffset(128, 61).addCuboid(-1.2F, 0.7F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);

		ModelPart membraneL6 = new ModelPart(textureWidth, textureHeight, 0, 0);
		membraneL6.setPivot(0.57F, -1.9F, 2.5F);
		armL1.addChild(membraneL6);
		setRotationAngle(membraneL6, -1.457F, 0.0F, 0.0F);
		membraneL6.setTextureOffset(156, 82).addCuboid(-0.5F, -8.8F, 0.8F, 0.0F, 11.0F, 13.0F, 0.0F, false);

		ModelPart armR1 = new ModelPart(textureWidth, textureHeight, 0, 0);
		armR1.setPivot(3.7F, -0.7F, 3.0F);
		bodyUpper.addChild(armR1);
		setRotationAngle(armR1, 0.1745F, -0.0456F, 1.5359F);
		armR1.setTextureOffset(170, 31).addCuboid(-1.0F, -1.5F, -2.0F, 2.0F, 13.0F, 4.0F, 0.0F, true);

		armR2 = new ModelPart(textureWidth, textureHeight, 0, 0);
		armR2.setPivot(-0.1F, 11.3F, -0.1F);
		armR1.addChild(armR2);
		setRotationAngle(armR2, -0.7741F, 0.0F, 0.0F);
		armR2.setTextureOffset(189, 27).addCuboid(-1.0F, -1.5F, -1.4F, 2.0F, 21.0F, 3.0F, 0.0F, true);

		ModelPart fingerR11 = new ModelPart(textureWidth, textureHeight, 0, 0);
		fingerR11.setPivot(-0.5F, 19.3F, -0.1F);
		armR2.addChild(fingerR11);
		setRotationAngle(fingerR11, 0.576F, 0.0F, 0.0F);
		fingerR11.setTextureOffset(145, 23).addCuboid(-0.5F, -0.5F, -1.0F, 1.0F, 18.0F, 2.0F, 0.0F, true);

		ModelPart fingerR2 = new ModelPart(textureWidth, textureHeight, 0, 0);
		fingerR2.setPivot(0.0F, 17.4F, 0.1F);
		fingerR11.addChild(fingerR2);
		setRotationAngle(fingerR2, 0.1745F, 0.0F, 0.0F);
		fingerR2.setTextureOffset(152, 23).addCuboid(-0.5F, -0.5F, -1.0F, 1.0F, 17.0F, 2.0F, 0.0F, true);

		ModelPart membraneR1 = new ModelPart(textureWidth, textureHeight, 0, 0);
		membraneR1.setPivot(-0.5F, 0.4F, -0.5F);
		fingerR11.addChild(membraneR1);
		setRotationAngle(membraneR1, 0.0911F, 0.0F, 0.0F);
		membraneR1.setTextureOffset(54, 73).addCuboid(0.5F, -1.0F, 0.0F, 0.0F, 33.0F, 18.0F, 0.0F, true);

		ModelPart fingerR21 = new ModelPart(textureWidth, textureHeight, 0, 0);
		fingerR21.setPivot(-0.3F, 18.6F, -0.1F);
		armR2.addChild(fingerR21);
		setRotationAngle(fingerR21, 1.1383F, 0.0F, 0.0F);
		fingerR21.setTextureOffset(145, 23).addCuboid(-0.5F, -0.5F, -1.0F, 1.0F, 17.0F, 2.0F, 0.0F, true);

		ModelPart fingerR22 = new ModelPart(textureWidth, textureHeight, 0, 0);
		fingerR22.setPivot(0.0F, 16.4F, 0.1F);
		fingerR21.addChild(fingerR22);
		setRotationAngle(fingerR22, 0.1745F, 0.0F, 0.0F);
		fingerR22.setTextureOffset(160, 23).addCuboid(-0.52F, -0.5F, -1.0F, 1.0F, 16.0F, 2.0F, 0.0F, true);

		ModelPart membraneR2 = new ModelPart(textureWidth, textureHeight, 0, 0);
		membraneR2.setPivot(-0.5F, 0.4F, -0.9F);
		fingerR21.addChild(membraneR2);
		setRotationAngle(membraneR2, 0.0911F, 0.0F, 0.0F);
		membraneR2.setTextureOffset(54, 75).addCuboid(0.5F, -0.5F, 0.0F, 0.0F, 32.0F, 18.0F, 0.0F, true);

		ModelPart fingerR31 = new ModelPart(textureWidth, textureHeight, 0, 0);
		fingerR31.setPivot(-0.2F, 18.3F, -0.1F);
		armR2.addChild(fingerR31);
		setRotationAngle(fingerR31, 1.7301F, 0.0F, 0.0F);
		fingerR31.setTextureOffset(145, 23).addCuboid(-0.5F, -0.5F, -1.0F, 1.0F, 16.0F, 2.0F, 0.0F, true);

		ModelPart fingerR32 = new ModelPart(textureWidth, textureHeight, 0, 0);
		fingerR32.setPivot(0.0F, 15.4F, 0.1F);
		fingerR31.addChild(fingerR32);
		setRotationAngle(fingerR32, 0.1745F, 0.0F, 0.0F);
		fingerR32.setTextureOffset(151, 52).addCuboid(-0.52F, -0.5F, -1.0F, 1.0F, 15.0F, 2.0F, 0.0F, true);

		ModelPart membraneR3 = new ModelPart(textureWidth, textureHeight, 0, 0);
		membraneR3.setPivot(-0.5F, 0.2F, -1.1F);
		fingerR31.addChild(membraneR3);
		setRotationAngle(membraneR3, 0.0911F, 0.0F, 0.0F);
		membraneR3.setTextureOffset(54, 77).addCuboid(0.5F, -0.5F, 0.0F, 0.0F, 30.0F, 18.0F, 0.0F, true);

		ModelPart fingerR41 = new ModelPart(textureWidth, textureHeight, 0, 0);
		fingerR41.setPivot(-0.1F, 18.3F, -0.1F);
		armR2.addChild(fingerR41);
		setRotationAngle(fingerR41, 2.322F, 0.0F, 0.0F);
		fingerR41.setTextureOffset(145, 23).addCuboid(-0.5F, -0.5F, -1.0F, 1.0F, 16.0F, 2.0F, 0.0F, true);

		ModelPart fingerR42 = new ModelPart(textureWidth, textureHeight, 0, 0);
		fingerR42.setPivot(0.0F, 15.4F, 0.1F);
		fingerR41.addChild(fingerR42);
		setRotationAngle(fingerR42, 0.1745F, 0.0F, 0.0F);
		fingerR42.setTextureOffset(151, 52).addCuboid(-0.52F, -0.5F, -1.0F, 1.0F, 15.0F, 2.0F, 0.0F, true);

		ModelPart membraneR4 = new ModelPart(textureWidth, textureHeight, 0, 0);
		membraneR4.setPivot(-0.5F, 0.2F, -1.1F);
		fingerR41.addChild(membraneR4);
		setRotationAngle(membraneR4, 0.0873F, 0.0F, 0.0F);
		membraneR4.setTextureOffset(54, 77).addCuboid(0.5F, -0.5F, 0.0F, 0.0F, 30.0F, 18.0F, 0.0F, true);

		ModelPart membraneR5 = new ModelPart(textureWidth, textureHeight, 0, 0);
		membraneR5.setPivot(-0.5F, 17.8F, 2.0F);
		armR2.addChild(membraneR5);
		setRotationAngle(membraneR5, 2.5042F, 0.0F, 0.0F);
		membraneR5.setTextureOffset(106, 73).addCuboid(0.5F, -0.5F, 0.0F, 0.0F, 27.0F, 18.0F, 0.0F, true);

		ModelPart clawR = new ModelPart(textureWidth, textureHeight, 0, 0);
		clawR.setPivot(0.1F, 19.0F, -0.1F);
		armR2.addChild(clawR);
		setRotationAngle(clawR, 3.1416F, -0.6981F, 1.5708F);
		clawR.setTextureOffset(128, 44).addCuboid(-1.7F, -0.8F, -0.5F, 2.0F, 2.0F, 1.0F, 0.0F, false);

		ModelPart clawR2 = new ModelPart(textureWidth, textureHeight, 0, 0);
		clawR2.setPivot(-2.0F, 0.0F, 0.0F);
		clawR.addChild(clawR2);
		clawR2.setTextureOffset(128, 52).addCuboid(-1.7F, -0.8F, -0.5F, 2.0F, 2.0F, 1.0F, 0.0F, false);

		ModelPart clawR3 = new ModelPart(textureWidth, textureHeight, 0, 0);
		clawR3.setPivot(0.0F, 0.0F, 0.0F);
		clawR2.addChild(clawR3);
		clawR3.setTextureOffset(128, 61).addCuboid(-1.2F, 0.7F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);

		ModelPart membraneR6 = new ModelPart(textureWidth, textureHeight, 0, 0);
		membraneR6.setPivot(-0.57F, -1.9F, 2.5F);
		armR1.addChild(membraneR6);
		setRotationAngle(membraneR6, -1.457F, 0.0F, 0.0F);
		membraneR6.setTextureOffset(156, 82).addCuboid(0.5F, -8.8F, 0.8F, 0.0F, 11.0F, 13.0F, 0.0F, true);
	}

	public void setRotationAngle(ModelPart ModelPart, float x, float y, float z)
	{
		ModelPart.pitch = x;
		ModelPart.yaw = y;
		ModelPart.roll = z;
	}

	@Override public void render(MatrixStack matrixStack, VertexConsumerProvider vertexConsumers, int light, AbstractClientPlayerEntity entity,
			float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch)
	{
		Identifier identifier = new Identifier("zero-point", "textures/cosmetic/white_3.png");

		matrixStack.push();
		float scale = .5f;
		matrixStack.scale(scale, scale, scale);

		float scaledHeight = (float) (1.25 / scale);
		//		matrixStack.multiply(Vector3f.POSITIVE_X.getDegreesQuaternion(90));
	/*	matrixStack.translate(0, 0.2, 0.50);

		matrixStack.translate(0.0F, -scaledHeight / 2, 0.0F);
		matrixStack.translate(0.0F, 0.0F, -0.15F / scale);*/

		matrixStack.translate(0, -scaledHeight / 2, 0);
		matrixStack.translate(0, 0, 0.15 / scale);
		if (entity.isSneaking())
		{
			matrixStack.translate(0.0F, (float) (0.0125 / scale), 0.0F);
		}

		MinecraftClient.getInstance().getTextureManager().bindTexture(identifier);
		float time = 1000;
		float f11 = System.currentTimeMillis() % ((long) time) / time * 3.1415927f * 2.0F;

		/*bodyUpper.pitch = (float) Math.toRadians(-80.0) - (float) Math.cos(f11) * 0.2F;
		bodyUpper.yaw = (float) Math.toRadians(20.0) + (float) Math.sin(f11) * 0.4F;
		bodyUpper.roll = (float) Math.toRadians(20.0);*/
		//			wingTip.roll = -(float) (Math.sin(f11 + 2.0F) + 0.5) * 0.75F;
		//		matrixStack.scale(-1.0F, 1.0F, 1.0F);

		float roll = -(float) (Math.sin(f11 + 2.0F) + 0.5) * 0.75F;
		armR2.roll = roll;
		armL2.roll = -roll;
		bodyUpper.render(matrixStack, vertexConsumers.getBuffer(RenderLayer.getEntityTranslucent(identifier)), light, OverlayTexture.DEFAULT_UV);

		matrixStack.pop();

	}
}
