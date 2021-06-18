package github.thesivlerecho.zeropoint.mixin;

import github.thesivlerecho.zeropoint.render.font.CustomFonts;
import net.minecraft.client.font.FontStorage;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Function;

@Mixin(TextRenderer.class)
public abstract class TextRendererMixin
{
	@Inject(method = "<init>", at = @At(value = "RETURN"))
	private void x(Function<Identifier, FontStorage> fontStorageAccessor, CallbackInfo ci)
	{
		for (CustomFonts value : CustomFonts.values())
			value.initFont();

	}


}
