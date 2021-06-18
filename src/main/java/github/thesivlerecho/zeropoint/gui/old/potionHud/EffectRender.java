package github.thesivlerecho.zeropoint.gui.old.potionHud;

import github.thesivlerecho.zeropoint.config.Settings;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class EffectRender
{
	public static void render(MatrixStack m)
	{
		Iterator<StatusEffectInstance> iterator = MinecraftClient.getInstance().player.getStatusEffects().iterator();
		int i = -22;
		while (iterator.hasNext())
		{
			i += 22;
			StatusEffectInstance effect = iterator.next();

			ArrayList<EffectSetting> effectSettings = Settings.EFFECT_SETTINGS;
			if (!effectSettings.isEmpty())
			{
				EffectSetting effectSetting = effectSettings.get(StatusEffect.getRawId(effect.getEffectType()));
				if (effectSetting != null)
					if (effectSetting.isEnabled())
						renderEffect(m, effectSetting, effect, 1, i, 3, 4);
			}

		}
	}

	private static void renderEffect(MatrixStack m, EffectSetting effectSetting, StatusEffectInstance effect, int x, int y, int w, int h)
	{
		if (Settings.renderIcons.get())
		{
			renderIcon(effectSetting, x, y, w, h);
		}
		renderText(m, effectSetting, effect, x, y, w, h);


	}

	private static void renderText(MatrixStack m, EffectSetting effectSetting, StatusEffectInstance effect, int x, int y, int w, int h)
	{
//		StatusEffectInstance statusEffect = MinecraftClient.getInstance().player.getStatusEffect(StatusEffect.byRawId(effectSetting.getEffectId()));
		if (effect != null)
		{
			String name = effectSetting.getCustomName().isEmpty() ? effect.getEffectType().getName().getString() : effectSetting.getCustomName();
			MinecraftClient.getInstance().textRenderer.draw(m, name, x, y, Color.pink.getRGB());
		}

	}

	private static void renderIcon(EffectSetting effectSetting, int x, int y, int w, int h)
	{

	}

}
