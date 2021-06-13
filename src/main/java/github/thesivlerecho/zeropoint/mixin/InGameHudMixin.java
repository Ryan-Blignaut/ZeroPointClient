package github.thesivlerecho.zeropoint.mixin;

import github.thesivlerecho.zeropoint.render.DrawingUtil;
import github.thesivlerecho.zeropoint.render.widget.Component2d;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.scoreboard.ScoreboardObjective;
import net.minecraft.util.Util;
import net.minecraft.util.math.Matrix4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.awt.*;

// renderScoreboardSidebar in InGameHud
@Mixin(InGameHud.class)
public abstract class InGameHudMixin extends DrawableHelper
{
	@Shadow
	private int scaledWidth;

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

	private int prevSlot;
	private long lastPressTime;
	private float distance;

	@Inject(method = "renderHotbar", at = @At(value = "HEAD"), cancellable = true)
	private void renderHotbar(float tickDelta, MatrixStack matrices, CallbackInfo ci)
	{
		ci.cancel();
		PlayerEntity playerEntity = this.getCameraPlayer();
		if (playerEntity != null)
		{
			final float halfWidth = this.scaledWidth / 2f;
			final Component2d component2d = new Component2d(halfWidth - 91 - 1 + 2, this.scaledHeight - 22 - 1, 20, 24).setColour(new Color(255, 255, 255, 80).getRGB());
			final int selectedSlot = playerEntity.getInventory().selectedSlot;

//			component2d.addToX((float) (Math.tan((Util.getMeasuringTimeMs() - lastPressTime) / 500f) * (distance)));
//			System.out.println(distance);
//			System.out.println(Math.tan((Util.getMeasuringTimeMs() - lastPressTime) / 500f) * (distance));

			if (selectedSlot != prevSlot)
			{
				lastPressTime = Util.getMeasuringTimeMs();
				distance = (selectedSlot - prevSlot);
				prevSlot = selectedSlot;
			}


			final int tempZIndex = DrawingUtil.getZIndex();
			DrawingUtil.setZIndex(-90);
			DrawingUtil.drawBasicBox(matrices, 0, this.scaledHeight - 23, this.scaledWidth, 23, new Color(55, 49, 49, 80).getRGB());
//			DrawingUtil.drawBasicBox(matrices, halfWidth - 91 - 1 + 2 + playerEntity.getInventory().selectedSlot * 20, this.scaledHeight - 22 - 1, 20, 24, new Color(255, 255, 255, 80).getRGB());
			final float tanh = (float) Math.tanh((Util.getMeasuringTimeMs() - lastPressTime) / 500f);
			component2d.addToX(selectedSlot * 20);
			DrawingUtil.drawRectWithShader(component2d, 3, matrices);
			DrawingUtil.setZIndex(tempZIndex);

			int random = 0;
			for (int slot = 0; slot < 9; slot++)
				this.renderHotbarItem((int) (halfWidth - 90 + slot * 20 + 2), this.scaledHeight - 16 - 3, tickDelta, playerEntity, playerEntity.getInventory().main.get(slot), random++);

		}

	}

	@Shadow
	protected abstract void renderHotbarItem(int x, int y, float tickDelta, PlayerEntity player, ItemStack stack, int seed);

	/*@Inject(method = "renderStatusBars", at = @At(value = "CONSTANT", args = "stringValue=armor", shift = At.Shift.BY, by = 0))
	private void renderStatusBars(MatrixStack matrices, CallbackInfo ci)
	{
//		float saturationLevel = MinecraftClient.getInstance().player.getHungerManager().getSaturationLevel();
//		for (int i = 0; i < saturationLevel; i++)
//		{
//			GuiHelper.rectangle(matrices, 7, new Box2d(i * 16, i * 16, 16 + i * 16, 16 + i * 16), new Color(255, 255, 255, 100).getRGB());
//		}

		final PlayerEntity player = this.getCameraPlayer();

		final double v = Optional.ofNullable(player.getAttributeInstance(EntityAttributes.GENERIC_ARMOR_TOUGHNESS)).map(EntityAttributeInstance::getValue).orElse(0D);
		int o = this.scaledHeight - 39;

//		int r = Math.max(10 - (q - 2), 3);
//		int s = o - (q - 1) * r - 10;
		int m = this.scaledWidth / 2 - 91;
		int z;
		int aa;
		int s = 5;
		for (z = 0; z < 10; ++z)
			if (v > 0)
			{
//				final VertexConsumerProvider.Immediate consumers = MinecraftClient.getInstance().getBufferBuilders().getEntityVertexConsumers();
//				final VertexConsumer dual = VertexConsumers.dual(consumers.getBuffer(ModRenderLayer.POT_OVERLAY), consumers.getBuffer(RenderLayer.getEntityTranslucent(new Identifier(ZeroPointClient.MOD_ID, "textures/ui/social/github.png"))));

				aa = m + z * 8;
//				this.drawTexture2(matrices, dual, aa, s, 34, 9, 9, 9);
				MinecraftClient.getInstance().getTextureManager().bindTexture(new Identifier(ZeroPointClient.MOD_ID, "textures/ui/social/github.png"));
				System.out.println(v);
				if (z * 2 + 1 < v)
				{
					this.drawTexture(matrices, aa, s, 34, 9, 9, 9);
				}
				if (z * 2 + 1 == v)
					this.drawTexture(matrices, aa, s, 25, 9, 9, 9);
				if (z * 2 + 1 > v)
					this.drawTexture(matrices, aa, s, 16, 9, 9, 9);
			}


	}*/

	public void drawTexture2(MatrixStack matrices, VertexConsumer dual, int x, int y, int u, int v, int width, int height)
	{
		drawTexture1(matrices, dual, x, y, (float) u, (float) v, width, height, 256, 256);
	}


	public void drawTexture1(MatrixStack matrices, VertexConsumer dual, int x, int y, float u, float v, int width, int height, int textureWidth, int textureHeight)
	{
		drawTexture2(matrices, dual, x, y, width, height, u, v, width, height, textureWidth, textureHeight);
	}

	private void drawTexture2(MatrixStack matrices, VertexConsumer dual, int x, int y, int width, int height, float u, float v, int regionWidth, int regionHeight, int textureWidth, int textureHeight)
	{
		drawTexture(matrices, dual, x, x + width, y, y + height, 0, regionWidth, regionHeight, u, v, textureWidth, textureHeight);
	}

	private void drawTexture(MatrixStack matrices, VertexConsumer dual, int x0, int y0, int x1, int y1, int z, int regionWidth, int regionHeight, float u, float v, int textureWidth, int textureHeight)
	{
		test(matrices.peek().getModel(), dual, x0, y0, x1, y1, z, (u + 0.0F) / (float) textureWidth, (u + (float) regionWidth) / (float) textureWidth, (v + 0.0F) / (float) textureHeight, (v + (float) regionHeight) / (float) textureHeight);
	}


	private void test(Matrix4f matrices, VertexConsumer dual, int x0, int x1, int y0, int y1, int z, float u0, float u1, float v0, float v1)
	{
		dual.vertex(matrices, (float) x0, (float) y1, (float) z).texture(u0, v1).next();
		dual.vertex(matrices, (float) x1, (float) y1, (float) z).texture(u1, v1).next();
		dual.vertex(matrices, (float) x1, (float) y0, (float) z).texture(u1, v0).next();
		dual.vertex(matrices, (float) x0, (float) y0, (float) z).texture(u0, v0).next();
//		Tessellator.getInstance().draw();

	}

	@Shadow
	public abstract TextRenderer getFontRenderer();

	@Shadow
	protected abstract PlayerEntity getCameraPlayer();

	@Shadow
	private int scaledHeight;

	@Inject(method = "render", at = @At(value = "FIELD", target = "Lnet/minecraft/client/option/GameOptions;hudHidden:Z", ordinal = 2))
	private void onDraw(
			MatrixStack matrixStack, float f, CallbackInfo ci)
	{
		if (MinecraftClient.getInstance().options.debugEnabled)
			return;

//		KeystrokesRenderer.getInstance().renderKeystrokes(matrixStack);
//		EffectRender.render(matrixStack);
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

//		CopyOnWriteArrayList<Module> modules = new CopyOnWriteArrayList<>();
//		for (Module module : ModuleManager.ACTIVE_MODULES)
//		{
//			if (!module.isToggled())
//				continue;
//			modules.add(module);
//
//		}
//		modules.sort(Comparator.comparingDouble(module -> getFontRenderer().getWidth(module.getName())));
//		int y = 4;
//		for (Module module : modules)
//		{
//			module.setLastPressTime(Util.getMeasuringTimeMs());
//			float delay = 900;
//			double tanh = Math.tanh((Util.getMeasuringTimeMs() - module.getLastPressTime()) / delay);
//
//			float x = (float) ((tanh) * (scaledWidth - getFontRenderer().getWidth(module.getName()) - 2.0f));
//			GuiHelper.drawRect(matrixStack, x - 1.0f, (float) (y - 4.3), scaledWidth, (float) (y + 5.5), new Color(160, 160, 160, 120).getRGB());
//			GuiHelper.drawRect(matrixStack, (float) (scaledWidth - 1.6), ((float) (y - 4.3)), scaledWidth, ((float) (y + 5.5)), -1);
//			getFontRenderer().drawWithShadow(matrixStack, module.getName(), x, y - 1, -1);
//
//			y += getFontRenderer().fontHeight;
//
//		}

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
