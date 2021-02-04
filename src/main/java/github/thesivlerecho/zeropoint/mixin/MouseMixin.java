package github.thesivlerecho.zeropoint.mixin;

import github.thesivlerecho.zeropoint.mod.ModPerspective;
import net.minecraft.client.Mouse;
import net.minecraft.client.network.ClientPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(Mouse.class)
public abstract class MouseMixin
{
	@Inject(method = "updateMouse", at = @At(value = "INVOKE", target = "net/minecraft/client/tutorial/TutorialManager.onUpdateMouse(DD)V"), locals = LocalCapture.CAPTURE_FAILEXCEPTION)
	private void perspectiveUpdatePitchYaw(CallbackInfo info, double adjustedSens, double x, double y, int invert)
	{
		if (ModPerspective.perspectiveEnabled)
		{
			ModPerspective.cameraYaw += x / 8.0F;
			ModPerspective.cameraPitch += (y * invert) / 8.0F;

			if (Math.abs(ModPerspective.cameraPitch) > 90.0F)
				ModPerspective.cameraPitch = ModPerspective.cameraPitch > 0.0F ? 90.0F : -90.0F;
		}
	}

	@Redirect(
			method = "updateMouse", at = @At(value = "INVOKE", target = "net/minecraft/client/network/ClientPlayerEntity.changeLookDirection(DD)V"))
	private void perspectivePreventPlayerMovement(ClientPlayerEntity player, double x, double y)
	{
		if (!ModPerspective.perspectiveEnabled)
			player.changeLookDirection(x, y);
	}

}
