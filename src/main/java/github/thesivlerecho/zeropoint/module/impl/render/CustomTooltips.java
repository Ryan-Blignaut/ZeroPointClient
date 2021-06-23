package github.thesivlerecho.zeropoint.module.impl.render;

import com.mojang.blaze3d.systems.RenderSystem;
import github.thesivlerecho.zeropoint.event.EventListener;
import github.thesivlerecho.zeropoint.event.events.RenderTooltipEvent;
import github.thesivlerecho.zeropoint.module.BaseModule;
import github.thesivlerecho.zeropoint.module.ModCategory;
import github.thesivlerecho.zeropoint.module.ZPModule;
import github.thesivlerecho.zeropoint.render.DrawingUtil;
import github.thesivlerecho.zeropoint.render.widget.PositioningComponent;
import net.minecraft.client.gui.tooltip.TooltipComponent;
import net.minecraft.client.util.math.MatrixStack;

import java.util.List;

@ZPModule(name = "New tooltip render", category = ModCategory.RENDER)
public class CustomTooltips extends BaseModule
{
	private static final int TOOLTIP_SPACE = 12;
	private static final int H_BORDER = 5;
	private static final int V_BORDER = 4;
	private static final int LINE_HEIGHT = 10;

	private boolean rarityEnabled = true;

	@EventListener
	public void renderSmoothTooltip(RenderTooltipEvent event)
	{
//		event.callbackInfo().cancel();
		final List<TooltipComponent> components = event.components();
		if (!components.isEmpty())
		{
			float tooltipX = event.x() + TOOLTIP_SPACE;
			float tooltipY = event.y() - TOOLTIP_SPACE;
			int tooltipWidth = 0;
			int tooltipHeight = V_BORDER * 2;

			for (TooltipComponent component : components)
				tooltipWidth = Math.max(tooltipWidth, component.getWidth(event.textRenderer()));

			if (components.size() > 1)
				tooltipHeight += 2 + (components.size() - 1) * LINE_HEIGHT;

			if (tooltipX + tooltipWidth > event.width())
				tooltipX -= 28 + tooltipWidth;

			if (tooltipY + tooltipHeight + 6 > event.height())
				tooltipY = event.height() - tooltipHeight - 6;

			final MatrixStack matrices = event.matrices();
//			drawTooltipRectangle(matrices, new PositioningComponent(tooltipX, tooltipY, tooltipWidth, tooltipHeight).setColour(Color.PINK.getRGB()));
//			System.out.println("test");


			matrices.pop();
		}
	}


	public static void drawTooltipRectangle(MatrixStack matrixStack, PositioningComponent positioningComponent)
	{
		RenderSystem.disableDepthTest();
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		matrixStack.push();
		final int zIndex = DrawingUtil.getZIndex();
		DrawingUtil.setZIndex(400);
		DrawingUtil.drawRectWithShader(positioningComponent, 3, matrixStack);
//		positioningComponent.setColour(Color.RED.getRGB());
//		DrawingUtil.drawRectWithShader(component2d, 3, matrixStack);
		DrawingUtil.setZIndex(zIndex);
		RenderSystem.enableDepthTest();
		RenderSystem.disableBlend();
	}

	private void renderTooltipFromComponents(MatrixStack matrices, List<TooltipComponent> components, int x, int y)
	{
		/*if (!components.isEmpty())
		{
			int i = 0;
			int j = components.size() == 1 ? -2 : 0;

			TooltipComponent tooltipComponent;
			for (Iterator var7 = components.iterator(); var7.hasNext(); j += tooltipComponent.getHeight())
			{
				tooltipComponent = (TooltipComponent) var7.next();
				int k = tooltipComponent.getWidth(this.textRenderer);
				if (k > i)
				{
					i = k;
				}
			}

			int l = x + 12;
			int m = y - 12;
			if (l + i > this.width)
			{
				l -= 28 + i;
			}

			if (m + j + 6 > this.height)
			{
				m = this.height - j - 6;
			}

			matrices.push();
			int p = -267386864;
			int q = 1347420415;
			int r = 1344798847;
			float f = this.itemRenderer.zOffset;
			this.itemRenderer.zOffset = 400.0F;
//			Tessellator tessellator = Tessellator.getInstance();
//			BufferBuilder bufferBuilder = tessellator.getBuffer();
//			RenderSystem.setShader(GameRenderer::getPositionColorShader);
//			bufferBuilder.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_COLOR);
//			Matrix4f matrix4f = matrices.peek().getModel();
//			fillGradient(matrix4f, bufferBuilder, l - 3, m - 4, l + i + 3, m - 3, 400, -267386864, -267386864);
//			fillGradient(matrix4f, bufferBuilder, l - 3, m + j + 3, l + i + 3, m + j + 4, 400, -267386864, -267386864);
//			fillGradient(matrix4f, bufferBuilder, l - 3, m - 3, l + i + 3, m + j + 3, 400, -267386864, -267386864);
//			fillGradient(matrix4f, bufferBuilder, l - 4, m - 3, l - 3, m + j + 3, 400, -267386864, -267386864);
//			fillGradient(matrix4f, bufferBuilder, l + i + 3, m - 3, l + i + 4, m + j + 3, 400, -267386864, -267386864);
//			fillGradient(matrix4f, bufferBuilder, l - 3, m - 3 + 1, l - 3 + 1, m + j + 3 - 1, 400, 1347420415, 1344798847);
//			fillGradient(matrix4f, bufferBuilder, l + i + 2, m - 3 + 1, l + i + 3, m + j + 3 - 1, 400, 1347420415, 1344798847);
//			fillGradient(matrix4f, bufferBuilder, l - 3, m - 3, l + i + 3, m - 3 + 1, 400, 1347420415, 1347420415);
//			fillGradient(matrix4f, bufferBuilder, l - 3, m + j + 2, l + i + 3, m + j + 3, 400, 1344798847, 1344798847);
//			RenderSystem.enableDepthTest();
//			RenderSystem.disableTexture();
//			RenderSystem.enableBlend();
//			RenderSystem.defaultBlendFunc();
//			bufferBuilder.end();
//			BufferRenderer.draw(bufferBuilder);
//			RenderSystem.disableBlend();
//			RenderSystem.enableTexture();
			VertexConsumerProvider.Immediate immediate = VertexConsumerProvider.immediate(Tessellator.getInstance().getBuffer());
			matrices.translate(0.0D, 0.0D, 400.0D);
			int t = m;

			int v;
			TooltipComponent tooltipComponent3;
			for (v = 0; v < components.size(); ++v)
			{
				tooltipComponent3 = components.get(v);
				tooltipComponent3.drawText(this.textRenderer, l, t, matrix4f, immediate);
				t += tooltipComponent3.getHeight() + (v == 0 ? 2 : 0);
			}

			immediate.draw();
			matrices.pop();
			t = m;

			for (v = 0; v < components.size(); ++v)
			{
				tooltipComponent3 = components.get(v);
				tooltipComponent3.drawItems(this.textRenderer, l, t, matrices, this.itemRenderer, 400, this.client.getTextureManager());
				t += tooltipComponent3.getHeight() + (v == 0 ? 2 : 0);
			}

			this.itemRenderer.zOffset = f;
		}*/
	}


}
