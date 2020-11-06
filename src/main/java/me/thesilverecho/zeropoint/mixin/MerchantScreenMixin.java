package me.thesilverecho.zeropoint.mixin;

import net.minecraft.client.gui.screen.ingame.MerchantScreen;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(MerchantScreen.class) public abstract class MerchantScreenMixin
{
	/*@Inject(method = "render", at = @At("INVOKE"), cancellable = true) private void setPosition1(int x, int y, int z,
			CallbackInfoReturnable<Integer> cir)
	{
	}*/
}
