package github.thesivlerecho.zeropoint.render;

import com.mojang.blaze3d.systems.RenderSystem;
import github.thesivlerecho.zeropoint.render.shader.ShaderManager;
import github.thesivlerecho.zeropoint.render.shader.ZeroPointShader;
import github.thesivlerecho.zeropoint.render.shader.programs.BlurPostprocessShaderSize;
import github.thesivlerecho.zeropoint.render.shader.programs.CircleShader;
import github.thesivlerecho.zeropoint.render.shader.programs.ContrastPostprocessShader;
import github.thesivlerecho.zeropoint.render.shader.programs.RoundedRectangleShader;
import github.thesivlerecho.zeropoint.render.widget.Component2d;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Matrix4f;

import java.util.function.Consumer;

import static org.lwjgl.opengl.GL11.*;

public class DrawingUtil
{
	private static final Tessellator TESSELLATOR = Tessellator.getInstance();
	private static final BufferBuilder BUFFER = TESSELLATOR.getBuffer();
	private static int zIndex = 0;

	public static void drawBasicBox(MatrixStack matrixStack, float x, float y, float width, float height, int colour)
	{
		enableGL2D();
		final Matrix4f matrix4f = matrixStack.peek().getModel();
		final ColorUtil.ColorHolder col = ColorUtil.getColor(colour);
		RenderSystem.setShader(GameRenderer::getPositionColorShader);
		BUFFER.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_COLOR);
		BUFFER.vertex(matrix4f, x, y + height, zIndex).color(col.getR(), col.getG(), col.getB(), col.getA()).next();
		BUFFER.vertex(matrix4f, x + width, y + height, zIndex).color(col.getR(), col.getG(), col.getB(), col.getA()).next();
		BUFFER.vertex(matrix4f, x + width, y, zIndex).color(col.getR(), col.getG(), col.getB(), col.getA()).next();
		BUFFER.vertex(matrix4f, x, y, zIndex).color(col.getR(), col.getG(), col.getB(), col.getA()).next();
		BUFFER.end();
		BufferRenderer.draw(BUFFER);
		disableGL2D();
	}


	public static void drawBasicBox(MatrixStack matrixStack, Component2d component2d)
	{
		enableGL2D();
		BufferRenderer.draw(initComponent(matrixStack, component2d));
		disableGL2D();
	}

	public static void drawBoxWithShader(MatrixStack matrixStack, Component2d component2d)
	{
		enableGL2D();
		BufferRenderer.postDraw(initComponent(matrixStack, component2d));
		disableGL2D();
	}

	private static BufferBuilder initComponent(MatrixStack matrixStack, Component2d component2d)
	{
		final Matrix4f matrix4f = matrixStack.peek().getModel();
		/*
		 * 4 3
		 * 1 2
		 * */
		final ColorUtil.ColorHolder col = ColorUtil.getColor(component2d.getColour());
		final BufferBuilder builder = Tessellator.getInstance().getBuffer();
		builder.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_COLOR);
		builder.vertex(matrix4f, component2d.getX(), component2d.getH(), zIndex).color(col.getR(), col.getG(), col.getB(), col.getA()).next();
		builder.vertex(matrix4f, component2d.getW(), component2d.getH(), zIndex).color(col.getR(), col.getG(), col.getB(), col.getA()).next();
		builder.vertex(matrix4f, component2d.getW(), component2d.getY(), zIndex).color(col.getR(), col.getG(), col.getB(), col.getA()).next();
		builder.vertex(matrix4f, component2d.getX(), component2d.getY(), zIndex).color(col.getR(), col.getG(), col.getB(), col.getA()).next();
		builder.end();
		return builder;
	}

	public static void drawRectWithShader(Component2d component2d, float radius, float feather, MatrixStack matrixStack)
	{
		drawCustomRectWithShader(component2d, radius, feather, comp -> DrawingUtil.drawBoxWithShader(matrixStack, comp));
	}

	public static void drawRectWithShader(Component2d component2d, float radius, MatrixStack matrixStack)
	{
		drawCustomRectWithShader(component2d, radius, 1, comp -> DrawingUtil.drawBoxWithShader(matrixStack, comp));
	}

	public static void drawCustomRectWithShader(Component2d component2d, float radius, float feather, Consumer<Component2d> consumer)
	{
		final RoundedRectangleShader shader = ShaderManager.getShader(RoundedRectangleShader.class, ZeroPointShader.ROUND_RECT);
		shader.bind();
		shader.setThickness(radius, Math.min(radius, feather));
		shader.setRectangle(component2d.getX() + radius, component2d.getY() + radius, component2d.getW() - radius, component2d.getH() - radius);
		consumer.accept(component2d);
		shader.unBind();
	}

	public static void drawBlurWithShader(Component2d component2d, MatrixStack matrixStack)
	{
		drawCustomBlurWithShader(component2d, comp -> DrawingUtil.drawBoxWithShader(matrixStack, comp));
	}

	public static void drawCustomBlurWithShader(Component2d component2d, Consumer<Component2d> consumer)
	{
		final BlurPostprocessShaderSize shader = ShaderManager.getShader(BlurPostprocessShaderSize.class, ZeroPointShader.BLUR2);
		shader.setFramebuffer(MinecraftClient.getInstance().getFramebuffer(), (int) component2d.getWidth(), (int) component2d.getHeight());
		shader.bind();
		consumer.accept(component2d);
		shader.unBind();
	}


	public static void drawWithShader(Component2d component2d, Consumer<Component2d> consumer)
	{
		final ContrastPostprocessShader shader = ShaderManager.getShader(ContrastPostprocessShader.class, ZeroPointShader.CONTRAST_POST_P);
//		shader.bind();
//		consumer.accept(component2d);
//		shader.unBind();
		shader.draw();
	}

	public static void drawCircleShader(Component2d component2d, float radius, float feather, Consumer<Component2d> consumer)
	{
		final CircleShader shader = ShaderManager.getShader(CircleShader.class, ZeroPointShader.CIRCLE);
		shader.bind();
		shader.setCenter(component2d.getX() + component2d.getCenterX(), component2d.getY() + component2d.getCenterY());
		shader.setRadius(radius, Math.min(radius, feather));
		consumer.accept(component2d);
		shader.unBind();
	}

	public static void drawBasicRoundRect(MatrixStack matrixStack, float x, float y, float width, float height, int colour, float radius)
	{
		final RoundedRectangleShader shader = ShaderManager.getShader(RoundedRectangleShader.class, ZeroPointShader.ROUND_RECT);
		shader.bind();
		shader.setThickness(radius, radius);
		shader.setRectangle(x + radius, y + radius, x + width - radius, y + height - radius);
		drawBasicBox(matrixStack, x, y, width, height, colour);
		shader.unBind();
	}

	public static void drawTextureBlend(MatrixStack matrixStack, float x, float y, float width, float height, float uMin, float uMax, float vMin, float vMax, int filter)
	{
		RenderSystem.enableBlend();
		RenderSystem.blendFuncSeparate(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA, GL_ONE, GL_ONE_MINUS_SRC_ALPHA);
		drawTexture(matrixStack, x, y, width, height, uMin, uMax, vMin, vMax, filter);
		RenderSystem.disableBlend();
	}

	public static void drawTextureBlend(MatrixStack matrixStack, float x, float y, float width, float height, int filter)
	{
		drawTextureBlend(matrixStack, x, y, width, height, 0, 1, 0, 1, filter);
	}

	public static void drawTexture(MatrixStack matrixStack, float x, float y, float width, float height, int filter)
	{
		drawTexture(matrixStack, x, y, width, height, 0, 1, 0, 1, filter);
	}


	public static void drawTexture(MatrixStack matrixStack, float x, float y, float width, float height, float uMin, float uMax, float vMin, float vMax, int filter)
	{
		final Matrix4f matrix4f = matrixStack.peek().getModel();
		RenderSystem.enableTexture();
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, filter);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, filter);
		RenderSystem.setShader(GameRenderer::getPositionTexShader);
		BUFFER.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_TEXTURE);
		BUFFER.vertex(matrix4f, x, y + height, zIndex).texture(uMin, vMax).next();
		BUFFER.vertex(matrix4f, x + width, y + height, zIndex).texture(uMax, vMax).next();
		BUFFER.vertex(matrix4f, x + width, y, zIndex).texture(uMax, vMin).next();
		BUFFER.vertex(matrix4f, x, y, zIndex).texture(uMin, vMin).next();
		BUFFER.end();
		BufferRenderer.draw(BUFFER);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, filter);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, filter);
	}


	private static void enableGL2D()
	{
		glDisable(GL_DEPTH_TEST);
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		glDepthMask(true);
		glEnable(GL_LINE_SMOOTH);
		glHint(GL_LINE_SMOOTH_HINT, GL_NICEST);
		glHint(GL_POLYGON_SMOOTH_HINT, GL_NICEST);
	}

	private static void disableGL2D()
	{
		glDisable(GL_BLEND);
		glEnable(GL_DEPTH_TEST);
		glDisable(GL_LINE_SMOOTH);
		glHint(GL_LINE_SMOOTH_HINT, GL_DONT_CARE);
		glHint(GL_POLYGON_SMOOTH_HINT, GL_DONT_CARE);
	}

	public static double getAnimationState(double animation, double finalState, double speed)
	{
		float add = (float) (0.055 * speed);
		return animation < finalState ? (Math.min(animation + (double) add, finalState)) : (Math.max(animation - (double) add, finalState));
	}

	public static int getZIndex()
	{
		return zIndex;
	}

	public static void setZIndex(int zIndex)
	{
		DrawingUtil.zIndex = zIndex;
	}

}
