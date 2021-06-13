/*
package github.thesivlerecho.zeropoint.gui;

import net.minecraft.client.render.BufferBuilder;
import net.minecraft.client.render.BufferRenderer;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Matrix4f;
import org.lwjgl.opengl.GL11;

import java.awt.*;

import static org.lwjgl.opengl.GL11.*;

public class RenderUtilOld
{
	private static final Tessellator tessellator = Tessellator.getInstance();
	private static final BufferBuilder bufferBuilder = tessellator.getBuffer();

	public static void enableGL2D()
	{
		glDisable(GL_DEPTH_TEST);
		glEnable(GL_BLEND);
		glDisable(GL_TEXTURE_2D);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		glDepthMask(true);
		glEnable(GL_LINE_SMOOTH);
		glHint(GL_LINE_SMOOTH_HINT, GL_NICEST);
		glHint(GL_POLYGON_SMOOTH_HINT, GL_NICEST);
	}

	public static void disableGL2D()
	{
		glEnable(GL_TEXTURE_2D);
		glDisable(GL_BLEND);
		glEnable(GL_DEPTH_TEST);
		glDisable(GL_LINE_SMOOTH);
		glHint(GL_LINE_SMOOTH_HINT, GL_DONT_CARE);
		glHint(GL_POLYGON_SMOOTH_HINT, GL_DONT_CARE);
	}

	public static void drawRectCorners(MatrixStack matrixStack, float x, float y, float x1, float y1, int lineSize, int color)
	{

		drawRect(matrixStack, x, y, x1, y1, Color.cyan.getRGB());

		float width = 2;
		//		lineSize =1;
		drawRect(matrixStack, x, y, x + lineSize, y + width, color);
		drawRect(matrixStack, x, y, x + width, y + lineSize, color);

		//		drawRect(matrixStack, x1, y, x1 - lineSize + width, y + width, color);
		//		drawRect(matrixStack, x1, y, x1 + width, y + lineSize, color);

//		drawRect(matrixStack, x, y1, x + lineSize, y1 - width, color);
		drawRect(matrixStack, x, y1, x - lineSize, y1, color);

		//		drawRect(matrixStack, x, y1, x + width, y1 + lineSize, color);

		drawRect(matrixStack, x1, y1, x1 - lineSize + width, y1 + width, color);
		drawRect(matrixStack, x1, y1, x1 + width, y1 + lineSize, color);



		*/
/*drawRect(matrixStack, x, y1, x + lineSize, y1 + width, color);
		drawRect(matrixStack, x, y1 - lineSize, x + width, y1, color);

		drawRect(matrixStack, x1 - lineSize, y, x1, y + width, Color.red.getRGB());
		drawRect(matrixStack, x1, y, x1 + width, y + lineSize, Color.red.getRGB());

		drawRect(matrixStack, x1 - lineSize, y1, x1, y1 + width, Color.red.getRGB());
		drawRect(matrixStack, x1, y1-lineSize, x1+width , y1, Color.red.getRGB());*//*


		//		drawRect(matrixStack, x1, y, x1 - lineSize, 2, color);
		//		drawRect(matrixStack, x1, y, 2, y + lineSize, color);

	}

	public static void drawRect(MatrixStack matrixStack, float x, float y, float x1, float y1, int color)
	{
		enableGL2D();
		Matrix4f matrix = matrixStack.peek().getModel();
		CustomColor col = new CustomColor(color);
		float j;
		if (x1 < x)
		{
			j = x;
			x = x1;
			x1 = j;
		}

		if (y1 < y)
		{
			j = y;
			y = y1;
			y1 = j;
		}

		bufferBuilder.begin(7, VertexFormats.POSITION_COLOR);
		drawRect(matrix, x, y, x1, y1, col);
		bufferBuilder.end();
		BufferRenderer.draw(bufferBuilder);

		disableGL2D();
	}

	private static void drawRect(Matrix4f matrix, float x, float y, float x1, float y1, CustomColor col)
	{

		//bottom left
		bufferBuilder.vertex(matrix, x, y1, 0.0F).color(col.getRed(), col.getGreen(), col.getBlue(), col.getAlpha()).next();
		//bottom right
		bufferBuilder.vertex(matrix, x1, y1, 0.0F).color(col.getRed(), col.getGreen(), col.getBlue(), col.getAlpha()).next();
		//top right
		bufferBuilder.vertex(matrix, x1, y, 0.0F).color(col.getRed(), col.getGreen(), col.getBlue(), col.getAlpha()).next();
		//top left
		bufferBuilder.vertex(matrix, x, y, 0.0F).color(col.getRed(), col.getGreen(), col.getBlue(), col.getAlpha()).next();
	}

	public static void drawTorus(MatrixStack matrixStack, float startAngle, float sizeAngle, float draws, float inner, float outer,
	                             CustomColor innerColor, CustomColor outerColor)
	{
		enableGL2D();
		glShadeModel(GL_SMOOTH);
		Matrix4f matrix4f = matrixStack.peek().getModel();
		bufferBuilder.begin(GL11.GL_QUAD_STRIP, VertexFormats.POSITION_COLOR);
		float draws1 = draws * (sizeAngle / 360F);

		for (int i = 0; i <= draws1; i++)
		{
			float angle = (float) Math.toRadians(startAngle + (i / draws) * 360);
			bufferBuilder.vertex(matrix4f, (float) (outer * Math.cos(angle)), (float) (outer * Math.sin(angle)), 0).color(innerColor.getRed(),
					innerColor.getGreen(), innerColor.getBlue(), innerColor.getAlpha()).next();
			bufferBuilder.vertex(matrix4f, (float) (inner * Math.cos(angle)), (float) (inner * Math.sin(angle)), 0).color(outerColor.getRed(),
					outerColor.getGreen(), outerColor.getBlue(), outerColor.getAlpha()).next();
		}
		bufferBuilder.end();
		BufferRenderer.draw(bufferBuilder);
		glShadeModel(GL_FLAT);

		disableGL2D();
	}

	*/
/*public static void draw2DCorner(Entity e, double posX, double posY, double posZ, int color)
	{
		GlStateManager.pushMatrix();
		GlStateManager.translate(posX, posY, posZ);
		GL11.glNormal3f(0.0F, 0.0F, 0.0F);
		GlStateManager.rotate(-RenderManager.playerViewY, 0.0F, 1.0F, 0.0F);
		GlStateManager.scale(-0.1D, -0.1D, 0.1D);
		GL11.glDisable(0xb50);
		GL11.glDisable(0xb71);
		GL11.glEnable(0xbe2);
		GL11.glBlendFunc(770, 771);
		GlStateManager.depthMask(true);
		drawRect(7.0D, -20.0D, 7.300000190734863D, -17.5D, color);
		drawRect(-7.300000190734863D, -20.0D, -7.0D, -17.5D, color);
		drawRect(4.0D, -20.299999237060547D, 7.300000190734863D, -20.0D, color);
		drawRect(-7.300000190734863D, -20.299999237060547D, -4.0D, -20.0D, color);
		drawRect(-7.0D, 3.0D, -4.0D, 3.299999952316284D, color);
		drawRect(4.0D, 3.0D, 7.0D, 3.299999952316284D, color);
		drawRect(-7.300000190734863D, 0.8D, -7.0D, 3.299999952316284D, color);
		drawRect(7.0D, 0.5D, 7.300000190734863D, 3.299999952316284D, color);
		drawRect(7.0D, -20.0D, 7.300000190734863D, -17.5D, color);
		drawRect(-7.300000190734863D, -20.0D, -7.0D, -17.5D, color);
		drawRect(4.0D, -20.299999237060547D, 7.300000190734863D, -20.0D, color);
		drawRect(-7.300000190734863D, -20.299999237060547D, -4.0D, -20.0D, color);
		drawRect(-7.0D, 3.0D, -4.0D, 3.299999952316284D, color);
		drawRect(4.0D, 3.0D, 7.0D, 3.299999952316284D, color);
		drawRect(-7.300000190734863D, 0.8D, -7.0D, 3.299999952316284D, color);
		drawRect(7.0D, 0.5D, 7.300000190734863D, 3.299999952316284D, color);
		GL11.glDisable(3042);
		GL11.glEnable(2929);
		GlStateManager.popMatrix();
	}

	public static void drawRect(double x2, double y2, double x1, double y1, int color)
	{
		enableGL2D();
		glColor(color);
		drawRect(x2, y2, x1, y1);
		disableGL2D();
	}

	private static void drawRect(double x2, double y2, double x1, double y1)
	{
		GL11.glBegin(7);
		GL11.glVertex2d(x2, y1);
		GL11.glVertex2d(x1, y1);
		GL11.glVertex2d(x1, y2);
		GL11.glVertex2d(x2, y2);
		GL11.glEnd();
	}

	public static void glColor(int hex)
	{
		float alpha = (float) (hex >> 24 & 255) / 255.0F;
		float red = (float) (hex >> 16 & 255) / 255.0F;
		float green = (float) (hex >> 8 & 255) / 255.0F;
		float blue = (float) (hex & 255) / 255.0F;
		GL11.glColor4f(red, green, blue, alpha);
	}

	public static void drawRect(float x, float y, float x1, float y1, int color)
	{
		enableGL2D();
		Helper.colorUtils().glColor(color);
		drawRect(x, y, x1, y1);
		disableGL2D();
	}

	public static void drawBorderedRect(float x, float y, float x1, float y1, float width, int borderColor)
	{
		enableGL2D();
		Helper.colorUtils().glColor(borderColor);
		drawRect(x + width, y, x1 - width, y + width);
		drawRect(x, y, x + width, y1);
		drawRect(x1 - width, y, x1, y1);
		drawRect(x + width, y1 - width, x1 - width, y1);
		disableGL2D();
	}

	public static void drawBorderedRect(float x, float y, float x1, float y1, float width, int internalColor, int borderColor)
	{
		enableGL2D();
		glColor(internalColor);
		drawRect(x + width, y + width, x1 - width, y1 - width);
		glColor(borderColor);
		drawRect(x + width, y, x1 - width, y + width);
		drawRect(x, y, x + width, y1);
		drawRect(x1 - width, y, x1, y1);
		drawRect(x + width, y1 - width, x1 - width, y1);
		disableGL2D();
	}

	public static void drawBorderedRect(double x, double y, double x1, double y1, double width, int internalColor, int borderColor)
	{
		enableGL2D();
		glColor(internalColor);
		drawRect(x + width, y + width, x1 - width, y1 - width);
		glColor(borderColor);
		drawRect(x + width, y, x1 - width, y + width);
		drawRect(x, y, x + width, y1);
		drawRect(x1 - width, y, x1, y1);
		drawRect(x + width, y1 - width, x1 - width, y1);
		disableGL2D();
	}

	public static void drawBorderedRect(float x, float y, float x1, float y1, int insideC, int borderC)
	{
		enableGL2D();
		x *= 2.0F;
		x1 *= 2.0F;
		y *= 2.0F;
		y1 *= 2.0F;
		GL11.glScalef(0.5F, 0.5F, 0.5F);
		drawVLine(x, y, y1, borderC);
		drawVLine(x1 - 1.0F, y, y1, borderC);
		drawHLine(x, x1 - 1.0F, y, borderC);
		drawHLine(x, x1 - 2.0F, y1 - 1.0F, borderC);
		drawRect(x + 1.0F, y + 1.0F, x1 - 1.0F, y1 - 1.0F, insideC);
		GL11.glScalef(2.0F, 2.0F, 2.0F);
		disableGL2D();
	}

	public static void drawGradientRect(float x, float y, float x1, float y1, int topColor, int bottomColor)
	{
		enableGL2D();
		GL11.glShadeModel(7425);
		GL11.glBegin(7);
		Helper.colorUtils().glColor(topColor);
		GL11.glVertex2f(x, y1);
		GL11.glVertex2f(x1, y1);
		Helper.colorUtils().glColor(bottomColor);
		GL11.glVertex2f(x1, y);
		GL11.glVertex2f(x, y);
		GL11.glEnd();
		GL11.glShadeModel(7424);
		disableGL2D();
	}

	public static void drawHLine(float x, float y, float x1, int y1)
	{
		if (y < x)
		{
			float var5 = x;
			x = y;
			y = var5;
		}

		drawRect(x, x1, y + 1.0F, x1 + 1.0F, y1);
	}

	public static void drawVLine(float x, float y, float x1, int y1)
	{
		if (x1 < y)
		{
			float var5 = y;
			y = x1;
			x1 = var5;
		}

		drawRect(x, y + 1.0F, x + 1.0F, x1, y1);
	}

	public static void drawHLine(float x, float y, float x1, int y1, int y2)
	{
		if (y < x)
		{
			float var5 = x;
			x = y;
			y = var5;
		}

		drawGradientRect(x, x1, y + 1.0F, x1 + 1.0F, y1, y2);
	}

	public static void drawRect(float x, float y, float x1, float y1)
	{
		GL11.glBegin(7);
		GL11.glVertex2f(x, y1);
		GL11.glVertex2f(x1, y1);
		GL11.glVertex2f(x1, y);
		GL11.glVertex2f(x, y);
		GL11.glEnd();
	}

	public static void drawTri(double x1, double y1, double x2, double y2, double x3, double y3, double width, Color c)
	{
		GL11.glEnable(3042);
		GL11.glDisable(3553);
		GL11.glEnable(2848);
		GL11.glBlendFunc(770, 771);
		Helper.colorUtils().glColor(c);
		GL11.glLineWidth((float) width);
		GL11.glBegin(3);
		GL11.glVertex2d(x1, y1);
		GL11.glVertex2d(x2, y2);
		GL11.glVertex2d(x3, y3);
		GL11.glEnd();
		GL11.glDisable(2848);
		GL11.glEnable(3553);
		GL11.glDisable(3042);
	}*//*

}
*/
