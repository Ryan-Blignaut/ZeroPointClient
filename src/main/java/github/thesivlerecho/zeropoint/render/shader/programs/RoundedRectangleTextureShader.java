package github.thesivlerecho.zeropoint.render.shader.programs;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import github.thesivlerecho.zeropoint.render.shader.Shader;
import net.minecraft.util.math.Vec2f;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL43;

public class RoundedRectangleTextureShader extends Shader
{
	public RoundedRectangleTextureShader()
	{
		super("new/round_rect_text", "new/colour_text");
	}

	public void setThickness(float thickness, float feather)
	{
		GL20.glUniform2f(0, thickness, feather);
	}

	public void setRectangle(float left, float top, float right, float bottom)
	{
		GL20.glUniform4f(1, left, top, right, bottom);
	}

	public void setUV(float U, float V)
	{
		setArgument("UV", new Vec2f(U, V));
	}

	public void setTexture(int location)
	{
		GlStateManager._bindTexture(location);
		GL20.glUniform1i(2, 0);
		RenderSystem.activeTexture(GL43.GL_TEXTURE0);
	}
}
