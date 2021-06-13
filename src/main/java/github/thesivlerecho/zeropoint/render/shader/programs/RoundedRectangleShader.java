package github.thesivlerecho.zeropoint.render.shader.programs;

import github.thesivlerecho.zeropoint.render.shader.Shader;
import org.lwjgl.opengl.GL20;

public class RoundedRectangleShader extends Shader
{
	public RoundedRectangleShader()
	{
		super("new/round_rect");
	}

	public void setThickness(float thickness)
	{
		GL20.glUniform1f(0, thickness);
	}

	public void setRectangle(float left, float top, float right, float bottom)
	{
		GL20.glUniform4f(1, left, top, right, bottom);
	}


}
