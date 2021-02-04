package github.thesivlerecho.zeropoint.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.render.BufferBuilder;
import net.minecraft.client.render.BufferRenderer;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Matrix4f;
import org.lwjgl.opengl.GL11;

import java.awt.*;

public class DrawingHelper
{

	private final BufferBuilder bufferBuilder = Tessellator.getInstance().getBuffer();
	private int r = 255;
	private int g = 255;
	private int b = 255;
	private int a = 255;

	private int drawingX = 0;
	private int drawingY = 0;

	public void drawIcon(Icon icon, float left, float top, float right, float bottom)
	{
		RenderSystem.enableTexture();
		icon.bindTexture();

		left += drawingX;
		top += drawingY;
		right += drawingX;
		bottom += drawingY;

		bufferBuilder.begin(GL11.GL_QUADS, VertexFormats.POSITION_COLOR_TEXTURE);
		bufferBuilder.vertex(left, bottom, 0).color(r, g, b, a).texture(icon.getLeft(), icon.getBottom()).next();
		bufferBuilder.vertex(right, bottom, 0).color(r, g, b, a).texture(icon.getRight(), icon.getBottom()).next();
		bufferBuilder.vertex(right, top, 0).color(r, g, b, a).texture(icon.getRight(), icon.getTop()).next();
		bufferBuilder.vertex(left, top, 0).color(r, g, b, a).texture(icon.getLeft(), icon.getTop()).next();
		bufferBuilder.end();
		BufferRenderer.draw(bufferBuilder);
	}

	public void drawIcon(MatrixStack matrixStack, Icon icon, float left, float top, float right, float bottom)
	{
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		Matrix4f model = matrixStack.peek().getModel();
		RenderSystem.enableTexture();
		icon.bindTexture();

		left += drawingX;
		top += drawingY;
		right += drawingX;
		bottom += drawingY;

		bufferBuilder.begin(GL11.GL_QUADS, VertexFormats.POSITION_COLOR_TEXTURE);
		bufferBuilder.vertex(model, left, bottom, 0).color(r, g, b, a).texture(icon.getLeft(), icon.getBottom()).next();
		bufferBuilder.vertex(model, right, bottom, 0).color(r, g, b, a).texture(icon.getRight(), icon.getBottom()).next();
		bufferBuilder.vertex(model, right, top, 0).color(r, g, b, a).texture(icon.getRight(), icon.getTop()).next();
		bufferBuilder.vertex(model, left, top, 0).color(r, g, b, a).texture(icon.getLeft(), icon.getTop()).next();
		bufferBuilder.end();
		BufferRenderer.draw(bufferBuilder);
		RenderSystem.disableBlend();
	}

	public void drawIcon(MatrixStack matrixStack, Icon icon, float left, float top, float right, float bottom, CustomColor col1, CustomColor col2)
	{
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		Matrix4f model = matrixStack.peek().getModel();
		RenderSystem.enableTexture();
		icon.bindTexture();

		left += drawingX;
		top += drawingY;
		right += drawingX;
		bottom += drawingY;

		bufferBuilder.begin(GL11.GL_QUADS, VertexFormats.POSITION_COLOR_TEXTURE);
		bufferBuilder.vertex(model, left, bottom, 0).color(col1.getRed(), col1.getBlue(), col1.getRed(), col1.getAlpha()).texture(icon.getLeft(),
				icon.getBottom()).next();
		bufferBuilder.vertex(model, right, bottom, 0).color(col1.getRed(), col1.getBlue(), col1.getRed(), col1.getAlpha()).texture(icon.getRight(),
				icon.getBottom()).next();
		bufferBuilder.vertex(model, right, top, 0).color(col1.getRed(), col1.getBlue(), col1.getRed(), col1.getAlpha()).texture(icon.getRight(),
				icon.getTop()).next();
		bufferBuilder.vertex(model, left, top, 0).color(col1.getRed(), col1.getBlue(), col1.getRed(), col1.getAlpha()).texture(icon.getLeft(),
				icon.getTop()).next();
		bufferBuilder.end();
		BufferRenderer.draw(bufferBuilder);
		RenderSystem.disableBlend();
	}

	public DrawingHelper setRGBA(int r, int b, int g, int a)
	{
		this.r = r;
		this.g = g;
		this.b = b;
		this.a = a;
		return this;
	}

	public DrawingHelper setRGB(int r, int g, int b)
	{
		this.r = r;
		this.g = g;
		this.b = b;
		return this;
	}

	public DrawingHelper setRGB(Color color)
	{

		this.r = color.getRed();
		this.g = color.getGreen();
		this.b = color.getBlue();
		this.a = color.getAlpha();
		return this;
	}

	public DrawingHelper setDrawingX(int drawingX)
	{
		this.drawingX = drawingX;
		return this;
	}

	public DrawingHelper setDrawingY(int drawingY)
	{
		this.drawingY = drawingY;
		return this;
	}
}
