package github.thesivlerecho.zeropoint.mixin;

import com.mojang.blaze3d.systems.RenderSystem;
import github.thesivlerecho.zeropoint.config.Settings;
import github.thesivlerecho.zeropoint.gui.GuiHelper;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.text.OrderedText;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(Screen.class)
public abstract class ScreenMixin
{
	private static final int TOOLTIP_SPACE = 12;
	private static final int H_BORDER = 5;
	private static final int V_BORDER = 4;
	private static final int LINE_HEIGHT = 10;
	private static final int TITLE_GAP = 2;
	private final int background = Settings.TOOLTIP_BACKGROUND_COLOUR;
	private int borderTop = Settings.TOOLTIP_BORDER_TOP_COLOUR;
	private int borderBottom = Settings.TOOLTIP_BORDER_BOTTOM_COLOUR;

	@Shadow
	protected TextRenderer textRenderer;
	@Shadow
	public int width;
	@Shadow
	public int height;

	@Inject(method = "renderTooltip(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/item/ItemStack;II)V", at = @At(value = "HEAD"))
	private void renderCustomTooltip(MatrixStack matrices, ItemStack stack, int x, int y, CallbackInfo ci)
	{
		switch (stack.getRarity())
		{
			case UNCOMMON:
				borderTop = borderBottom = -251658411;
				break;
			case RARE:
				borderTop = borderBottom = -262799361;
				break;
			case EPIC:
				borderTop = borderBottom = -251701761;
				break;
			default:
			case COMMON:
				borderTop = Settings.TOOLTIP_BORDER_TOP_COLOUR;
				borderBottom = Settings.TOOLTIP_BORDER_BOTTOM_COLOUR;
				break;

		}
	}

	@Inject(method = "renderOrderedTooltip", at = @At(value = "HEAD"), cancellable = true)
	private void renderCustomTooltip(MatrixStack matrixStack, List<? extends OrderedText> lines, int x, int y, CallbackInfo ci)
	{
		ci.cancel();
		float tooltipX = x + TOOLTIP_SPACE;
		float tooltipY = y - TOOLTIP_SPACE;
		int tooltipWidth = 0;
		int tooltipHeight = V_BORDER * 2;

		for (OrderedText line : lines)
			tooltipWidth = Math.max(tooltipWidth, textRenderer.getWidth(line));

		if (lines.size() > 1)
		{
			tooltipHeight += 2 + (lines.size() - 1) * LINE_HEIGHT;
//			if (lines.size() > titleLinesCount) tooltipHeight += TITLE_GAP;
		}

		if (tooltipX + tooltipWidth > this.width)
			tooltipX -= 28 + tooltipWidth;

		if (tooltipY + tooltipHeight + 6 > this.height)
			tooltipY = this.height - tooltipHeight - 6;

		RenderSystem.disableDepthTest();
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		matrixStack.push();
		GuiHelper.drawRoundedRect(matrixStack, tooltipX - H_BORDER, tooltipY - V_BORDER, tooltipX + tooltipWidth + H_BORDER, tooltipY + tooltipHeight + V_BORDER, 3, background);
		GuiHelper.drawGradientBoardedRect(matrixStack,
				tooltipX - H_BORDER, tooltipY - V_BORDER, tooltipX + tooltipWidth + H_BORDER,
				tooltipY + tooltipHeight + V_BORDER, 3,
				borderTop,
				borderBottom);

		RenderSystem.enableDepthTest();
		RenderSystem.disableBlend();

		VertexConsumerProvider.Immediate immediate = VertexConsumerProvider.immediate(Tessellator.getInstance().getBuffer());
		matrixStack.translate(0.0D, 0.0D, 400.0D);


		for (int s = 0; s < lines.size(); ++s)
		{
			OrderedText orderedText2 = lines.get(s);
			if (orderedText2 != null)
				this.textRenderer.draw(orderedText2, tooltipX, tooltipY, -1, true, matrixStack.peek().getModel(), immediate, false, 0, 15728880);

			if (s == 0)
				tooltipY += 2;

			tooltipY += 10;
		}

		immediate.draw();

		matrixStack.pop();
	}


}
