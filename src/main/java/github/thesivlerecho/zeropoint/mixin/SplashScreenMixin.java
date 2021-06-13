package github.thesivlerecho.zeropoint.mixin;

import com.mojang.blaze3d.systems.RenderSystem;
import github.thesivlerecho.zeropoint.ZeroPointClient;
import github.thesivlerecho.zeropoint.render.DrawingUtil;
import github.thesivlerecho.zeropoint.render.widget.Component2d;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Overlay;
import net.minecraft.client.gui.screen.SplashScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.resource.ResourceReload;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.awt.*;
import java.util.Optional;
import java.util.function.Consumer;

@Mixin(SplashScreen.class)
public abstract class SplashScreenMixin extends Overlay
{
	private static final Identifier LOGO = new Identifier(ZeroPointClient.MOD_ID, "textures/ui/portal.png");

	@Shadow @Final private MinecraftClient client;
	@Shadow @Final private ResourceReload reload;
	@Shadow @Final private Consumer<Optional<Throwable>> exceptionHandler;
	@Shadow @Final private boolean reloading;
	@Shadow private long reloadCompleteTime;
	@Shadow private float progress;
	@Shadow private long reloadStartTime;

	@Inject(method = "render", at = @At(value = "HEAD"), cancellable = true)
	public void renderCustom(MatrixStack matrices, int mouseX, int mouseY, float delta, CallbackInfo ci)
	{
		ci.cancel();

		final int scaledWidth = this.client.getWindow().getScaledWidth();
		final int scaledHeight = this.client.getWindow().getScaledHeight();
		final long timeDif = Util.getMeasuringTimeMs();
		final float y = this.reload.getProgress();
		final float f = this.reloadCompleteTime > -1L ? (float) (timeDif - this.reloadCompleteTime) / 1000.0F : -1.0F;
		float g = this.reloadStartTime > -1L ? (float) (timeDif - this.reloadStartTime) / 500.0F : -1.0F;

		RenderSystem.setShaderTexture(0, LOGO);
		RenderSystem.setShader(GameRenderer::getPositionTexShader);
		RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1);
		drawTexture(matrices, 0, 0, scaledWidth, scaledHeight, 0.0F, 0.0F, 1080, 1920, 1080, 1920);

		this.progress = MathHelper.clamp(this.progress * 0.95F + y * 0.050000012F, 0.0F, 1.0F);

		if (f < 1.0F)
			this.renderProgressBar(matrices, scaledWidth / 2 - 150, scaledHeight / 4 * 3, 150, 12);
		else if (f >= 2.0F)
			this.client.setOverlay(null);


		if (this.reloadCompleteTime == -1L && this.reload.isComplete() && (!this.reloading || g >= 2.0F))
		{
			try
			{
				this.reload.throwException();
				this.exceptionHandler.accept(Optional.empty());
			} catch (Throwable var23)
			{
				this.exceptionHandler.accept(Optional.of(var23));
			}

			this.reloadCompleteTime = Util.getMeasuringTimeMs();
			if (this.client.currentScreen != null)
			{
				this.client.currentScreen.init(this.client, this.client.getWindow().getScaledWidth(), this.client.getWindow().getScaledHeight());
			}
		}


//		TODO re add

//		ci.cancel();
//		int i = MinecraftClient.getInstance().getWindow().getScaledWidth();
//		int j = MinecraftClient.getInstance().getWindow().getScaledHeight();
//		long l = Util.getMeasuringTimeMs();
//		if (this.reloading && (this.reload.isPrepareStageComplete() || MinecraftClient.getInstance().currentScreen != null) && this.prepareCompleteTime == -1L)
//			this.prepareCompleteTime = l;
//
//		float f = this.applyCompleteTime > -1L ? (float) (l - this.applyCompleteTime) / 1000.0F : -1.0F;
//		float g = this.prepareCompleteTime > -1L ? (float) (l - this.prepareCompleteTime) / 500.0F : -1.0F;
//		float o;
//		int m;
//		if (f >= 1.0F)
//		{
//			if (MinecraftClient.getInstance().currentScreen != null)
//				MinecraftClient.getInstance().currentScreen.render(matrices, 0, 0, delta);
//
//			m = MathHelper.ceil((1.0F - MathHelper.clamp(f - 1.0F, 0.0F, 1.0F)) * 255.0F);
//			GuiHelper.fill(matrices, 7, 0, 0, i, j, 16777215 | m << 24);
//			o = 1.0F - MathHelper.clamp(f - 1.0F, 0.0F, 1.0F);
//		} else if (this.reloading)
//		{
//			if (MinecraftClient.getInstance().currentScreen != null && g < 1.0F)
//				MinecraftClient.getInstance().currentScreen.render(matrices, mouseX, mouseY, delta);
//
//			m = MathHelper.ceil(MathHelper.clamp(g, 0.15D, 1.0D) * 255.0D);
//			GuiHelper.fill(matrices, 0, 0, i, j, 16777215 | m << 24);
//			o = MathHelper.clamp(g, 0.0F, 1.0F);
//		} else
//		{
//			GuiHelper.fill(matrices, 0, 0, i, j, -1);
//			o = 1.0F;
//		}
//
//		MinecraftClient.getInstance().getTextureManager().bindTexture(LOGO);
//		RenderSystem.enableBlend();
//		RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, o);
//		drawTexture(matrices, 0, 0, MinecraftClient.getInstance().getWindow().getScaledWidth(), MinecraftClient.getInstance().getWindow().getScaledHeight(), 0.0F, 0.0F, 1080, 1920, 1080, 1920);
//		//		blit(0, 0, 0, 0, MinecraftClient.getInstance().getWindow().getWidth(), MinecraftClient.getInstance().getWindow().getHeight());
////		GuiHelper.fillGradient(7, 0, 0, MinecraftClient.getInstance().getWindow().getScaledWidth(),
////				MinecraftClient.getInstance().getWindow().getScaledHeight(), new Color(46, 52, 64, 35).getRGB(), new Color(76, 86, 106).getRGB());
//		float r = this.reloadMonitor.getProgress();
//		this.progress = MathHelper.clamp(this.progress * 0.95F + r * 0.050000012F, 0.0F, 1.0F);
//		if (f < 1.0F)
//		{
//
//			//			fill(i / 2 - 150, 0, i / 2 + 150, 21, -1);
//			this.renderProgressBar(matrices, i / 2 - 150, j / 4 * 3, i / 2 + 150, j / 4 * 3 + 12);
//			String text = "Progress: " + progress * 100 + "%";
////			Tweaks.renderer.drawString(text, (float) (i / 2 - Tweaks.renderer.getStringWidth(text) / 2), (float) j / 4 * 3 - 1, -1, false, 0.4F);
//		}
//
//		if (f >= 2.0F)
//		{
//			MinecraftClient.getInstance().setOverlay(null);
//		}
//
//		if (this.applyCompleteTime == -1L && this.reloadMonitor.isApplyStageComplete() && (!this.reloading || g >= 2.0F))
//		{
//			System.out.println(Util.getMeasuringTimeMs() - applyCompleteTime);
//
//			try
//			{
//				this.reloadMonitor.throwExceptions();
//				this.exceptionHandler.accept(Optional.empty());
//			} catch (Throwable throwable)
//			{
//				this.exceptionHandler.accept(Optional.of(throwable));
//			}
//
//			this.applyCompleteTime = Util.getMeasuringTimeMs();
//			if (MinecraftClient.getInstance().currentScreen != null)
//				MinecraftClient.getInstance().currentScreen.init(MinecraftClient.getInstance(), MinecraftClient.getInstance().getWindow().getScaledWidth(), MinecraftClient.getInstance().getWindow().getScaledHeight());
//		}

	}

	private void renderProgressBar(MatrixStack matrix, int minX, int minY, int maxX, int maxY)
	{
		final int i = MathHelper.ceil((float) (maxX - minX - 1) * this.progress);

		final Component2d component = new Component2d(minX, minY, maxX, maxY);
//		DrawingUtil.drawBox(matrix, component.resizeComponent(-1).setColour(new Color(76, 86, 106).getRGB()));
//		DrawingUtil.drawBox(matrix, component.setColour(new Color(70, 77, 93).getRGB()));
		DrawingUtil.drawBoxWithShader(matrix, component.resizeComponent(1).setW(minX + i).setColour(new Color(143, 188, 187).getRGB()));

//		DrawingUtil.drawRectWithShader(component.resizeComponent(-1).setColour(new Color(76, 86, 106).getRGB()), radius + 1, matrix);
//		DrawingUtil.drawRectWithShader(component.setColour(new Color(70, 77, 93).getRGB()), radius + 1, matrix);
//		DrawingUtil.drawRectWithShader(component.resizeComponent(1).setW(minX + i).setColour(new Color(143, 188, 187).getRGB()), radius, matrix);
//		GuiHelper.drawRoundedRect(matrix, minX, minY, maxX, maxY, radius + 1, new Color(70, 77, 93).getRGB());//inner colour
//		GuiHelper.drawRoundedRect(matrix, minX + 1, minY + 1, minX + i, maxY - 1, radius, new Color(143, 188, 187).getRGB() /*| 143 << 16 | 188 << 8 | 187*/);
	}
}
