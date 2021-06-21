package github.thesivlerecho.zeropoint.render.shader.programs.post;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import github.thesivlerecho.zeropoint.render.shader.Shader;
import net.minecraft.client.gl.Framebuffer;
import net.minecraft.util.math.Matrix4f;
import net.minecraft.util.math.Vec2f;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL43;

import static org.lwjgl.opengl.GL20.glUseProgram;

public class BasePostShader extends Shader
{
	private Framebuffer input;
	private Framebuffer output;

	public void setUpFBOs(Framebuffer input, Framebuffer output)
	{
		this.input = input;
		this.output = output;
	}

	public BasePostShader(String fragLocation, String vertLocation)
	{
		super(fragLocation, vertLocation);
	}


	@Override
	public Shader bind()
	{
		RenderSystem.enableTexture();
		RenderSystem.disableBlend();
		RenderSystem.disableDepthTest();
		input.endWrite();
		float f = (float) output.textureWidth;
		float g = (float) output.textureHeight;
		RenderSystem.viewport(0, 0, (int) f, (int) g);
		glUseProgram(programId);
		setArgument("ProjMat", Matrix4f.projectionMatrix(0.0F, (float) input.textureWidth, (float) input.textureHeight, 0.0F, 0.1F, 1000.0F));
		GlStateManager._bindTexture(input.getColorAttachment());
		GL20.glUniform1i(0, 0);
		RenderSystem.activeTexture(GL43.GL_TEXTURE0);
		setArgument("InSize", new Vec2f((float) input.textureWidth, (float) input.textureHeight));
		setArgument("OutSize", new Vec2f(f, g));
		applyExtraUniforms();
		output.beginWrite(false);
		RenderSystem.depthFunc(519);

		return this;
	}

	protected void applyExtraUniforms()
	{

	}


	@Override
	public void unBind()
	{
		RenderSystem.depthFunc(515);
		super.unBind();
//		output.endWrite();
		input.endRead();
	}

}
