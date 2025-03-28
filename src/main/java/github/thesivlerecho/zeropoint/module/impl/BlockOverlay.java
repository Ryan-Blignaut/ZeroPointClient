package github.thesivlerecho.zeropoint.module.impl;

import github.thesivlerecho.zeropoint.config.selector.ConfigOption;
import github.thesivlerecho.zeropoint.event.EventListener;
import github.thesivlerecho.zeropoint.event.events.BlockOutlineEvent;
import github.thesivlerecho.zeropoint.module.BaseModule;
import github.thesivlerecho.zeropoint.module.ModCategory;
import github.thesivlerecho.zeropoint.module.ZPModule;
import github.thesivlerecho.zeropoint.util.ColourUtil;
import net.minecraft.client.render.BufferBuilder;
import net.minecraft.client.render.BufferRenderer;
import net.minecraft.client.render.VertexFormat;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Matrix4f;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.awt.*;

@ZPModule(name = "Custom block highlight", category = ModCategory.RENDER)
public class BlockOverlay extends BaseModule
{
	@ConfigOption
	private boolean renderOutline = false, renderCustom = false;


	@EventListener
	public void renderBlockOutline(BlockOutlineEvent event)
	{
		final CallbackInfo callbackInfo = event.callbackInfo();
		if (renderOutline)
			callbackInfo.cancel();
		else if (renderCustom)
		{
			callbackInfo.cancel();

		}
	}


	//settings: outline t/f , outline color ; fill t/f , fill color ; line width


	public static void renderBoundingBox(MatrixStack matrixStack, BufferBuilder buffer, float x1, float y1, float z1, float x2, float y2, float z2)
	{
//	code to render custom bounding box
		Matrix4f matrix4f = matrixStack.peek().getModel();
//		RenderUtil.enableGL2D();
		buffer.begin(VertexFormat.DrawMode.TRIANGLE_STRIP, VertexFormats.POSITION_COLOR);
		drawBox1(matrix4f, buffer, x1, y1, z1, x2, y2, z2, new ColourUtil(new Color(200, 0, 0, 100)), new ColourUtil(new Color(0, 110, 0, 100)));
		buffer.end();
		BufferRenderer.draw(buffer);
//		RenderUtil.disableGL2D();
	}

	public static void renderBoundingQuads(MatrixStack matrixStack, BufferBuilder buffer, Box box)
	{
		final Matrix4f model = matrixStack.peek().getModel();
		final ColourUtil colourUtil = new ColourUtil(new Color(120, 0, 0, 80));

//		final BlurPostprocessShader shader1 = ShaderManager.getShader(BlurPostprocessShader.class, ZeroPointShader.BLUR);
//		final Framebuffer framebuffer = MinecraftClient.getInstance().getFramebuffer();
//		shader1.setUp(framebuffer, framebuffer, 12, new Vec2f(1, 1));
//		shader1.bind();

		buffer.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_COLOR);
		buffer.vertex(model, (float) box.minX, (float) box.minY, (float) box.minZ).color(colourUtil.getR(), colourUtil.getG(), colourUtil.getB(), colourUtil.getA()).next();
		buffer.vertex(model, (float) box.maxX, (float) box.minY, (float) box.minZ).color(colourUtil.getR(), colourUtil.getG(), colourUtil.getB(), colourUtil.getA()).next();
		buffer.vertex(model, (float) box.maxX, (float) box.minY, (float) box.maxZ).color(colourUtil.getR(), colourUtil.getG(), colourUtil.getB(), colourUtil.getA()).next();
		buffer.vertex(model, (float) box.minX, (float) box.minY, (float) box.maxZ).color(colourUtil.getR(), colourUtil.getG(), colourUtil.getB(), colourUtil.getA()).next();
		buffer.end();
		BufferRenderer.draw(buffer);

//		top of block
		buffer.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_COLOR);
		buffer.vertex(model, (float) box.minX, (float) box.maxY, (float) box.maxZ).color(colourUtil.getR(), colourUtil.getG(), colourUtil.getB(), colourUtil.getA()).next();
		buffer.vertex(model, (float) box.maxX, (float) box.maxY, (float) box.maxZ).color(colourUtil.getR(), colourUtil.getG(), colourUtil.getB(), colourUtil.getA()).next();
		buffer.vertex(model, (float) box.maxX, (float) box.maxY, (float) box.minZ).color(colourUtil.getR(), colourUtil.getG(), colourUtil.getB(), colourUtil.getA()).next();
		buffer.vertex(model, (float) box.minX, (float) box.maxY, (float) box.minZ).color(colourUtil.getR(), colourUtil.getG(), colourUtil.getB(), colourUtil.getA()).next();
		buffer.end();
		BufferRenderer.draw(buffer);

//      facing east (towards positive X)
		buffer.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_COLOR);
		buffer.vertex(model, (float) box.minX, (float) box.minY, (float) box.maxZ).color(colourUtil.getR(), colourUtil.getG(), colourUtil.getB(), colourUtil.getA()).next();
		buffer.vertex(model, (float) box.minX, (float) box.maxY, (float) box.maxZ).color(colourUtil.getR(), colourUtil.getG(), colourUtil.getB(), colourUtil.getA()).next();
		buffer.vertex(model, (float) box.minX, (float) box.maxY, (float) box.minZ).color(colourUtil.getR(), colourUtil.getG(), colourUtil.getB(), colourUtil.getA()).next();
		buffer.vertex(model, (float) box.minX, (float) box.minY, (float) box.minZ).color(colourUtil.getR(), colourUtil.getG(), colourUtil.getB(), colourUtil.getA()).next();
		buffer.end();
		BufferRenderer.draw(buffer);

//      facing west (towards negative X)
		buffer.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_COLOR);
		buffer.vertex(model, (float) box.maxX, (float) box.minY, (float) box.minZ).color(colourUtil.getR(), colourUtil.getG(), colourUtil.getB(), colourUtil.getA()).next();
		buffer.vertex(model, (float) box.maxX, (float) box.maxY, (float) box.minZ).color(colourUtil.getR(), colourUtil.getG(), colourUtil.getB(), colourUtil.getA()).next();
		buffer.vertex(model, (float) box.maxX, (float) box.maxY, (float) box.maxZ).color(colourUtil.getR(), colourUtil.getG(), colourUtil.getB(), colourUtil.getA()).next();
		buffer.vertex(model, (float) box.maxX, (float) box.minY, (float) box.maxZ).color(colourUtil.getR(), colourUtil.getG(), colourUtil.getB(), colourUtil.getA()).next();
		buffer.end();
		BufferRenderer.draw(buffer);

		buffer.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_COLOR);
		buffer.vertex(model, (float) box.minX, (float) box.maxY, (float) box.minZ).color(colourUtil.getR(), colourUtil.getG(), colourUtil.getB(), colourUtil.getA()).next();
		buffer.vertex(model, (float) box.maxX, (float) box.maxY, (float) box.minZ).color(colourUtil.getR(), colourUtil.getG(), colourUtil.getB(), colourUtil.getA()).next();
		buffer.vertex(model, (float) box.maxX, (float) box.minY, (float) box.minZ).color(colourUtil.getR(), colourUtil.getG(), colourUtil.getB(), colourUtil.getA()).next();
		buffer.vertex(model, (float) box.minX, (float) box.minY, (float) box.minZ).color(colourUtil.getR(), colourUtil.getG(), colourUtil.getB(), colourUtil.getA()).next();
		buffer.end();
		BufferRenderer.draw(buffer);

		buffer.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_COLOR);
		buffer.vertex(model, (float) box.minX, (float) box.minY, (float) box.maxZ).color(colourUtil.getR(), colourUtil.getG(), colourUtil.getB(), colourUtil.getA()).next();
		buffer.vertex(model, (float) box.maxX, (float) box.minY, (float) box.maxZ).color(colourUtil.getR(), colourUtil.getG(), colourUtil.getB(), colourUtil.getA()).next();
		buffer.vertex(model, (float) box.maxX, (float) box.maxY, (float) box.maxZ).color(colourUtil.getR(), colourUtil.getG(), colourUtil.getB(), colourUtil.getA()).next();
		buffer.vertex(model, (float) box.minX, (float) box.maxY, (float) box.maxZ).color(colourUtil.getR(), colourUtil.getG(), colourUtil.getB(), colourUtil.getA()).next();
		buffer.end();
		BufferRenderer.draw(buffer);
//		shader1.bind();


	}
	/*{
		Matrix4f matrix4f = matrixStack.peek().getModel();
//		RenderUtil.enableGL2D();
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
		buffer.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_COLOR);
		buffer.vertex(matrix4f, (float) box.minX, (float) box.minY, (float) box.minZ).color(botR, botG, botB, botA).next();
		buffer.vertex(matrix4f, (float) box.maxX, (float) box.minY, (float) box.minZ).color(botR, botG, botB, botA).next();
		buffer.vertex(matrix4f, (float) box.maxX, (float) box.minY, (float) box.maxZ).color(botR, botG, botB, botA).next();
		buffer.vertex(matrix4f, (float) box.minX, (float) box.minY, (float) box.maxZ).color(botR, botG, botB, botA).next();
		buffer.end();
		BufferRenderer.draw(buffer);
//		top of block
		buffer.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_COLOR);
		buffer.vertex(matrix4f, (float) box.minX, (float) box.maxY, (float) box.maxZ).color(topR, topG, topB, topA).next();
		buffer.vertex(matrix4f, (float) box.maxX, (float) box.maxY, (float) box.maxZ).color(topR, topG, topB, topA).next();
		buffer.vertex(matrix4f, (float) box.maxX, (float) box.maxY, (float) box.minZ).color(topR, topG, topB, topA).next();
		buffer.vertex(matrix4f, (float) box.minX, (float) box.maxY, (float) box.minZ).color(topR, topG, topB, topA).next();
		buffer.end();
		BufferRenderer.draw(buffer);
//      facing east (towards positive X)
		buffer.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_COLOR);
		buffer.vertex(matrix4f, (float) box.minX, (float) box.minY, (float) box.maxZ).color(botR, botG, botB, botA).next();
		buffer.vertex(matrix4f, (float) box.minX, (float) box.maxY, (float) box.maxZ).color(topR, topG, topB, topA).next();
		buffer.vertex(matrix4f, (float) box.minX, (float) box.maxY, (float) box.minZ).color(topR, topG, topB, topA).next();
		buffer.vertex(matrix4f, (float) box.minX, (float) box.minY, (float) box.minZ).color(botR, botG, botB, botA).next();
		buffer.end();
		BufferRenderer.draw(buffer);
//      facing west (towards negative X)
		buffer.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_COLOR);
		buffer.vertex(matrix4f, (float) box.maxX, (float) box.minY, (float) box.minZ).color(botR, botG, botB, botA).next();
		buffer.vertex(matrix4f, (float) box.maxX, (float) box.maxY, (float) box.minZ).color(topR, topG, topB, topA).next();
		buffer.vertex(matrix4f, (float) box.maxX, (float) box.maxY, (float) box.maxZ).color(topR, topG, topB, topA).next();
		buffer.vertex(matrix4f, (float) box.maxX, (float) box.minY, (float) box.maxZ).color(botR, botG, botB, botA).next();
		buffer.end();
		BufferRenderer.draw(buffer);

		buffer.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_COLOR);
		buffer.vertex(matrix4f, (float) box.minX, (float) box.maxY, (float) box.minZ).color(topR, topG, topB, topA).next();
		buffer.vertex(matrix4f, (float) box.maxX, (float) box.maxY, (float) box.minZ).color(topR, topG, topB, topA).next();
		buffer.vertex(matrix4f, (float) box.maxX, (float) box.minY, (float) box.minZ).color(botR, botG, botB, botA).next();
		buffer.vertex(matrix4f, (float) box.minX, (float) box.minY, (float) box.minZ).color(botR, botG, botB, botA).next();
		buffer.end();
		BufferRenderer.draw(buffer);

		buffer.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_COLOR);
		buffer.vertex(matrix4f, (float) box.minX, (float) box.minY, (float) box.maxZ).color(botR, botG, botB, botA).next();
		buffer.vertex(matrix4f, (float) box.maxX, (float) box.minY, (float) box.maxZ).color(botR, botG, botB, botA).next();
		buffer.vertex(matrix4f, (float) box.maxX, (float) box.maxY, (float) box.maxZ).color(topR, topG, topB, topA).next();
		buffer.vertex(matrix4f, (float) box.minX, (float) box.maxY, (float) box.maxZ).color(topR, topG, topB, topA).next();
		buffer.end();
		BufferRenderer.draw(buffer);

		glShadeModel(GL_FLAT);
//		RenderUtil.disableGL2D();
	}*/


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
