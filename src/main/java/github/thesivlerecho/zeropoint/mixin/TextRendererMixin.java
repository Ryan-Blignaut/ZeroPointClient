package github.thesivlerecho.zeropoint.mixin;

import github.thesivlerecho.zeropoint.ZeroPointClient;
import github.thesivlerecho.zeropoint.gui.font.FontLoader;
import github.thesivlerecho.zeropoint.gui.font.GlyphPageFontRenderer;
import net.minecraft.client.font.FontStorage;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.awt.*;
import java.util.function.Function;

@Mixin(TextRenderer.class)
public abstract class TextRendererMixin
{
	@Inject(method = "<init>", at = @At(value = "RETURN"))
	private void x(Function<Identifier, FontStorage> fontStorageAccessor, CallbackInfo ci)
	{
		ZeroPointClient.RENDERER = GlyphPageFontRenderer.create("SF Mono", 15, false, false, false);

		ZeroPointClient.FONT = new FontLoader(Font.decode("Cascadia Code Regular").deriveFont(15f), true, true);
		final char[] chars = new char[255];
		for (int i = 0; i < chars.length; i++)
		{
			chars[i] = ((char) i);
		}
		ZeroPointClient.FONT.generatePage(chars);
		ZeroPointClient.FONT.loadFontImage();

	}


}
