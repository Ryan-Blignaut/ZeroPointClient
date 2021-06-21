package github.thesivlerecho.zeropoint.render.shader.programs.post;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import github.thesivlerecho.zeropoint.render.shader.Shader;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gl.Framebuffer;
import net.minecraft.client.render.*;
import net.minecraft.util.math.Matrix4f;
import net.minecraft.util.math.Vec2f;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL43;

import static org.lwjgl.opengl.GL20.glUseProgram;

public class FastBlurPostprocessShader extends Shader
{

	public FastBlurPostprocessShader()
	{
		super("new/fastblur", "new/fastblur");


	}

	@Override
	public Shader bind()
	{
		RenderSystem.enableTexture();
		RenderSystem.disableBlend();
		RenderSystem.disableDepthTest();
		glUseProgram(programId);
		return this;
	}


	public void draw()
	{
		Framebuffer input = MinecraftClient.getInstance().getFramebuffer();
		Framebuffer output = MinecraftClient.getInstance().getFramebuffer();
//		Framebuffer framebuffer = new WindowFramebuffer(input.viewportWidth, input.viewportHeight);
//		draw1(input, framebuffer, 5, new Vec2f(0, 1));
		draw1(input, output, 10, new Vec2f(0, 1));
	}

	public void draw1(Framebuffer input, Framebuffer output, float radius, Vec2f dir)
	{

		input.endWrite();
		float f = (float) output.textureWidth;
		float g = (float) output.textureHeight;

		RenderSystem.viewport(0, 0, (int) f, (int) g);
		this.bind();
		setArgument("ProjMat", Matrix4f.projectionMatrix(0.0F, (float) input.textureWidth, (float) input.textureHeight, 0.0F, 0.1F, 1000.0F));
		GlStateManager._bindTexture(input.getColorAttachment());
//		MinecraftClient.getInstance().getTextureManager().bindTexture(new Identifier(ZeroPointClient.MOD_ID, "textures/ui/zero-logo.png"));
		GL20.glUniform1i(0, 0);
		RenderSystem.activeTexture(GL43.GL_TEXTURE0);
		setArgument("InSize", new Vec2f((float) input.textureWidth, (float) input.textureHeight));
		setArgument("OutSize", new Vec2f(f, g));
//		GL20.glUniform1f(1, radius);
//		GL20.glUniform2f(2, dir.x, dir.y);


//		this.output.clear(MinecraftClient.IS_SYSTEM_MAC);
		output.beginWrite(false);
		RenderSystem.depthFunc(519);
		BufferBuilder bufferBuilder = Tessellator.getInstance().getBuffer();
		bufferBuilder.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION);
		bufferBuilder.vertex(0.0D, 0.0D, 500.0D).next();
		bufferBuilder.vertex(f, 0.0D, 500.0D).next();
		bufferBuilder.vertex(f, g, 500.0D).next();
		bufferBuilder.vertex(0.0D, g, 500.0D).next();
		bufferBuilder.end();
		BufferRenderer.postDraw(bufferBuilder);
		RenderSystem.depthFunc(515);
		this.unBind();
		output.endWrite();
		input.endRead();
	}

	@Override
	public void unBind()
	{
		GlStateManager._bindTexture(0);
		super.unBind();
	}
}
