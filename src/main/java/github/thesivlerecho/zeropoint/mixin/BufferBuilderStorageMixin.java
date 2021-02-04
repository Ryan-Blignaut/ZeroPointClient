package github.thesivlerecho.zeropoint.mixin;

import github.thesivlerecho.zeropoint.gui.ModRenderLayer;
import net.minecraft.client.render.BufferBuilder;
import net.minecraft.client.render.BufferBuilderStorage;
import net.minecraft.client.render.RenderLayer;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.SortedMap;

@Mixin(BufferBuilderStorage.class)
public abstract class BufferBuilderStorageMixin
{

	@Shadow
	@Final
	private SortedMap<RenderLayer, BufferBuilder> entityBuilders;

	@Inject(method = "<init>", at = @At("TAIL"))
	protected void add(CallbackInfo ci)
	{
		entityBuilders.put(ModRenderLayer.POT_OVERLAY, new BufferBuilder(ModRenderLayer.POT_OVERLAY.getExpectedBufferSize()));
		entityBuilders.put(ModRenderLayer.EXTREME_OVER, new BufferBuilder(ModRenderLayer.EXTREME_OVER.getExpectedBufferSize()));

	}

}

