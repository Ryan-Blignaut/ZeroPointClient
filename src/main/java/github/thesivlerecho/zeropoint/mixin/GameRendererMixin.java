package github.thesivlerecho.zeropoint.mixin;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.render.GameRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameRenderer.class)
public abstract class GameRendererMixin
{
	@Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/WorldRenderer;drawEntityOutlinesFramebuffer()V", shift = At.Shift.AFTER), method = "render")
	private void hookShaderRender(float tickDelta, long nanoTime, boolean renderLevel, CallbackInfo info)
	{
//		final ContrastPostprocessShader shader = ShaderManager.getShader(ContrastPostprocessShader.class, ZeroPointShader.CONTRAST_POST_P);
//		shader.draw();

	}

	@Inject(
			at = @At(value = "INVOKE", target = "Lnet/minecraft/client/MinecraftClient;getFramebuffer()Lnet/minecraft/client/gl/Framebuffer;"),
			method = "render"
	)
	private void fixMojankGl(float tickDelta, long nanoTime, boolean renderLevel, CallbackInfo info)
	{
		RenderSystem.enableTexture();
	}

}