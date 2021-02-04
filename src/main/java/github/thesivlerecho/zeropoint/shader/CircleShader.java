package github.thesivlerecho.zeropoint.shader;

import org.lwjgl.opengl.GL20;

import static org.lwjgl.opengl.GL20.glUseProgram;

public class CircleShader extends Shader
{
	public static CircleShader INST = new CircleShader("rectV.vert", "circ.frag");

	public CircleShader(String vertFile, String fragFile)
	{
		super(vertFile, fragFile);
		create();
	}

	@Override
	public void bind()
	{
		glUseProgram(proId);
	}

	public void setRadius(float radius)
	{
		GL20.glUniform1f(0, radius);
	}

	public void setCenter(float x, float y)
	{
		GL20.glUniform2f(1, x, y);
	}
}
