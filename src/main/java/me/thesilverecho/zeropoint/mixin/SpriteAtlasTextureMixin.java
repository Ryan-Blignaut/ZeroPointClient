package me.thesilverecho.zeropoint.mixin;

import me.thesilverecho.zeropoint.registration.ColourEnum;
import me.thesilverecho.zeropoint.registration.SpriteAccessor;
import net.minecraft.client.texture.NativeImage;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.resource.Resource;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Mixin(SpriteAtlasTexture.class)
public abstract class SpriteAtlasTextureMixin
{
	@Shadow
	protected abstract Identifier getTexturePath(Identifier identifier);

	@Shadow
	@Final
	private static Logger LOGGER;

	@Inject(method = "loadSprite", at = @At("HEAD"), cancellable = true)
	private void load(ResourceManager container, Sprite.Info info,
	                  int atlasWidth, int atlasHeight, int maxLevel, int x, int y, CallbackInfoReturnable<Sprite> cir)
	{
		Identifier identifier = this.getTexturePath(info.getId());

		try
		{
			Resource resource = container.getResource(identifier);
			Throwable throwable = null;

			Sprite sprite;
			try
			{
				NativeImage nativeImage = NativeImage.read(resource.getInputStream());
				sprite = new SpriteAccessor((SpriteAtlasTexture) (Object) this, info, maxLevel, atlasWidth, atlasHeight, x, y,
						parseImage(nativeImage, identifier));
			} catch (Throwable var23)
			{
				throwable = var23;
				throw var23;
			} finally
			{
				if (resource != null)
					try
					{
						resource.close();
					} catch (Throwable var22)
					{
						throwable.addSuppressed(var22);
					}
			}

			cir.setReturnValue(sprite);
		} catch (RuntimeException var25)
		{
			LOGGER.error("Unable to parse metadata from {}", identifier, var25);
			cir.setReturnValue(null);
		} catch (IOException var26)
		{
			LOGGER.error("Using missing texture, unable to load {}", identifier, var26);
			cir.setReturnValue(null);
		}

	}

	private NativeImage parseImage(NativeImage nativeImage, Identifier identifier)
	{
		Pattern compile = Pattern.compile("([a-z]*_?[a-z]*)(_stained_glass|_stained_glass_pane_top)\\.png$");
		Matcher matcher = compile.matcher(identifier.getPath());
		if (matcher.find())
		{
			NativeImage glassText = new NativeImage(nativeImage.getWidth(), nativeImage.getHeight(), false);
			glassText.fillRect(0, 0, nativeImage.getWidth(), nativeImage.getHeight(), ColourEnum.fromName(matcher.group(1)).getColor(100));
			return glassText;
		}

		return nativeImage;
	}

	private int getColour(Identifier identifier)
	{
		Pattern compile = Pattern.compile("coal_ore\\d*\\.png$");
		if (compile.matcher(identifier.getNamespace()).find())
			return 1;

		return -1;
	}

}
