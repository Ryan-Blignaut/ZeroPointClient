package github.thesivlerecho.zeropoint.cosmetic;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.util.math.MatrixStack;

public class DWings extends FeatureRenderer<AbstractClientPlayerEntity, PlayerEntityModel<AbstractClientPlayerEntity>>
{
	public ModelPart rightBaseStem;
	public ModelPart leftBaseStem;
	public ModelPart rightOuterStem;
	public ModelPart rightWingInner;
	public ModelPart rightWingOuter;
	public ModelPart leftOuterStem;
	public ModelPart leftWingInner;
	public ModelPart leftWingOuter;

	public DWings(FeatureRendererContext<AbstractClientPlayerEntity, PlayerEntityModel<AbstractClientPlayerEntity>> context)
	{
		super(context);
//		this.leftWingOuter = new ModelPart(this, 0, 18);

	}

	@Override
	public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, AbstractClientPlayerEntity entity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch)
	{

	}
}
