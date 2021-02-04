package github.thesivlerecho.zeropoint.mixin;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BipedEntityModel.class)
public abstract class BipedEntityModelMixin
{

	@Shadow
	public ModelPart head;

	@Shadow
	public ModelPart rightArm;

	@Shadow
	public ModelPart leftArm;

	@Inject(method = "setAngles", at = @At("INVOKE"), cancellable = true)
	private void setPosition1(LivingEntity livingEntity, float f, float g, float h,
	                          float i, float j, CallbackInfo ci)
	{
	/*	this.head.pitch = 0.5f;
		this.head.yaw = 0.75f;
		this.rightArm.pitch = 4.75f;
		this.rightArm.yaw = -1.0f;
		this.leftArm.pitch = 4.5f;
		this.leftArm.yaw = -1.25f;*/

	}
}
