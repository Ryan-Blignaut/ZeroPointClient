package me.thesilverecho.zeropoint.mixin;

import me.thesilverecho.zeropoint.gui.Box2d;
import me.thesilverecho.zeropoint.gui.GuiHelper;
import me.thesilverecho.zeropoint.gui.overlay.keystrokes.KeystrokesRenderer;
import me.thesilverecho.zeropoint.gui.potionHud.EffectRender;
import me.thesilverecho.zeropoint.module.Module;
import me.thesilverecho.zeropoint.module.ModuleManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.scoreboard.ScoreboardObjective;
import net.minecraft.util.Util;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.awt.*;
import java.util.Comparator;
import java.util.concurrent.CopyOnWriteArrayList;

// renderScoreboardSidebar in InGameHud
@Mixin(InGameHud.class)
public abstract class InGameHudMixin extends DrawableHelper
{
	@Inject(method = "renderScoreboardSidebar", at = @At(value = "HEAD"), cancellable = true)
	private void renderScoreboardSidebar(
			MatrixStack matrixStack, ScoreboardObjective scoreboardObjective, CallbackInfo ci)
	{
		//		ci.cancel();
		//		Tweaks.customSidebar.drawSidebar(matrixStack, scoreboardObjective, MinecraftClient.getInstance().getWindow().getScaledWidth(),
		//				MinecraftClient.getInstance().getWindow().getScaledHeight());
	}

	@Inject(method = "renderCrosshair", at = @At(value = "HEAD"), cancellable = true)
	private void customCrossHair(CallbackInfo ci)
	{
	/*	if (Config2.customCrosshairEnabled.isOn())
		{
			ci.cancel();
			CustomCrossHair.render();
		}*/
	}


	@Inject(method = "renderStatusBars", at = @At(value = "CONSTANT", args = "stringValue=food", shift = At.Shift.BY, by = 2))
	private void renderStatusBars(MatrixStack matrices, CallbackInfo ci)
	{
		float saturationLevel = MinecraftClient.getInstance().player.getHungerManager().getSaturationLevel();
		for (int i = 0; i < saturationLevel; i++)
		{
			GuiHelper.rectangle(matrices, 7, new Box2d(i * 16, i * 16, 16 + i * 16, 16 + i * 16), new Color(255, 255, 255, 100).getRGB());
		}
	}


	@Shadow
	public abstract TextRenderer getFontRenderer();

	@Shadow
	private int scaledWidth;

	@Inject(method = "render", at = @At(value = "FIELD", target = "Lnet/minecraft/client/options/GameOptions;hudHidden:Z", ordinal = 2))
	private void onDraw(
			MatrixStack matrixStack, float f, CallbackInfo ci)
	{
		if (MinecraftClient.getInstance().options.debugEnabled)
			return;

		KeystrokesRenderer.getInstance().renderKeystrokes(matrixStack);
		EffectRender.render(matrixStack);
		//		List<Module> mods = new ArrayList<>();
	/*	AtomicInteger y = new AtomicInteger(6);
		ModuleManager.ACTIVE_MODULES.stream().sorted(Comparator.comparingDouble(module -> getFontRenderer().getWidth(module.getName()))).forEach(
				module ->
				{



					if (module.isToggled() && module.anim != -1)
					{
						String s = module.getName() + " ";
						getFontRenderer().getWidth(module.getName() + " ");

						getFontRenderer().drawWithShadow(matrixStack, module.getName(), (scaledWidth - module.anim), (y.get() - 1), -1);
						y.addAndGet(getFontRenderer().fontHeight + 6);

						if (module.anim < getFontRenderer().getWidth(s) + 1 && module.isToggled())
						{
							++module.anim;
						}

						if (module.anim < getFontRenderer().getWidth(s) + 1 && module.isToggled())
						{
							++module.anim;
						}

						if (module.anim < getFontRenderer().getWidth(s) + 1 && module.isToggled())
						{
							++module.anim;
						}

						if (module.anim < getFontRenderer().getWidth(s) + 1 && module.isToggled())
						{
							++module.anim;
						}

						if (module.anim < getFontRenderer().getWidth(s) + 1 && module.isToggled())
						{
							++module.anim;
						}

						if (module.anim > getFontRenderer().getWidth(s) + 1 && module.isToggled())
						{
							module.anim = getFontRenderer().getWidth(s);
						}

						if (module.anim != -1 && !module.isToggled())
						{
							--module.anim;
						}

						if (module.anim != -1 && !module.isToggled())
						{
							--module.anim;
						}

						if (module.anim != -1 && !module.isToggled())
						{
							--module.anim;
						}

						if (module.anim != -1 && !module.isToggled())
						{
							--module.anim;
						}

						if (module.anim < -1 && !module.isToggled())
						{
							module.anim = 0;
						}

					}
				});*/

		CopyOnWriteArrayList<Module> modules = new CopyOnWriteArrayList<>();
		for (Module module : ModuleManager.ACTIVE_MODULES)
		{
			if (!module.isToggled())
				continue;
			modules.add(module);

		}
		modules.sort(Comparator.comparingDouble(module -> getFontRenderer().getWidth(module.getName())));
		int y = 4;
		for (Module module : modules)
		{
			module.setLastPressTime(Util.getMeasuringTimeMs());
			float delay = 900;
			double tanh = Math.tanh((System.currentTimeMillis() - module.getLastPressTime()) / delay);

			float x = (float) ((tanh) * (scaledWidth - getFontRenderer().getWidth(module.getName()) - 2.0f));
			GuiHelper.drawRect(matrixStack, x - 1.0f, (float) (y - 4.3), scaledWidth, (float) (y + 5.5), new Color(160, 160, 160, 120).getRGB());
			GuiHelper.drawRect(matrixStack, (float) (scaledWidth - 1.6), ((float) (y - 4.3)), scaledWidth, ((float) (y + 5.5)), -1);
			getFontRenderer().drawWithShadow(matrixStack, module.getName(), x, y - 1, -1);

			y += getFontRenderer().fontHeight;

		}

			/*if (!toSort.isEmpty())
			{
				String name = toSort.get(0).getName();
				int width = textRenderer.getWidth(name);

				//top line
				GuiHelper.line(GL11.GL_LINE_LOOP, 5, 5, width, 5, Color.red.getRGB());

				for (int i = 0; i < toSort.size(); i++)
				{
					//line top down
					GuiHelper.line(GL11.GL_LINE_LOOP, width, 5 + y.getAndAdd(8), width, textRenderer.fontHeight, Color.red.getRGB());
					//line from down to next
					GuiHelper.line(GL11.GL_LINE_LOOP, width, 5 + y.getAndAdd(8), width - textRenderer.getWidth(toSort.get(i + 1).getName()),
							textRenderer.fontHeight, Color.red.getRGB());

					textRenderer.draw(matrixStack, name, 5, 5 + y.getAndAdd(8), Color.RED.getRGB());

				}
				GuiHelper.line(GL11.GL_LINE_LOOP, 5, 5 + y.get(), width, 5 + y.get(), Color.red.getRGB());
			}*/

	}

}
