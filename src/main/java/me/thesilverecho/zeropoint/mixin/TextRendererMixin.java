package me.thesilverecho.zeropoint.mixin;

import me.thesilverecho.zeropoint.ZeroPointClient;
import me.thesilverecho.zeropoint.gui.font.GlyphPageFontRenderer;
import net.minecraft.client.font.FontStorage;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Function;

@Mixin(TextRenderer.class)
public class TextRendererMixin
{
	@Inject(method = "<init>", at = @At(value = "RETURN"))
	private void x(Function<Identifier, FontStorage> fontStorageAccessor, CallbackInfo ci)
	{
		ZeroPointClient.RENDERER = GlyphPageFontRenderer.create("SF Mono", 12, false, false, false);
	}


}
