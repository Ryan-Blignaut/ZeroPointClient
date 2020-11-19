package me.thesilverecho.zeropoint.mixin;

import me.thesilverecho.zeropoint.gui.screen.SlideOut;
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

		if (SlideOut.skin != null)
			cir.setReturnValue(SlideOut.skin);
//		cir.setReturnValue(new Identifier(ZeroPointClient.MOD_ID, "textures/cosmetic/a.png"));
//		Identifier loc = FabricOfflineSkins.getLocationCape(getGameProfile(), info.getReturnValue());
//		if (loc != null)
//			info.setReturnValue(loc);
	}
}
