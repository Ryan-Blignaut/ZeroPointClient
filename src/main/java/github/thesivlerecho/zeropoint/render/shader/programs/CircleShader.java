package github.thesivlerecho.zeropoint.render.shader.programs;

import github.thesivlerecho.zeropoint.render.shader.Shader;
import org.lwjgl.opengl.GL20;

public class CircleShader extends Shader
{
	public CircleShader()
	{
		super("new/circle");
	}

	public void setCenter(float x, float y)
	{
		GL20.glUniform2f(0, x, y);
	}

	public void setRadius(float radius, float feather)
	{
		GL20.glUniform2f(1, radius, feather);
	}

}
