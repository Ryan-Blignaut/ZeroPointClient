package github.thesivlerecho.zeropoint.gui.old;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.render.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Matrix4f;
import net.minecraft.util.math.Vec2f;
import net.minecraft.util.math.Vector4f;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL14;

import java.awt.*;

public class GuiHelper extends DrawableHelper
{

	private static final Tessellator tessellator = Tessellator.getInstance();
	private static final BufferBuilder bufferBuilder = tessellator.getBuffer();
	private final static Vec2f[] arr = new Vec2f[30];


	public static void drawTexturedRect(float x, float y, float width, float height, float uMin, float uMax, float vMin, float vMax, int filter)
	{
		GlStateManager._enableTexture();
		GlStateManager._enableBlend();
		GL14.glBlendFuncSeparate(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, GL11.GL_ONE, GL11.GL_ONE_MINUS_SRC_ALPHA);

		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, filter);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, filter);

		Tessellator tessellator = Tessellator.getInstance();
		BufferBuilder buffer = tessellator.getBuffer();
		buffer.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_TEXTURE);
		buffer.vertex(x, y + height, 0.0D).texture(uMin, vMax).next();
		buffer.vertex(x + width, y + height, 0.0D).texture(uMax, vMax).next();
		buffer.vertex(x + width, y, 0.0D).texture(uMax, vMin).next();
		buffer.vertex(x, y, 0.0D).texture(uMin, vMin).next();
		tessellator.draw();

		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);

		GlStateManager._disableBlend();
	}


//	public static void gradientTopToBottomRectangle(MatrixStack matrixStack, int mode, Box2d box2d, int colorStart, int colorEnd)
//	{
//		Matrix4f matrix = matrixStack.peek().getModel();
//		CustomColor col1 = new CustomColor(colorStart);
//		CustomColor col2 = new CustomColor(colorEnd);
//		RenderSystem.disableTexture();
//		RenderSystem.enableBlend();
////		RenderSystem.disableAlphaTest();
//		RenderSystem.defaultBlendFunc();
//		RenderSystem.shadeModel(GL11.GL_SMOOTH);
//		bufferBuilder.begin(mode, VertexFormats.POSITION_COLOR);
//
//		topToBottomGradient(matrix, box2d, col1, col2);
//
//		bufferBuilder.end();
//		BufferRenderer.draw(bufferBuilder);
//
//		RenderSystem.shadeModel(GL11.GL_FLAT);
//		RenderSystem.disableBlend();
//		RenderSystem.enableAlphaTest();
//		RenderSystem.enableTexture();
//	}

	private static void topToBottomGradient(Matrix4f matrix, Box2d box2d, CustomColor col1, CustomColor col2)
	{
		//bottom left
		GuiHelper.bufferBuilder.vertex(matrix, box2d.getLeft(), box2d.getBottom(), 0.0F).color(col1.getRed(), col1.getGreen(), col1.getBlue(), col1.getAlpha())
				.next();
		//bottom right
		GuiHelper.bufferBuilder.vertex(matrix, box2d.getRight(), box2d.getBottom(), 0.0F).color(col1.getRed(), col1.getGreen(), col1.getBlue(), col1.getAlpha())
				.next();
		//top right
		GuiHelper.bufferBuilder.vertex(matrix, box2d.getRight(), box2d.getTop(), 0.0F).color(col2.getRed(), col2.getGreen(), col2.getBlue(), col2.getAlpha())
				.next();
		//top left
		GuiHelper.bufferBuilder.vertex(matrix, box2d.getLeft(), box2d.getTop(), 0.0F).color(col2.getRed(), col2.getGreen(), col2.getBlue(), col2.getAlpha())
				.next();
	}


	public static void drawFeatheredRect(MatrixStack matrixStack, float left, float top, float right, float bottom, float thickness, int col)
	{
//		FeatheredRect inst = FeatheredRect.INST;
//		inst.bind();
//		inst.setThickness(thickness);
//		inst.setInnerRect(left + thickness, top + thickness, right - thickness, bottom - thickness);
//		fill(matrixStack, (int) left, (int) top, (int) right, (int) bottom, col);
//		inst.unBind();
	}

	public static void drawRoundedRect(MatrixStack matrixStack, float left, float top, float right, float bottom, float radius, int color)
	{
//		RoundedRect inst = RoundedRect.INST;
//		inst.bind();
//		inst.setThickness(radius /*- 1*/);
//		inst.setInnerRect(left + radius, top + radius, right - radius, bottom - radius);
//		fillCustom(matrixStack, left, top, right, bottom, color);
//		inst.unBind();
	}

	public static void drawRoundedBoardedRect(MatrixStack matrixStack, float left, float top, float right, float bottom, float radius, int color)
	{
//		RoundBoardedRect inst = RoundBoardedRect.INST;
//		inst.bind();
//		inst.setThickness(radius /*- 1*/);
//		inst.setInnerRect(left + radius, top + radius, right - radius, bottom - radius);
//		fillCustom(matrixStack, left, top, right, bottom, color);
//		inst.unBind();
	}


	public static void drawGradientRoundedRect(MatrixStack matrixStack, float left, float top, float right, float bottom, float radius, int colorStart, int colorEnd)
	{
//		RoundedRect inst = RoundedRect.INST;
//		inst.bind();
//		inst.setThickness(radius);
//		inst.setInnerRect(left + radius, top + radius, right - radius, bottom - radius);
//		fillGradientCustom(matrixStack, left, top, right, bottom, colorStart, colorEnd);
//		inst.unBind();
	}

	public static void drawGradientBoardedRect(MatrixStack matrixStack, float left, float top, float right, float bottom, float radius, int colorStart, int colorEnd)
	{
//		RoundBoardedRect inst = RoundBoardedRect.INST;
//		inst.bind();
//		inst.setThickness(radius);
//		inst.setInnerRect(left + radius, top + radius, right - radius, bottom - radius);
//		fillGradientCustom(matrixStack, left, top, right, bottom, colorStart, colorEnd);
//		inst.unBind();
	}

	private static void fillCustom(MatrixStack matrixStack, float x1, float y1, float x2, float y2, int color)
	{
		final Matrix4f matrix = matrixStack.peek().getModel();
		float j;
		if (x1 < x2)
		{
			j = x1;
			x1 = x2;
			x2 = j;
		}

		if (y1 < y2)
		{
			j = y1;
			y1 = y2;
			y2 = j;
		}

		float f = (float) (color >> 24 & 255) / 255.0F;
		float g = (float) (color >> 16 & 255) / 255.0F;
		float h = (float) (color >> 8 & 255) / 255.0F;
		float k = (float) (color & 255) / 255.0F;
		BufferBuilder bufferBuilder = Tessellator.getInstance().getBuffer();
		RenderSystem.enableBlend();
		RenderSystem.disableTexture();
		RenderSystem.defaultBlendFunc();
		bufferBuilder.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_COLOR);
		bufferBuilder.vertex(matrix, x1, y2, 0.0F).color(g, h, k, f).next();
		bufferBuilder.vertex(matrix, x2, y2, 0.0F).color(g, h, k, f).next();
		bufferBuilder.vertex(matrix, x2, y1, 0.0F).color(g, h, k, f).next();
		bufferBuilder.vertex(matrix, x1, y1, 0.0F).color(g, h, k, f).next();
		bufferBuilder.end();
		BufferRenderer.draw(bufferBuilder);
		RenderSystem.enableTexture();
		RenderSystem.disableBlend();
	}


	public static void rectangle(MatrixStack matrixStack, int mode, Box2d box2d, int color)
	{
		Matrix4f matrix = matrixStack.peek().getModel();
		CustomColor col = new CustomColor(color);

		RenderSystem.enableBlend();
		RenderSystem.disableTexture();
		RenderSystem.defaultBlendFunc();
		bufferBuilder.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_COLOR);

		//bottom left
		bufferBuilder.vertex(matrix, box2d.getLeft(), box2d.getBottom(), 0.0F).color(col.getRed(), col.getGreen(), col.getBlue(), col.getAlpha())
				.next();
		//bottom right
		bufferBuilder.vertex(matrix, box2d.getRight(), box2d.getBottom(), 0.0F).color(col.getRed(), col.getGreen(), col.getBlue(), col.getAlpha())
				.next();
		//top right
		bufferBuilder.vertex(matrix, box2d.getRight(), box2d.getTop(), 0.0F).color(col.getRed(), col.getGreen(), col.getBlue(), col.getAlpha())
				.next();
		//top left
		bufferBuilder.vertex(matrix, box2d.getLeft(), box2d.getTop(), 0.0F).color(col.getRed(), col.getGreen(), col.getBlue(), col.getAlpha()).next();

		bufferBuilder.end();
		BufferRenderer.draw(bufferBuilder);

		RenderSystem.enableTexture();
		RenderSystem.disableBlend();

	}

//	public static void gradientLeftToRightRectangle(MatrixStack matrixStack, int mode, int x, int y, int w, int h, int colorStart, int colorEnd)
//	{
//
//		Matrix4f matrix = matrixStack.peek().getModel();
//		CustomColor col1 = new CustomColor(colorStart);
//		CustomColor col2 = new CustomColor(colorEnd);
//
//		RenderSystem.disableTexture();
//		RenderSystem.enableBlend();
////		RenderSystem.disableAlphaTest();
//		RenderSystem.defaultBlendFunc();
////		RenderSystem.shadeModel(GL11.GL_SMOOTH);
//		bufferBuilder.begin(mode, VertexFormats.POSITION_COLOR);
//
//		//bottom left
//		bufferBuilder.vertex(matrix, x, y, 0.0F).color(col1.getRed(), col1.getGreen(), col1.getBlue(), col1.getAlpha()).next();
//		//bottom right
//		bufferBuilder.vertex(matrix, w, y, 0.0F).color(col2.getRed(), col2.getGreen(), col2.getBlue(), col2.getAlpha()).next();
//		//top right
//		bufferBuilder.vertex(matrix, w, h, 0.0F).color(col2.getRed(), col2.getGreen(), col2.getBlue(), col2.getAlpha()).next();
//		//top left
//		bufferBuilder.vertex(matrix, x, h, 0.0F).color(col1.getRed(), col1.getGreen(), col1.getBlue(), col1.getAlpha()).next();
//
//		bufferBuilder.end();
//		BufferRenderer.draw(bufferBuilder);
//
//		RenderSystem.shadeModel(GL11.GL_FLAT);
//		RenderSystem.disableBlend();
//		RenderSystem.enableAlphaTest();
//		RenderSystem.enableTexture();
//	}

	public static void drawBorderedRect(MatrixStack matrices, final float x, final float y, final float x2, final float y2, final float l1,
	                                    final int col1)
	{
		//		drawRect(x, y, x2, y2, col2);
		final float f = (col1 >> 24 & 0xFF) / 255.0f;
		final float f2 = (col1 >> 16 & 0xFF) / 255.0f;
		final float f3 = (col1 >> 8 & 0xFF) / 255.0f;
		final float f4 = (col1 & 0xFF) / 255.0f;

		GL11.glEnable(0xbe2);
		GL11.glDisable(0xde1);
		GL11.glBlendFunc(770, 771);
		GL11.glEnable(0xb20);
		//		GL11.glPushMatrix();
		matrices.push();
		GL11.glColor4f(f2, f3, f4, f);
		GL11.glLineWidth(l1);
		//		GL11.glBegin(1);

		bufferBuilder.begin(VertexFormat.DrawMode.LINES, VertexFormats.POSITION_COLOR);
		bufferBuilder.vertex(matrices.peek().getModel(), x, y, 0).color(1, 1, 1, 1).next();
		bufferBuilder.vertex(matrices.peek().getModel(), x, y2, 0).color(1, 1, 1, 1).next();
		bufferBuilder.vertex(matrices.peek().getModel(), x2, y2, 0).color(1, 1, 1, 1).next();
		bufferBuilder.vertex(matrices.peek().getModel(), x2, y, 0).color(1, 1, 1, 1).next();

		bufferBuilder.vertex(matrices.peek().getModel(), x, y, 0).color(1, 1, 1, 1).next();
		bufferBuilder.vertex(matrices.peek().getModel(), x2, y, 0).color(1, 1, 1, 1).next();
		bufferBuilder.vertex(matrices.peek().getModel(), x, y2, 0).color(1, 1, 1, 1).next();
		bufferBuilder.vertex(matrices.peek().getModel(), x2, y2, 0).color(1, 1, 1, 1).next();

		tessellator.draw();

		//		GL11.glEnd();
		//		GL11.glPopMatrix();
		matrices.pop();
		GL11.glEnable(3553);
		GL11.glDisable(3042);
		GL11.glDisable(2848);
	}

	public static void drawRect(MatrixStack matrixStack, float x, float y, float w, float h, int color)
	{
		Matrix4f matrix = matrixStack.peek().getModel();

		w = x + w;
		h = y + h;

		float a = (float) (color >> 24 & 255) / 255.0F;
		float r = (float) (color >> 16 & 255) / 255.0F;
		float g = (float) (color >> 8 & 255) / 255.0F;
		float b = (float) (color & 255) / 255.0F;

		BufferBuilder bufferBuilder = Tessellator.getInstance().getBuffer();
		RenderSystem.enableBlend();
		RenderSystem.disableTexture();
		RenderSystem.defaultBlendFunc();
		bufferBuilder.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_COLOR);
		bufferBuilder.vertex(matrix, x, h, 0.0F).color(r, g, b, a).next();
		bufferBuilder.vertex(matrix, w, h, 0.0F).color(r, g, b, a).next();
		bufferBuilder.vertex(matrix, w, y, 0.0F).color(r, g, b, a).next();
		bufferBuilder.vertex(matrix, x, y, 0.0F).color(r, g, b, a).next();
		bufferBuilder.end();
		BufferRenderer.draw(bufferBuilder);
		RenderSystem.enableTexture();
		RenderSystem.disableBlend();
	}

//	public static void fillGradientCustom(MatrixStack matrixStack, float xStart, float yStart, float xEnd, float yEnd, int colorStart, int colorEnd)
//	{
//		Matrix4f model = matrixStack.peek().getModel();
//		Vector4f col1 = colourHelper(colorStart);
//		Vector4f col2 = colourHelper(colorEnd);
//		RenderSystem.disableTexture();
//		RenderSystem.enableBlend();
////		RenderSystem.disableAlphaTest();
//		RenderSystem.defaultBlendFunc();
//		RenderSystem.shadeModel(GL11.GL_SMOOTH);
//		bufferBuilder.begin(7, VertexFormats.POSITION_COLOR);
//		bufferBuilder.vertex(model, xEnd, yStart, 0).color(col1.getX(), col1.getY(), col1.getZ(), col1.getW()).next();
//		bufferBuilder.vertex(model, xStart, yStart, 0).color(col1.getX(), col1.getY(), col1.getZ(), col1.getW()).next();
//		bufferBuilder.vertex(model, xStart, yEnd, 0).color(col2.getX(), col2.getY(), col2.getZ(), col2.getW()).next();
//		bufferBuilder.vertex(model, xEnd, yEnd, 0).color(col2.getX(), col2.getY(), col2.getZ(), col2.getW()).next();
//		tessellator.draw();
//		RenderSystem.shadeModel(GL11.GL_FLAT);
//		RenderSystem.disableBlend();
//		RenderSystem.enableAlphaTest();
//		RenderSystem.enableTexture();
//	}

	public static void fill(MatrixStack matrixStack, int mode, float x1, float y1, float x2, float y2, int color)
	{

		Matrix4f model = matrixStack.peek().getModel();
		float j;
		if (x1 < x2)
		{
			j = x1;
			x1 = x2;
			x2 = j;
		}

		if (y1 < y2)
		{
			j = y1;
			y1 = y2;
			y2 = j;
		}
		Vector4f cVec = colourHelper(color);
		RenderSystem.enableBlend();
		RenderSystem.disableTexture();
		RenderSystem.defaultBlendFunc();
		bufferBuilder.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_COLOR);
		bufferBuilder.vertex(model, x1, y2, 0.0F).color(cVec.getX(), cVec.getY(), cVec.getZ(), cVec.getW()).next();
		bufferBuilder.vertex(model, x2, y2, 0.0F).color(cVec.getX(), cVec.getY(), cVec.getZ(), cVec.getW()).next();
		bufferBuilder.vertex(model, x2, y1, 0.0F).color(cVec.getX(), cVec.getY(), cVec.getZ(), cVec.getW()).next();
		bufferBuilder.vertex(model, x1, y1, 0.0F).color(cVec.getX(), cVec.getY(), cVec.getZ(), cVec.getW()).next();
		tessellator.draw();
		RenderSystem.enableTexture();
		RenderSystem.disableBlend();
	}

	public static void drawTexturedQuad(MatrixStack matrixStack, float x0, float x1, float y0, float y1, float u0, float u1, float v0, float v1)
	{
		Matrix4f model = matrixStack.peek().getModel();
		bufferBuilder.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_TEXTURE);
		bufferBuilder.vertex(model, x0, y1, 0).texture(u0, v1).next();
		bufferBuilder.vertex(model, x1, y1, 0).texture(u1, v1).next();
		bufferBuilder.vertex(model, x1, y0, 0).texture(u1, v0).next();
		bufferBuilder.vertex(model, x0, y0, 0).texture(u0, v0).next();
		bufferBuilder.end();
//		RenderSystem.enableAlphaTest();
		BufferRenderer.draw(bufferBuilder);
	}

	/*public static void drawOutline(MatrixStack matrixStack, int minXIn, int minYIn)
	{

		RenderSystem.pushMatrix();
		RenderSystem.enableBlend();
		RenderSystem.enableDepthTest();
		RenderSystem.blendFuncSeparate(GlStateManager.SrcFactor.SRC_ALPHA, GlStateManager.DstFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SrcFactor.ONE,
				GlStateManager.DstFactor.ZERO);
		RenderSystem.depthMask(false);
		RenderSystem.color4f(0, 1, 1, 0.2f);
		RenderSystem.defaultAlphaFunc();
		RenderSystem.enableAlphaTest();
		RenderSystem.disableCull();
		RenderSystem.disableTexture();
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		BufferBuilder bufferBuilder = Tessellator.getInstance().getBuffer();
		bufferBuilder.begin(GL_TRIANGLE_STRIP, VertexFormats.POSITION);
		//		voxelShape.forEachBox((minX, minY, minZ, maxX, maxY, maxZ) -> GuiHelper
		//				.drawBox1(matrixStack, bufferBuilder, minX + d - 0.005, minY + e - 0.005, minZ + f - 0.005, maxX + d + 0.005, maxY + e + 0.005,
		//						maxZ + f + 0.005, 1, 1, 1, 0.2F));
		bufferBuilder.end();
		BufferRenderer.draw(bufferBuilder);

		RenderSystem.enableCull();
		RenderSystem.disableAlphaTest();
		RenderSystem.enableAlphaTest();
		RenderSystem.disableBlend();
		RenderSystem.depthMask(true);
		RenderSystem.popMatrix();

	}
*/
	public static void drawBox1(MatrixStack matrixStack, BufferBuilder buffer, float x1, float y1, float z1, float x2, float y2, float z2, float red,
	                            float green, float blue, float alpha)
	{
		Matrix4f matrix4f = matrixStack.peek().getModel();

		buffer.vertex(matrix4f, x1, y1, z1).color(red, green, blue, alpha).next();
		buffer.vertex(matrix4f, x1, y1, z1).color(red, green, blue, alpha).next();
		buffer.vertex(matrix4f, x1, y1, z1).color(red, green, blue, alpha).next();
		buffer.vertex(matrix4f, x1, y1, z2).color(red, green, blue, alpha).next();
		buffer.vertex(matrix4f, x1, y2, z1).color(red, green, blue, alpha).next();

		buffer.vertex(matrix4f, x1, y2, z2).color(red, green, blue, alpha).next();
		buffer.vertex(matrix4f, x1, y2, z2).color(red, green, blue, alpha).next();
		buffer.vertex(matrix4f, x1, y1, z2).color(red, green, blue, alpha).next();
		buffer.vertex(matrix4f, x2, y2, z2).color(red, green, blue, alpha).next();
		buffer.vertex(matrix4f, x2, y1, z2).color(red, green, blue, alpha).next();

		buffer.vertex(matrix4f, x2, y1, z2).color(red, green, blue, alpha).next();
		buffer.vertex(matrix4f, x2, y1, z1).color(red, green, blue, alpha).next();
		buffer.vertex(matrix4f, x2, y2, z2).color(red, green, blue, alpha).next();
		buffer.vertex(matrix4f, x2, y2, z1).color(red, green, blue, alpha).next();
		buffer.vertex(matrix4f, x2, y2, z1).color(red, green, blue, alpha).next();

		buffer.vertex(matrix4f, x2, y1, z1).color(red, green, blue, alpha).next();
		buffer.vertex(matrix4f, x1, y2, z1).color(red, green, blue, alpha).next();
		buffer.vertex(matrix4f, x1, y1, z1).color(red, green, blue, alpha).next();
		buffer.vertex(matrix4f, x1, y1, z1).color(red, green, blue, alpha).next();
		buffer.vertex(matrix4f, x2, y1, z1).color(red, green, blue, alpha).next();

		buffer.vertex(matrix4f, x1, y1, z2).color(red, green, blue, alpha).next();
		buffer.vertex(matrix4f, x2, y1, z2).color(red, green, blue, alpha).next();
		buffer.vertex(matrix4f, x2, y1, z2).color(red, green, blue, alpha).next();
		buffer.vertex(matrix4f, x1, y2, z1).color(red, green, blue, alpha).next();
		buffer.vertex(matrix4f, x1, y2, z1).color(red, green, blue, alpha).next();

		buffer.vertex(matrix4f, x1, y2, z2).color(red, green, blue, alpha).next();
		buffer.vertex(matrix4f, x2, y2, z1).color(red, green, blue, alpha).next();
		buffer.vertex(matrix4f, x2, y2, z2).color(red, green, blue, alpha).next();
		buffer.vertex(matrix4f, x2, y2, z2).color(red, green, blue, alpha).next();
		buffer.vertex(matrix4f, x2, y2, z2).color(red, green, blue, alpha).next();
	}

	public static void line(int mode, float x1, float y1, float x2, float y2, int color)
	{

		float f = (float) (color >> 24 & 255) / 255.0F;
		float g = (float) (color >> 16 & 255) / 255.0F;
		float h = (float) (color >> 8 & 255) / 255.0F;
		float k = (float) (color & 255) / 255.0F;
		BufferBuilder bufferBuilder = Tessellator.getInstance().getBuffer();
		RenderSystem.enableBlend();
		RenderSystem.disableTexture();
		RenderSystem.defaultBlendFunc();
		bufferBuilder.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_COLOR);
		bufferBuilder.vertex(/*Rotation3.identity().getMatrix(),*/ x1, y1, 0.0F).color(g, h, k, f).next();
		bufferBuilder.vertex(/*Rotation3.identity().getMatrix(),*/ x2, y2, 0.0F).color(g, h, k, f).next();

		bufferBuilder.end();
		BufferRenderer.draw(bufferBuilder);
		RenderSystem.enableTexture();
		RenderSystem.disableBlend();
	}

	public static void drawTorus(MatrixStack matrixStack, float startAngle, float sizeAngle, float draws, float inner, float outer)
	{
		Matrix4f matrix4f = matrixStack.peek().getModel();
		bufferBuilder.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION);
		float draws1 = draws * (sizeAngle / 360F);
		for (int i = 0; i <= draws1; i++)
		{
			float angle = (float) Math.toRadians(startAngle + (i / draws) * 360);
			bufferBuilder.vertex(matrix4f, (float) (outer * Math.cos(angle)), (float) (outer * Math.sin(angle)), 0).next();
			bufferBuilder.vertex(matrix4f, (float) (inner * Math.cos(angle)), (float) (inner * Math.sin(angle)), 0).next();
		}
		bufferBuilder.end();
		BufferRenderer.draw(bufferBuilder);

	}

	public static void fillCircle(int mode, int x, int y, int radius, int color)
	{
		float f = (float) (color >> 24 & 255) / 255.0F;
		float g = (float) (color >> 16 & 255) / 255.0F;
		float h = (float) (color >> 8 & 255) / 255.0F;
		float k = (float) (color & 255) / 255.0F;
		BufferBuilder bufferBuilder = Tessellator.getInstance().getBuffer();
		RenderSystem.enableBlend();
		RenderSystem.disableTexture();
		RenderSystem.defaultBlendFunc();
		bufferBuilder.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_COLOR);
		for (float w = 0; w <= 360; w += 0.01)
		{
			bufferBuilder.vertex(/*Rotation3.identity().getMatrix(),*/ (float) (x + radius * Math.cos(Math.toRadians(w))),
					(float) (y - radius * Math.sin(Math.toRadians(w))), 0.0F).color(g, h, k, f).next();
		}
		bufferBuilder.end();
		BufferRenderer.draw(bufferBuilder);
		RenderSystem.enableTexture();
		RenderSystem.disableBlend();
	}

	public static void fillEllipse(MatrixStack matrixStack, int mode, float x, float y, float radiusX, float radiusY, int color)
	{
		float f = (float) (color >> 24 & 255) / 255.0F;
		float g = (float) (color >> 16 & 255) / 255.0F;
		float h = (float) (color >> 8 & 255) / 255.0F;
		float k = (float) (color & 255) / 255.0F;
		Matrix4f model = matrixStack.peek().getModel();
		Vector4f newColour = colourHelper(color);
		RenderSystem.enableBlend();
		RenderSystem.disableTexture();
		RenderSystem.defaultBlendFunc();
		bufferBuilder.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_COLOR);
		bufferBuilder.vertex(x, y, 0).color(g, h, k, f).next();
		for (int i = 0; i <= 360; ++i)
		{
			float sin = (float) (Math.sin((double) i * 3.141592653589793 / 180.0) * radiusX);
			float cos = (float) (Math.cos((double) i * 3.141592653589793 / 180.0) * radiusY);
			bufferBuilder.vertex(model, x + sin, y + cos, 0).color(g, h, k, f).next();
		}

		//		for (float i = 0; i <= 360; i += 0.01)
		//		{
		//			bufferBuilder.vertex(/*Rotation3.identity().getMatrix(),*/ ((float) (x + Math.cos(i * (Math.PI / 180)) * radiusX)),
		//					((float) (y + Math.sin(i * (Math.PI / 180)) * radiusY)), 0.0F).color(newColour.getX(), newColour.getY(), newColour.getZ(),
		//					newColour.getW()).next();
		//		}
		//		BufferRenderer.draw(bufferBuilder);
		tessellator.draw();
		RenderSystem.enableTexture();
		RenderSystem.disableBlend();
	}

	public static void fillEllipse(int mode, int x, int y, float radiusX, float radiusY, int color, int percent)
	{

		float f = (float) (color >> 24 & 255) / 255.0F;
		float g = (float) (color >> 16 & 255) / 255.0F;
		float h = (float) (color >> 8 & 255) / 255.0F;
		float k = (float) (color & 255) / 255.0F;
		BufferBuilder bufferBuilder = Tessellator.getInstance().getBuffer();
		RenderSystem.enableBlend();
		RenderSystem.disableTexture();
		RenderSystem.defaultBlendFunc();
		bufferBuilder.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_COLOR);

		for (float i = 0; i <= percent; i += 0.01)
		{
			bufferBuilder.vertex(/*Rotation3.identity().getMatrix(),*/ ((float) (x + Math.cos(i * (Math.PI / 180)) * radiusX)),
					((float) (y + Math.sin(i * (Math.PI / 180)) * radiusY)), 0.0F).color(g, h, k, f).next();
		}
		bufferBuilder.end();
		BufferRenderer.draw(bufferBuilder);
		RenderSystem.enableTexture();
		RenderSystem.disableBlend();
	}

	public static void createRoundedCorners(int num)
	{
		// Generate the corner vertexes
		float slice = (float) (Math.PI / 2 / num);
		int i;
		float a = 0;
		for (i = 0; i < num; a += slice, ++i)
		{
			arr[i] = (new Vec2f(((float) Math.cosh(a)), ((float) Math.sinh(a))));
		}
	}

	public static void glwDrawRoundedRectGradientFill(float x, float y, float width, float height, float radius, int topColor, int bottomColor)
	{
		//		float f = (float) (topColor >> 24 & 255) / 255.0F;
		//		float g = (float) (topColor >> 16 & 255) / 255.0F;
		//		float h = (float) (topColor >> 8 & 255) / 255.0F;
		//		float i = (float) (topColor & 255) / 255.0F;

		Vector4f topcol1 = colourHelper(topColor);

		float j = (float) (bottomColor >> 24 & 255) / 255.0F;
		float k = (float) (bottomColor >> 16 & 255) / 255.0F;
		float l = (float) (bottomColor >> 8 & 255) / 255.0F;
		float m = (float) (bottomColor & 255) / 255.0F;

		float left = x;
		float top = y;
		float bottom = y + height - 1;
		float right = x + width - 1;
		int r;

		BufferBuilder bufferBuilder = Tessellator.getInstance().getBuffer();
		RenderSystem.enableBlend();
		RenderSystem.disableTexture();
		RenderSystem.defaultBlendFunc();
		bufferBuilder.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_COLOR);
		for (int q = 0; q < 5; ++q)
		{

			bufferBuilder.vertex(left + radius - radius * arr[(q)].x, bottom - radius + radius * arr[(q)].y, 0).color(topcol1.getX(), topcol1.getY(),
					topcol1.getZ(), topcol1.getW()).next();
			bufferBuilder.vertex(left + radius - radius * arr[(q)].x, top + radius - radius * arr[(q)].y, 0).color(k, l, m, j).next();
		}
		for (int n = 5 - 1; n >= 0; n--)
		{
			bufferBuilder.vertex(right + radius - radius * arr[(n)].x, bottom - radius + radius * arr[(n)].y, 0).color(topcol1.getX(), topcol1.getY(),
					topcol1.getZ(), topcol1.getW()).next();
			bufferBuilder.vertex(right + radius - radius * arr[(n)].x, top + radius - radius * arr[(n)].y, 0).color(k, l, m, j).next();
		}

		// Draw right rounded side.
		//		for (r = 5 - 1; r >= 0; --r)
		//		{
		//			bufferBuilder.vertex(right + radius - radius * arr[(r)].x, bottom - radius + radius * arr[(r)].y, 0).color(f, g, h, i).next();
		//			bufferBuilder.vertex(right + radius - radius * arr[(r)].x, top + radius - radius * arr[(r)].y, 0).color(j, k, l, m).next();
		//		}

		bufferBuilder.end();
		BufferRenderer.draw(bufferBuilder);
		RenderSystem.enableTexture();
		RenderSystem.disableBlend();

	}

	private static Vector4f colourHelper(int col)
	{
		float r = (float) (col >> 16 & 255) / 255.0F;
		float g = (float) (col >> 8 & 255) / 255.0F;
		float b = (float) (col & 255) / 255.0F;
		float a = (float) (col >> 24 & 255) / 255.0F;

		return new Vector4f(r, g, b, a);
	}

	public static void drawOctagonRectFrame(MatrixStack matrixStack, float left, float top, float right, float bottom, float bevel, int color)
	{
		RenderSystem.disableTexture();
		bufferBuilder.begin(VertexFormat.DrawMode.TRIANGLE_FAN, VertexFormats.POSITION_COLOR);
		Vector4f col = colourHelper(color);
		Matrix4f model = matrixStack.peek().getModel();
		bufferBuilder.vertex(model, left, bottom - bevel, 0).color(col.getX(), col.getY(), col.getZ(), col.getW()).next();
		bufferBuilder.vertex(model, left + bevel, bottom, 0).color(col.getX(), col.getY(), col.getZ(), col.getW()).next();
		bufferBuilder.vertex(model, right - bevel, bottom, 0).color(col.getX(), col.getY(), col.getZ(), col.getW()).next();
		bufferBuilder.vertex(model, right, bottom - bevel, 0).color(col.getX(), col.getY(), col.getZ(), col.getW()).next();
		bufferBuilder.vertex(model, right, top + bevel, 0).color(col.getX(), col.getY(), col.getZ(), col.getW()).next();
		bufferBuilder.vertex(model, right - bevel, top, 0).color(col.getX(), col.getY(), col.getZ(), col.getW()).next();
		bufferBuilder.vertex(model, left + bevel, top, 0).color(col.getX(), col.getY(), col.getZ(), col.getW()).next();
		bufferBuilder.vertex(model, left, top + bevel, 0).color(col.getX(), col.getY(), col.getZ(), col.getW()).next();
		tessellator.draw();

	}

	public static void clipStart(float x, float y, float width, float height)
	{
		double scale = MinecraftClient.getInstance().getWindow().getScaleFactor();
		GL11.glEnable(GL11.GL_SCISSOR_TEST);
		GL11.glScissor((int) (x * scale), (int) (MinecraftClient.getInstance().getWindow().getFramebufferHeight() - ((y + height) * scale)),
				(int) (width * scale), (int) (height * scale));
	}

	public static void clipEnd()
	{
		GL11.glDisable(GL11.GL_SCISSOR_TEST);
	}

	public static Color lerp(Color color1, Color color2, double double3)
	{
		if (Double.isNaN(double3) || double3 < 0.0)
		{
			double3 = 0.0;
		}
		if (double3 > 1.0)
		{
			double3 = 1.0;
		}
		return new Color((int) (color1.getRed() * double3 + color2.getRed() * (1.0 - double3)),
				(int) (color1.getGreen() * double3 + color2.getGreen() * (1.0 - double3)),
				(int) (color1.getBlue() * double3 + color2.getBlue() * (1.0 - double3)),
				(int) (color1.getAlpha() * double3 + color2.getAlpha() * (1.0 - double3)));
	}


	public static Color lerp(CustomColor color1, CustomColor color2, double double3)
	{
		if (Double.isNaN(double3) || double3 < 0.0)
		{
			double3 = 0.0;
		}
		if (double3 > 1.0)
		{
			double3 = 1.0;
		}
		return new Color((int) (color1.getRed() * double3 + color2.getRed() * (1.0 - double3)),
				(int) (color1.getGreen() * double3 + color2.getGreen() * (1.0 - double3)),
				(int) (color1.getBlue() * double3 + color2.getBlue() * (1.0 - double3)),
				(int) (color1.getAlpha() * double3 + color2.getAlpha() * (1.0 - double3)));
	}


	public static Color lerp(int c1, int c2, double double3)
	{
		CustomColor color1 = new CustomColor(c1);
		CustomColor color2 = new CustomColor(c2);

		if (Double.isNaN(double3) || double3 < 0.0)
		{
			double3 = 0.0;
		}
		if (double3 > 1.0)
		{
			double3 = 1.0;
		}
		return new Color((int) (color1.getRed() * double3 + color2.getRed() * (1.0 - double3)),
				(int) (color1.getGreen() * double3 + color2.getGreen() * (1.0 - double3)),
				(int) (color1.getBlue() * double3 + color2.getBlue() * (1.0 - double3)),
				(int) (color1.getAlpha() * double3 + color2.getAlpha() * (1.0 - double3)));
	}


}
