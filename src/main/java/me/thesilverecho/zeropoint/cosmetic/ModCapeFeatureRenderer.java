package me.thesilverecho.zeropoint.cosmetic;

import me.thesilverecho.zeropoint.ZeroPointClient;
import me.thesilverecho.zeropoint.gui.ModRenderLayer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.*;
import net.minecraft.client.render.entity.PlayerModelPart;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.util.math.Vector3f;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

public class ModCapeFeatureRenderer extends FeatureRenderer<AbstractClientPlayerEntity, PlayerEntityModel<AbstractClientPlayerEntity>>
{

	private final ModelPart cape;
	private final ModelPart capebit1;
	private final ModelPart capebit2;

	public ModCapeFeatureRenderer(FeatureRendererContext<AbstractClientPlayerEntity, PlayerEntityModel<AbstractClientPlayerEntity>> context)
	{
		super(context);

		this.cape = new ModelPart(22, 23, 0, 0);
		this.cape.addCuboid(-5.0F, 0.0F, -1.0F, 10.0F, 8.0F, 1.0F);
		ModelPart capeHalf = new ModelPart(22, 23, 0, 8);
		capeHalf.addCuboid(-5.0F, 8.0F, -1.0F, 10.0F, 8.0F, 1.0F);

		capebit1 = (new ModelPart(22, 23, 0, 17).addCuboid(3, -0.5F, -1, 2, 1, 5));
		capebit2 = (new ModelPart(22, 23, 0, 17).addCuboid(-5, -0.5F, -1, 2, 1, 5));

		cape.addChild(capeHalf);

	}

	@Override
	public void render(MatrixStack matrixStack, VertexConsumerProvider vertexConsumers, int light, AbstractClientPlayerEntity abstractClientPlayerEntity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch)
	{
		if (abstractClientPlayerEntity.canRenderCapeTexture() && !abstractClientPlayerEntity.isInvisible() && abstractClientPlayerEntity.isPartVisible(PlayerModelPart.CAPE) && ZeroPointClient.PLAYER_COSMETICS.containsKey(MinecraftClient.getInstance().player.getUuid()))
		{
			ItemStack itemStack = abstractClientPlayerEntity.getEquippedStack(EquipmentSlot.CHEST);
			if (itemStack.getItem() != Items.ELYTRA)
			{
				matrixStack.push();
				matrixStack.translate(0.0D, 0.0D, 0.125D);
				double d = MathHelper.lerp(tickDelta, abstractClientPlayerEntity.prevCapeX, abstractClientPlayerEntity.capeX) - MathHelper.lerp(tickDelta, abstractClientPlayerEntity.prevX, abstractClientPlayerEntity.getX());
				double e = MathHelper.lerp(tickDelta, abstractClientPlayerEntity.prevCapeY, abstractClientPlayerEntity.capeY) - MathHelper.lerp(tickDelta, abstractClientPlayerEntity.prevY, abstractClientPlayerEntity.getY());
				double m = MathHelper.lerp(tickDelta, abstractClientPlayerEntity.prevCapeZ, abstractClientPlayerEntity.capeZ) - MathHelper.lerp(tickDelta, abstractClientPlayerEntity.prevZ, abstractClientPlayerEntity.getZ());
				float n = abstractClientPlayerEntity.prevBodyYaw + (abstractClientPlayerEntity.bodyYaw - abstractClientPlayerEntity.prevBodyYaw);
				double o = MathHelper.sin(n * 0.017453292F);
				double p = -MathHelper.cos(n * 0.017453292F);
				float q = (float) e * 10.0F;
				q = MathHelper.clamp(q, -6.0F, 32.0F);
				float r = (float) (d * o + m * p) * 100.0F;
				r = MathHelper.clamp(r, 0.0F, 150.0F);
				float s = (float) (d * p - m * o) * 100.0F;
				s = MathHelper.clamp(s, -20.0F, 20.0F);
				if (r < 0.0F)
				{
					r = 0.0F;
				}

				float t = MathHelper.lerp(tickDelta, abstractClientPlayerEntity.prevStrideDistance, abstractClientPlayerEntity.strideDistance);
				q += MathHelper.sin(MathHelper.lerp(tickDelta, abstractClientPlayerEntity.prevHorizontalSpeed, abstractClientPlayerEntity.horizontalSpeed) * 6.0F) * 32.0F * t;
				if (abstractClientPlayerEntity.isInSneakingPose())
				{
					q += 25.0F;
				}

				matrixStack.multiply(Vector3f.POSITIVE_X.getDegreesQuaternion(6.0F + r / 2.0F + q));
				matrixStack.multiply(Vector3f.POSITIVE_Z.getDegreesQuaternion(s / 2.0F));
				matrixStack.multiply(Vector3f.POSITIVE_Y.getDegreesQuaternion(180.0F - s / 2.0F));
				VertexConsumer vertexConsumer = VertexConsumers.dual(vertexConsumers.getBuffer(ModRenderLayer.EXTREME_OVER), vertexConsumers.getBuffer(RenderLayer.getEntitySolid(new Identifier("zero-point", "textures/cosmetic/" + ZeroPointClient.PLAYER_COSMETICS.get(MinecraftClient.getInstance().player.getUuid()).getEquippedCape() + ".png"))));
//				this.getContextModel().renderCape(matrixStack, vertexConsumer, light, OverlayTexture.DEFAULT_UV);
				cape.render(matrixStack, vertexConsumer, light, OverlayTexture.DEFAULT_UV);
				capebit1.render(matrixStack, vertexConsumer, light, OverlayTexture.DEFAULT_UV);
				capebit2.render(matrixStack, vertexConsumer, light, OverlayTexture.DEFAULT_UV);
				matrixStack.pop();
			}
		}

	}
}
