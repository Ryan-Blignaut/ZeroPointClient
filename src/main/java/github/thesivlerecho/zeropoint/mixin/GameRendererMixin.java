package github.thesivlerecho.zeropoint.mixin;

import com.mojang.blaze3d.systems.RenderSystem;
import github.thesivlerecho.zeropoint.event.EventManager;
import github.thesivlerecho.zeropoint.event.events.RenderShaderEvent;
import net.minecraft.client.render.GameRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameRenderer.class)
public abstract class GameRendererMixin
{
	@Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/WorldRenderer;drawEntityOutlinesFramebuffer()V", shift = At.Shift.AFTER))
	private void hookShaderRender(float tickDelta, long nanoTime, boolean renderLevel, CallbackInfo info)
	{
		EventManager.call(new RenderShaderEvent());
	}


	@Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/MinecraftClient;getFramebuffer()Lnet/minecraft/client/gl/Framebuffer;"))
	private void reEnableTexture(float tickDelta, long nanoTime, boolean renderLevel, CallbackInfo info)
	{
		RenderSystem.enableTexture();
	}

}