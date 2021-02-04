package github.thesivlerecho.zeropoint.mod;

import github.thesivlerecho.zeropoint.gui.helper.ColourHelper;
import github.thesivlerecho.zeropoint.util.ColourUtil;
import github.thesivlerecho.zeropoint.util.RenderUtil;
import net.minecraft.client.render.BufferBuilder;
import net.minecraft.client.render.BufferRenderer;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Matrix4f;
import org.lwjgl.opengl.GL11;

import java.awt.*;

import static org.lwjgl.opengl.GL11.*;

public class BlockOverlay
{

	//settings: outline t/f , outline color ; fill t/f , fill color ; line width

	public static void renderBoundingBox(MatrixStack matrixStack, BufferBuilder buffer, float x1, float y1, float z1, float x2, float y2, float z2)
	{
//	code to render custom bounding box
		Matrix4f matrix4f = matrixStack.peek().getModel();
		RenderUtil.enableGL2D();
		buffer.begin(GL11.GL_TRIANGLE_STRIP, VertexFormats.POSITION_COLOR);
		drawBox1(matrix4f, buffer, x1, y1, z1, x2, y2, z2, new ColourUtil(new Color(200, 0, 0, 100)), new ColourUtil(new Color(0, 110, 0, 100)));
		buffer.end();
		BufferRenderer.draw(buffer);
		RenderUtil.disableGL2D();
	}

	public static void renderBoundingQuads(MatrixStack matrixStack, BufferBuilder buffer, Box box)
	{
		Matrix4f matrix4f = matrixStack.peek().getModel();
		RenderUtil.enableGL2D();
		final int colorTop = -804318950;
		final int colorBot = Color.cyan.getRGB();
		final float topR = ColourHelper.getRedFloat(colorTop);
		final float topG = ColourHelper.getGreenFloat(colorTop);
		final float topB = ColourHelper.getBlueFloat(colorTop);
		final float topA = ColourHelper.getAlphaFloat(colorTop);

		final float botR = ColourHelper.getRedFloat(colorBot);
		final float botG = ColourHelper.getGreenFloat(colorBot);
		final float botB = ColourHelper.getBlueFloat(colorBot);
		final float botA = ColourHelper.getAlphaFloat(colorBot);
		glShadeModel(GL_SMOOTH);
//		Y
//		bottom of block
		buffer.begin(GL11.GL_QUADS, VertexFormats.POSITION_COLOR);
		buffer.vertex(matrix4f, (float) box.minX, (float) box.minY, (float) box.minZ).color(botR, botG, botB, botA).next();
		buffer.vertex(matrix4f, (float) box.maxX, (float) box.minY, (float) box.minZ).color(botR, botG, botB, botA).next();
		buffer.vertex(matrix4f, (float) box.maxX, (float) box.minY, (float) box.maxZ).color(botR, botG, botB, botA).next();
		buffer.vertex(matrix4f, (float) box.minX, (float) box.minY, (float) box.maxZ).color(botR, botG, botB, botA).next();
		buffer.end();
		BufferRenderer.draw(buffer);
//		top of block
		buffer.begin(GL11.GL_QUADS, VertexFormats.POSITION_COLOR);
		buffer.vertex(matrix4f, (float) box.minX, (float) box.maxY, (float) box.maxZ).color(topR, topG, topB, topA).next();
		buffer.vertex(matrix4f, (float) box.maxX, (float) box.maxY, (float) box.maxZ).color(topR, topG, topB, topA).next();
		buffer.vertex(matrix4f, (float) box.maxX, (float) box.maxY, (float) box.minZ).color(topR, topG, topB, topA).next();
		buffer.vertex(matrix4f, (float) box.minX, (float) box.maxY, (float) box.minZ).color(topR, topG, topB, topA).next();
		buffer.end();
		BufferRenderer.draw(buffer);
//      facing east (towards positive X)
		buffer.begin(GL11.GL_QUADS, VertexFormats.POSITION_COLOR);
		buffer.vertex(matrix4f, (float) box.minX, (float) box.minY, (float) box.maxZ).color(botR, botG, botB, botA).next();
		buffer.vertex(matrix4f, (float) box.minX, (float) box.maxY, (float) box.maxZ).color(topR, topG, topB, topA).next();
		buffer.vertex(matrix4f, (float) box.minX, (float) box.maxY, (float) box.minZ).color(topR, topG, topB, topA).next();
		buffer.vertex(matrix4f, (float) box.minX, (float) box.minY, (float) box.minZ).color(botR, botG, botB, botA).next();
		buffer.end();
		BufferRenderer.draw(buffer);
//      facing west (towards negative X)
		buffer.begin(GL11.GL_QUADS, VertexFormats.POSITION_COLOR);
		buffer.vertex(matrix4f, (float) box.maxX, (float) box.minY, (float) box.minZ).color(botR, botG, botB, botA).next();
		buffer.vertex(matrix4f, (float) box.maxX, (float) box.maxY, (float) box.minZ).color(topR, topG, topB, topA).next();
		buffer.vertex(matrix4f, (float) box.maxX, (float) box.maxY, (float) box.maxZ).color(topR, topG, topB, topA).next();
		buffer.vertex(matrix4f, (float) box.maxX, (float) box.minY, (float) box.maxZ).color(botR, botG, botB, botA).next();
		buffer.end();
		BufferRenderer.draw(buffer);

		buffer.begin(GL11.GL_QUADS, VertexFormats.POSITION_COLOR);
		buffer.vertex(matrix4f, (float) box.minX, (float) box.maxY, (float) box.minZ).color(topR, topG, topB, topA).next();
		buffer.vertex(matrix4f, (float) box.maxX, (float) box.maxY, (float) box.minZ).color(topR, topG, topB, topA).next();
		buffer.vertex(matrix4f, (float) box.maxX, (float) box.minY, (float) box.minZ).color(botR, botG, botB, botA).next();
		buffer.vertex(matrix4f, (float) box.minX, (float) box.minY, (float) box.minZ).color(botR, botG, botB, botA).next();
		buffer.end();
		BufferRenderer.draw(buffer);

		buffer.begin(GL11.GL_QUADS, VertexFormats.POSITION_COLOR);
		buffer.vertex(matrix4f, (float) box.minX, (float) box.minY, (float) box.maxZ).color(botR, botG, botB, botA).next();
		buffer.vertex(matrix4f, (float) box.maxX, (float) box.minY, (float) box.maxZ).color(botR, botG, botB, botA).next();
		buffer.vertex(matrix4f, (float) box.maxX, (float) box.maxY, (float) box.maxZ).color(topR, topG, topB, topA).next();
		buffer.vertex(matrix4f, (float) box.minX, (float) box.maxY, (float) box.maxZ).color(topR, topG, topB, topA).next();
		buffer.end();
		BufferRenderer.draw(buffer);

		glShadeModel(GL_FLAT);
		RenderUtil.disableGL2D();
	}


	public static void drawBox1(Matrix4f matrix4f, BufferBuilder buffer, float x1, float y1, float z1, float x2, float y2, float z2, ColourUtil colour, ColourUtil colour1)
	{

		buffer.vertex(matrix4f, x1, y1, z1).color(colour.getR(), colour.getG(), colour.getB(), colour.getA()).next();
		buffer.vertex(matrix4f, x1, y1, z1).color(colour.getR(), colour.getG(), colour.getB(), colour.getA()).next();
		buffer.vertex(matrix4f, x1, y1, z1).color(colour.getR(), colour.getG(), colour.getB(), colour.getA()).next();
		buffer.vertex(matrix4f, x1, y1, z2).color(colour.getR(), colour.getG(), colour.getB(), colour.getA()).next();
		buffer.vertex(matrix4f, x1, y2, z1).color(colour.getR(), colour.getG(), colour.getB(), colour.getA()).next();
		buffer.vertex(matrix4f, x1, y2, z2).color(colour1.getR(), colour1.getG(), colour1.getB(), colour1.getA()).next();

		buffer.vertex(matrix4f, x1, y2, z2).color(colour.getR(), colour.getG(), colour.getB(), colour.getA()).next();
		buffer.vertex(matrix4f, x1, y1, z2).color(colour.getR(), colour.getG(), colour.getB(), colour.getA()).next();
		buffer.vertex(matrix4f, x2, y2, z2).color(colour.getR(), colour.getG(), colour.getB(), colour.getA()).next();
		buffer.vertex(matrix4f, x2, y1, z2).color(colour.getR(), colour.getG(), colour.getB(), colour.getA()).next();
		buffer.vertex(matrix4f, x2, y1, z2).color(colour.getR(), colour.getG(), colour.getB(), colour.getA()).next();
		buffer.vertex(matrix4f, x2, y1, z1).color(colour.getR(), colour.getG(), colour.getB(), colour.getA()).next();

		buffer.vertex(matrix4f, x2, y2, z2).color(colour.getR(), colour.getG(), colour.getB(), colour.getA()).next();
		buffer.vertex(matrix4f, x2, y2, z1).color(colour.getR(), colour.getG(), colour.getB(), colour.getA()).next();
		buffer.vertex(matrix4f, x2, y2, z1).color(colour.getR(), colour.getG(), colour.getB(), colour.getA()).next();
		buffer.vertex(matrix4f, x2, y1, z1).color(colour.getR(), colour.getG(), colour.getB(), colour.getA()).next();
		buffer.vertex(matrix4f, x1, y2, z1).color(colour.getR(), colour.getG(), colour.getB(), colour.getA()).next();
		buffer.vertex(matrix4f, x1, y1, z1).color(colour.getR(), colour.getG(), colour.getB(), colour.getA()).next();

		buffer.vertex(matrix4f, x1, y1, z1).color(colour.getR(), colour.getG(), colour.getB(), colour.getA()).next();
		buffer.vertex(matrix4f, x2, y1, z1).color(colour.getR(), colour.getG(), colour.getB(), colour.getA()).next();
		buffer.vertex(matrix4f, x1, y1, z2).color(colour.getR(), colour.getG(), colour.getB(), colour.getA()).next();
		buffer.vertex(matrix4f, x2, y1, z2).color(colour.getR(), colour.getG(), colour.getB(), colour.getA()).next();
		buffer.vertex(matrix4f, x2, y1, z2).color(colour.getR(), colour.getG(), colour.getB(), colour.getA()).next();
		buffer.vertex(matrix4f, x1, y2, z1).color(colour.getR(), colour.getG(), colour.getB(), colour.getA()).next();

		buffer.vertex(matrix4f, x1, y2, z1).color(colour.getR(), colour.getG(), colour.getB(), colour.getA()).next();
		buffer.vertex(matrix4f, x1, y2, z2).color(colour.getR(), colour.getG(), colour.getB(), colour.getA()).next();
		buffer.vertex(matrix4f, x2, y2, z1).color(colour.getR(), colour.getG(), colour.getB(), colour.getA()).next();
		buffer.vertex(matrix4f, x2, y2, z2).color(colour.getR(), colour.getG(), colour.getB(), colour.getA()).next();
		buffer.vertex(matrix4f, x2, y2, z2).color(colour.getR(), colour.getG(), colour.getB(), colour.getA()).next();
		buffer.vertex(matrix4f, x2, y2, z2).color(colour.getR(), colour.getG(), colour.getB(), colour.getA()).next();
	}


}
