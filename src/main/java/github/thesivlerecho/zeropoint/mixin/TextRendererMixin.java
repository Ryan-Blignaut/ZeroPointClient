package github.thesivlerecho.zeropoint.mixin;

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
//		ZeroPointClient.RENDERER = GlyphPageFontRenderer.create("SF Mono", 15, false, false, false);

//		ThreadPoolExecutor executorService = (ThreadPoolExecutor) Executors.newFixedThreadPool(8);
//		ConcurrentLinkedQueue<ResourceTexture.TextureData> textureQueue = new ConcurrentLinkedQueue<>();
//		ZeroPointClient.fontRenderer = new TTFFontRenderer(executorService, textureQueue, new Font("Verdana", Font.PLAIN, 18));

//		ZeroPointClient.font = new FontLoader(Font.decode("Cascadia Code Regular").deriveFont(15f), true, true);
//		final char[] chars = new char[255];
//		for (int i = 0; i < chars.length; i++)
//		{
//			chars[i] = ((char) i);
//		}
//		ZeroPointClient.font.generatePage(chars);
//		ZeroPointClient.font.loadFontImage();


	}


}
