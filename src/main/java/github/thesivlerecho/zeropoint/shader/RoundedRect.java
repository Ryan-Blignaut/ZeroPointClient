package github.thesivlerecho.zeropoint.shader;

import org.lwjgl.opengl.GL20;

import static org.lwjgl.opengl.GL20.glUseProgram;

public class RoundedRect extends Shader
{
	public static RoundedRect INST = new RoundedRect("rectV.vert", "rectF.frag");

	public RoundedRect(String vertFile, String fragFile)
	{
		super(vertFile, fragFile);
		create();
	}

	@Override
	public void bind()
	{
		glUseProgram(proId);
	}

	public void setThickness(float thickness)
	{
		GL20.glUniform1f(0, thickness);
	}

	public void setInnerRect(float left, float top, float right, float bottom)
	{
		GL20.glUniform4f(1, left, top, right, bottom);
	}


}
