package github.thesivlerecho.zeropoint.render.shader.programs;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import github.thesivlerecho.zeropoint.render.shader.Shader;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gl.Framebuffer;
import net.minecraft.util.math.Matrix4f;
import net.minecraft.util.math.Vec2f;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL43;

import static org.lwjgl.opengl.GL20.glUseProgram;

public class OutlinePostprocessShader extends Shader
{
	public final Framebuffer input;
	public final Framebuffer output;

	public OutlinePostprocessShader()
	{
		super("new/outline", "new/defaultpp");

		input = MinecraftClient.getInstance().getFramebuffer();
		output = MinecraftClient.getInstance().getFramebuffer();

	}

	@Override
	public Shader bind()
	{
		RenderSystem.enableTexture();
		RenderSystem.disableBlend();
		RenderSystem.disableDepthTest();
		this.input.endWrite();
		float f = (float) this.output.textureWidth;
		float g = (float) this.output.textureHeight;
		RenderSystem.viewport(0, 0, (int) f, (int) g);
		glUseProgram(programId);

		GlStateManager._bindTexture(input.getColorAttachment());
		GL20.glUniform1i(0, 0);
		RenderSystem.activeTexture(GL43.GL_TEXTURE0);

		setArgument("InSize", new Vec2f((float) this.input.textureWidth, (float) this.input.textureHeight));
		setArgument("OutSize", new Vec2f(f, g));
//		this.output.clear(MinecraftClient.IS_SYSTEM_MAC);
		this.output.beginWrite(false);
		RenderSystem.depthFunc(519);

		setArgument("ProjMat", Matrix4f.projectionMatrix(0.0F, (float) this.input.textureWidth, (float) this.input.textureHeight, 0.0F, 0.1F, 1000.0F));
		return this;
	}

	@Override
	public void unBind()
	{
		RenderSystem.depthFunc(515);
//		GlStateManager._bindTexture(0);
		super.unBind();
		this.output.endWrite();
		this.input.endRead();
	}
}
