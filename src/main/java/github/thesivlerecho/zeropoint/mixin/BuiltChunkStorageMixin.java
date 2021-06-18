package github.thesivlerecho.zeropoint.mixin;

import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Matrix4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WorldRenderer.class)
public class BuiltChunkStorageMixin
{
	private long time = -1;

	@Inject(method = "renderLayer", at = @At("HEAD"), cancellable = true)
	private void setOrigin(RenderLayer renderLayer, MatrixStack matrices, double d, double y, double f, Matrix4f matrix4f, CallbackInfo ci)
	{

//		if (time == -1)
//			time = System.currentTimeMillis();
//
//		long timeDif = System.currentTimeMillis() - time;
//
//
//		matrices.translate(0, 256 - y - (Math.tanh(timeDif / 2000f) * 256 - y), 0);

	}

}
