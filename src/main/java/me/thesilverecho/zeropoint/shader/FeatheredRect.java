package me.thesilverecho.zeropoint.shader;

import org.lwjgl.opengl.GL20;

import static org.lwjgl.opengl.GL20.glUseProgram;

public class FeatheredRect extends Shader
{
	public static FeatheredRect INST = new FeatheredRect("feathered_rect.vert", "feathered_rect.frag");

	public FeatheredRect(String vertFile, String fragFile)
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
