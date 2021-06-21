package github.thesivlerecho.zeropoint.render.shader.programs;

import github.thesivlerecho.zeropoint.render.shader.Shader;
import org.lwjgl.opengl.GL20;

public class CircleLineShader extends Shader
{
	public CircleLineShader()
	{
		super("new/circleline");
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
