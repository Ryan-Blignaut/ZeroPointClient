package me.thesilverecho.zeropoint.shader;

import static org.lwjgl.opengl.GL20.glUseProgram;

public class BlurShader extends Shader
{
	public BlurShader(String vertFile, String fragFile)
	{
		super(vertFile, fragFile);
	}

	@Override
	public void bind()
	{
		glUseProgram(proId);
	}


}
