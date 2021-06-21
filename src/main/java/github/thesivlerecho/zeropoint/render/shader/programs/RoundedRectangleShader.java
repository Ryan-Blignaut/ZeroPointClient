package github.thesivlerecho.zeropoint.render.shader.programs;

import github.thesivlerecho.zeropoint.render.shader.Shader;
import org.lwjgl.opengl.GL20;

public class RoundedRectangleShader extends Shader
{
	public RoundedRectangleShader()
	{
		super("new/round_rect");
	}

	public static void setThickness(float thickness, float feather)
	{
		GL20.glUniform2f(0, thickness, feather);
	}

	public static void setRectangle(float left, float top, float right, float bottom)
	{
		GL20.glUniform4f(1, left, top, right, bottom);
	}

	public static void setColour(float r, float g, float b, float a)
	{
		GL20.glUniform4f(3, r, g, b, a);
	}

}
