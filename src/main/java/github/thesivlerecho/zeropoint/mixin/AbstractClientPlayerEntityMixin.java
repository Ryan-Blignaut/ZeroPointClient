package github.thesivlerecho.zeropoint.mixin;

import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractClientPlayerEntity.class)
public abstract class AbstractClientPlayerEntityMixin
{
	@Inject(method = "getSkinTexture", at = @At("RETURN"), cancellable = true)
	private void getSkinTexture(CallbackInfoReturnable<@Nullable Identifier> cir)
	{
		//TODO: custom skin applier
//		if (SlideOut.skin != null)
//			callbackInfoReturnable.setReturnValue(SlideOut.skin);
	}
}
