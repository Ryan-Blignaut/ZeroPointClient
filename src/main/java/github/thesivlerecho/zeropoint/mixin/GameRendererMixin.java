package github.thesivlerecho.zeropoint.mixin;

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
		/*final BlurPostprocessShader shader1 = ShaderManager.getShader(BlurPostprocessShader.class, ZeroPointShader.BLUR);
		final Framebuffer framebuffer = MinecraftClient.getInstance().getFramebuffer();
		shader1.setUp(framebuffer, framebuffer, 12, new Vec2f(1, 1));
		shader1.setUp(framebuffer, framebuffer, 12, new Vec2f(1, 0));
		shader1.draw1();*/
	}


	@Inject(
			at = @At(value = "INVOKE", target = "Lnet/minecraft/client/MinecraftClient;getFramebuffer()Lnet/minecraft/client/gl/Framebuffer;"),
			method = "render"
	)
	private void fixMojankGl(float tickDelta, long nanoTime, boolean renderLevel, CallbackInfo info)
	{
//		RenderSystem.enableTexture();
	}

}