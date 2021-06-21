package github.thesivlerecho.zeropoint.render;

import github.thesivlerecho.zeropoint.render.shader.Shader;
import github.thesivlerecho.zeropoint.render.shader.ShaderManager;
import github.thesivlerecho.zeropoint.render.shader.ZeroPointShader;
import github.thesivlerecho.zeropoint.render.shader.programs.CircleLineShader;
import github.thesivlerecho.zeropoint.render.shader.programs.CircleShader;
import github.thesivlerecho.zeropoint.render.shader.programs.RoundedRectangleShader;
import github.thesivlerecho.zeropoint.render.widget.PositioningComponent;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gl.Framebuffer;
import net.minecraft.client.render.*;
import net.minecraft.client.util.Window;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Matrix4f;

import java.awt.*;
import java.util.function.Consumer;

import static org.lwjgl.opengl.GL11.*;

public class DrawingUtil
{
	private static final Tessellator TESSELLATOR = Tessellator.getInstance();
	private static final BufferBuilder BUFFER = TESSELLATOR.getBuffer();
	private static int zIndex = 0;
	private static ColorUtil.ColorHolder color = ColorUtil.getColor(Color.WHITE.getRGB());

	public static void drawShader(MatrixStack matrixStack, PositioningComponent component2d, ZeroPointShader shader, Consumer<Shader> shaderUniforms)
	{
		final Shader shader1 = ShaderManager.getShader(shader);
		drawShaderCustom(component2d, shader1, shaderUniforms, comp -> DrawingUtil.drawBoxWithShader(matrixStack, comp));
	}


	public static void drawShaderCustom(PositioningComponent component2d, Shader shader, Consumer<Shader> shaderUniforms, Consumer<PositioningComponent> consumer)
	{
		shader.bind();
//		shader.setCenter(component2d.getX() + component2d.getActualWidth() / 2, component2d.getY() + component2d.getActualHeight() / 2);
//		shader.setRadius(radius, Math.min(radius, feather));
		shaderUniforms.accept(shader);
		consumer.accept(component2d);
		shader.unBind();
	}


	public static void drawBasicBoxtest(MatrixStack matrixStack, float x, float y, float width, float height, int colour)
	{
		enableGL2D();
		final Matrix4f matrix4f = matrixStack.peek().getModel();
		final ColorUtil.ColorHolder col = ColorUtil.getColor(colour);
		BUFFER.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_COLOR);
		final Window window = MinecraftClient.getInstance().getWindow();
		final double s = window.getScaleFactor();
		final Framebuffer framebuffer = MinecraftClient.getInstance().getFramebuffer();
		float y1 = (float) (framebuffer.textureHeight - y * s);
		float y2 = (float) (framebuffer.textureHeight - (y + height) * s);

		if (y1 < y2)
		{
			float j = y1;
			y1 = y2;
			y2 = j;
		}
		BUFFER.vertex(matrix4f, (float) (x * s), y2, zIndex).color(col.getR(), col.getG(), col.getB(), col.getA()).next();
		BUFFER.vertex(matrix4f, (float) ((x + width) * s), y2, zIndex).color(col.getR(), col.getG(), col.getB(), col.getA()).next();
		BUFFER.vertex(matrix4f, (float) ((x + width) * s), y1, zIndex).color(col.getR(), col.getG(), col.getB(), col.getA()).next();
		BUFFER.vertex(matrix4f, (float) (x * s), y1, zIndex).color(col.getR(), col.getG(), col.getB(), col.getA()).next();
		BUFFER.end();
		BufferRenderer.postDraw(BUFFER);
		disableGL2D();
	}


	public static void drawBasicBox(MatrixStack matrixStack, PositioningComponent positioningComponent)
	{
		enableGL2D();
		BufferRenderer.draw(initComponent(matrixStack, positioningComponent));
		disableGL2D();
	}

	public static void drawBoxWithShader(MatrixStack matrixStack, PositioningComponent positioningComponent)
	{
		enableGL2D();
		BufferRenderer.postDraw(initComponent(matrixStack, positioningComponent));
		disableGL2D();
	}

	private static BufferBuilder initShaderDrawComponent(PositioningComponent positioningComponent)
	{
		final BufferBuilder builder = Tessellator.getInstance().getBuffer();
		builder.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_COLOR);
		builder.vertex(positioningComponent.getX(), positioningComponent.getH(), zIndex).next();
		builder.vertex(positioningComponent.getW(), positioningComponent.getH(), zIndex).next();
		builder.vertex(positioningComponent.getW(), positioningComponent.getY(), zIndex).next();
		builder.vertex(positioningComponent.getX(), positioningComponent.getY(), zIndex).next();
		builder.end();
		return builder;
	}

	private static BufferBuilder initComponent(MatrixStack matrixStack, PositioningComponent positioningComponent)
	{
		final Matrix4f matrix4f = matrixStack.peek().getModel();
		/*
		 * 4 3
		 * 1 2
		 * */
//		final ColorUtil.ColorHolder col = ColorUtil.getColor(positioningComponent.getColour());
		final BufferBuilder builder = Tessellator.getInstance().getBuffer();
		builder.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_COLOR);
		builder.vertex(matrix4f, positioningComponent.getX(), positioningComponent.getH(), zIndex).color(color.getR(), color.getG(), color.getB(), color.getA()).next();
		builder.vertex(matrix4f, positioningComponent.getW(), positioningComponent.getH(), zIndex).color(color.getR(), color.getG(), color.getB(), color.getA()).next();
		builder.vertex(matrix4f, positioningComponent.getW(), positioningComponent.getY(), zIndex).color(color.getR(), color.getG(), color.getB(), color.getA()).next();
		builder.vertex(matrix4f, positioningComponent.getX(), positioningComponent.getY(), zIndex).color(color.getR(), color.getG(), color.getB(), color.getA()).next();
		builder.end();
		return builder;
	}

	public static void drawRectWithShader(PositioningComponent positioningComponent, float radius, float feather, MatrixStack matrixStack)
	{
		drawRoundRectShader(positioningComponent, radius, feather, comp -> DrawingUtil.drawBoxWithShader(matrixStack, comp));
	}

	public static void drawRectWithShader(PositioningComponent positioningComponent, float radius, MatrixStack matrixStack)
	{
		drawRoundRectShader(positioningComponent, radius, 1, comp -> DrawingUtil.drawBoxWithShader(matrixStack, comp));
	}

	public static void drawCircleLineShader(PositioningComponent positioningComponent, float radius, MatrixStack matrixStack)
	{
		drawCircleLineShader(positioningComponent, radius, 1, comp -> DrawingUtil.drawBoxWithShader(matrixStack, comp));
	}

	public static void drawRoundRectShader(PositioningComponent positioningComponent, float radius, float feather, Consumer<PositioningComponent> consumer)
	{
		final RoundedRectangleShader shader = ShaderManager.getShader(RoundedRectangleShader.class, ZeroPointShader.ROUND_RECT);
		shader.bind();
		shader.setThickness(radius, Math.min(radius, feather));
		shader.setRectangle(positioningComponent.getX() + radius, positioningComponent.getY() + radius, positioningComponent.getW() - radius, positioningComponent.getH() - radius);
		consumer.accept(positioningComponent);
		shader.unBind();
	}

	public static void drawCircleShader(PositioningComponent component2d, float radius, float feather, Consumer<PositioningComponent> consumer)
	{
		final CircleShader shader = ShaderManager.getShader(CircleShader.class, ZeroPointShader.CIRCLE);
		shader.bind();
		shader.setCenter(component2d.getX() + component2d.getActualWidth() / 2, component2d.getY() + component2d.getActualHeight() / 2);
		shader.setRadius(radius, Math.min(radius, feather));
		consumer.accept(component2d);
		shader.unBind();
	}

	public static void drawCircleLineShader(PositioningComponent positioningComponent, float radius, float feather, Consumer<PositioningComponent> consumer)
	{
		final CircleLineShader shader = ShaderManager.getShader(CircleLineShader.class, ZeroPointShader.CIRCLE_LINE);
		shader.bind();
		shader.setCenter(positioningComponent.getX() + positioningComponent.getActualHeight() / 2, positioningComponent.getY() + positioningComponent.getActualWidth() / 2);
		shader.setRadius(radius, Math.min(radius, feather));
		consumer.accept(positioningComponent);
		shader.unBind();
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

	public static ColorUtil.ColorHolder getColor()
	{
		return color;
	}

	public static void setColor(int color)
	{
		DrawingUtil.color = ColorUtil.getColor(color);
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
