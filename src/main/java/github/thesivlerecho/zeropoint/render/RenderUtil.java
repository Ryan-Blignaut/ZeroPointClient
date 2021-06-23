package github.thesivlerecho.zeropoint.render;

import github.thesivlerecho.zeropoint.render.shader.Shader;
import github.thesivlerecho.zeropoint.render.shader.ShaderManager;
import github.thesivlerecho.zeropoint.render.shader.ZeroPointShader;
import github.thesivlerecho.zeropoint.render.widget.PositioningComponent;
import github.thesivlerecho.zeropoint.util.ZPColour;
import net.minecraft.client.render.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Matrix4f;

import java.awt.*;
import java.util.function.Consumer;

import static org.lwjgl.opengl.GL11.*;

public class RenderUtil
{
	private static int zIndex = 0;
	private static ZPColour color = ColorUtil.getColor(Color.WHITE.getRGB());


	public static void drawShader(MatrixStack matrixStack, PositioningComponent component2d, ZeroPointShader shader, Consumer<Shader> shaderUniforms)
	{
		//TODO: not rendering
		final Shader shader1 = ShaderManager.getShader(shader);
		drawShaderCustom(component2d, shader1, shaderUniforms, comp -> RenderUtil.drawBoxWithShader(matrixStack, comp));
	}

	private static void drawShaderCustom(PositioningComponent component2d, Shader shader, Consumer<Shader> shaderUniforms, Consumer<PositioningComponent> consumer)
	{
		shader.bind();
		shaderUniforms.accept(shader);
		consumer.accept(component2d);
		shader.unBind();
	}


	public static void initShaderWithColor(ZPColour zpColour)
	{


	}

	public static void initShaderWithRadius(Color color)
	{

	}

	public static void initShaderWithTexture(Color color)
	{

	}


	private static void drawBoxWithShader(MatrixStack matrixStack, PositioningComponent positioningComponent)
	{
		enableGL2D();
		BufferRenderer.postDraw(initComponent(matrixStack, positioningComponent));
		disableGL2D();
	}


	private static BufferBuilder initComponent(MatrixStack matrixStack, PositioningComponent positioningComponent)
	{
		final Matrix4f matrix4f = matrixStack.peek().getModel();
		final BufferBuilder builder = Tessellator.getInstance().getBuffer();
		builder.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_COLOR);
		builder.vertex(matrix4f, positioningComponent.getX(), positioningComponent.getH(), zIndex).color(color.getR(), color.getG(), color.getB(), color.getA()).next();
		builder.vertex(matrix4f, positioningComponent.getW(), positioningComponent.getH(), zIndex).color(color.getR(), color.getG(), color.getB(), color.getA()).next();
		builder.vertex(matrix4f, positioningComponent.getW(), positioningComponent.getY(), zIndex).color(color.getR(), color.getG(), color.getB(), color.getA()).next();
		builder.vertex(matrix4f, positioningComponent.getX(), positioningComponent.getY(), zIndex).color(color.getR(), color.getG(), color.getB(), color.getA()).next();
		builder.end();
		return builder;
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


}
