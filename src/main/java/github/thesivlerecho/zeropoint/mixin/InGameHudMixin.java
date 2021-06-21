package github.thesivlerecho.zeropoint.mixin;

import github.thesivlerecho.zeropoint.event.EventManager;
import github.thesivlerecho.zeropoint.event.events.Render2dEvent;
import github.thesivlerecho.zeropoint.gui.crosshair.CrossHairModule;
import github.thesivlerecho.zeropoint.render.DrawingUtil;
import github.thesivlerecho.zeropoint.render.shader.ShaderManager;
import github.thesivlerecho.zeropoint.render.shader.ZeroPointShader;
import github.thesivlerecho.zeropoint.render.shader.programs.post.BlurPostprocessShader;
import github.thesivlerecho.zeropoint.render.widget.PositioningComponent;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gl.Framebuffer;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.scoreboard.ScoreboardObjective;
import net.minecraft.util.math.Vec2f;
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

	@Shadow private int scaledWidth;

	@Shadow
	protected abstract void renderHotbarItem(int x, int y, float tickDelta, PlayerEntity player, ItemStack stack, int seed);

	@Shadow
	protected abstract PlayerEntity getCameraPlayer();

	@Shadow private int scaledHeight;

	private float animationX;

	@Inject(method = "renderCrosshair", at = @At(value = "HEAD"), cancellable = true)
	private void customCrossHair(MatrixStack matrixStack, CallbackInfo ci)
	{
		CrossHairModule.renderCrossHair(matrixStack);
	/*	if (Config2.customCrosshairEnabled.isOn())
		{
			callbackInfo.cancel();
			CustomCrossHair.render();
		}*/
	}

	@Inject(method = "renderScoreboardSidebar", at = @At(value = "HEAD"), cancellable = true)
	private void renderScoreboardSidebar(
			MatrixStack matrixStack, ScoreboardObjective scoreboardObjective, CallbackInfo ci)
	{
		//		callbackInfo.cancel();
		//		Tweaks.customSidebar.drawSidebar(matrixStack, scoreboardObjective, MinecraftClient.getInstance().getWindow().getScaledWidth(),
		//				MinecraftClient.getInstance().getWindow().getScaledHeight());
	}

//	@Inject(method = "render", at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/client/util/math/MatrixStack;pop()V", ordinal = 5), cancellable = true)
//	private void onDraw(MatrixStack matrixStack, float tickDelta, CallbackInfo ci)
//	{
//		if (MinecraftClient.getInstance().options.debugEnabled)
//			return;
//		EventManager.call(new Render2dEvent(matrixStack, tickDelta, ci));
//	}


	@Inject(method = "render", at = @At(value = "TAIL"), cancellable = true)
	private void onDraw1(MatrixStack matrixStack, float tickDelta, CallbackInfo ci)
	{
		if (MinecraftClient.getInstance().options.debugEnabled)
			return;
		EventManager.call(new Render2dEvent(matrixStack, tickDelta, ci));
	}


	@Inject(method = "renderHotbar", at = @At(value = "HEAD"), cancellable = true)
	private void renderHotbar(float tickDelta, MatrixStack matrices, CallbackInfo ci)
	{
		ci.cancel();
		PlayerEntity playerEntity = this.getCameraPlayer();
		if (playerEntity != null)
		{
			final float halfWidth = this.scaledWidth / 2f;
			final PositioningComponent box = new PositioningComponent(halfWidth - 91 - 1 + 2, this.scaledHeight - 22 - 1, 20, 23);/*.setColour(new Color(255, 255, 255, 80).getRGB()*/
			final int selectedSlot = playerEntity.getInventory().selectedSlot;
			final int tempZIndex = DrawingUtil.getZIndex();
			DrawingUtil.setZIndex(-90);
			final int yMod = 0;//16;
			final PositioningComponent bar = new PositioningComponent(0, this.scaledHeight - 23 - yMod, this.scaledWidth, 23)/*.setColour(new Color(55, 49, 49, 10).getRGB())*/;
			final BlurPostprocessShader shader1 = ShaderManager.getShader(BlurPostprocessShader.class, ZeroPointShader.BLUR);
			final Framebuffer framebuffer = MinecraftClient.getInstance().getFramebuffer();
			shader1.setUp(framebuffer, framebuffer, 12, new Vec2f(1, 0));
			shader1.bind();
			DrawingUtil.drawBasicBoxtest(matrices, 0, this.scaledHeight - 23 - yMod, this.scaledWidth, 23, new Color(55, 49, 49, 80).getRGB());
			shader1.unBind();


//			DrawingUtil.drawBasicBox(matrices, 0, this.scaledHeight - 23 - yMod, this.scaledWidth, 23, new Color(55, 49, 49, 80).getRGB());
			float x = (halfWidth - 91 - 1 + selectedSlot * 20 + 2);
			this.animationX = (float) DrawingUtil.getAnimationState(this.animationX, x, Math.max(50.0F, Math.abs(this.animationX - x) * 10.0F));
			box.setY(this.scaledHeight - 23 - yMod);
			box.setX(this.animationX);
			DrawingUtil.drawRectWithShader(box, 3, matrices);
			DrawingUtil.setZIndex(tempZIndex);

			int random = 0;
			for (int slot = 0; slot < 9; slot++)
				this.renderHotbarItem((int) (halfWidth - 90 + slot * 20 + 2), this.scaledHeight - 16 - 3 - yMod, tickDelta, playerEntity, playerEntity.getInventory().main.get(slot), random++);
		}
	}

}
