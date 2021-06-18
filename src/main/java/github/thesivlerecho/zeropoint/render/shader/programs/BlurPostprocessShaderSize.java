package github.thesivlerecho.zeropoint.render.shader.programs;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import github.thesivlerecho.zeropoint.render.shader.Shader;
import net.minecraft.client.gl.Framebuffer;
import net.minecraft.util.math.Matrix4f;
import net.minecraft.util.math.Vec2f;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL43;

import static org.lwjgl.opengl.GL20.glUseProgram;

public class BlurPostprocessShaderSize extends Shader
{
	private Framebuffer input;
	private int blurWidth, blurHeight;

	public BlurPostprocessShaderSize()
	{
		super("new/test", "new/defaultpp");


	}

	@Override
	public Shader bind()
	{
		RenderSystem.enableTexture();
		RenderSystem.disableBlend();
		RenderSystem.disableDepthTest();

		input.endWrite();
		RenderSystem.viewport(0, 0, blurWidth, blurHeight);

		glUseProgram(programId);

		setArgument("ProjMat", Matrix4f.projectionMatrix(0.0F, (float) input.textureWidth, (float) input.textureHeight, 0.0F, 0.1F, 1000.0F));
		GlStateManager._bindTexture(input.getColorAttachment());
//		MinecraftClient.getInstance().getTextureManager().bindTexture(new Identifier(ZeroPointClient.MOD_ID, "textures/ui/zero-logo.png"));
		GL20.glUniform1i(0, 0);
		RenderSystem.activeTexture(GL43.GL_TEXTURE0);
		setArgument("InSize", new Vec2f((float) input.textureWidth, (float) input.textureHeight));
		setArgument("OutSize", new Vec2f(blurWidth, blurHeight));
		GL20.glUniform1f(1, 5);
		GL20.glUniform2f(2, 0, 1);
		RenderSystem.depthFunc(519);
		return this;
	}


	public void setFramebuffer(Framebuffer input, int blurWidth, int blurHeight)
	{
		this.input = input;
		this.blurWidth = blurWidth;
		this.blurHeight = blurHeight;
	}


	@Override
	public void unBind()
	{
		RenderSystem.depthFunc(515);
		GlStateManager._bindTexture(0);
		super.unBind();
		input.endRead();
	}
}
