package github.thesivlerecho.zeropoint.render.shader.programs;

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

public class ContrastPostprocessShader extends Shader
{


	public final Framebuffer input;
	public final Framebuffer output;

	public ContrastPostprocessShader()
	{
		super("new/contrast", "new/defaultpp");

		input = MinecraftClient.getInstance().getFramebuffer();
		output = MinecraftClient.getInstance().getFramebuffer();

	}

	@Override
	public Shader bind()
	{
		RenderSystem.enableTexture();
		RenderSystem.disableBlend();
		RenderSystem.disableDepthTest();
		glUseProgram(programId);
		setArgument("ProjMat", Matrix4f.projectionMatrix(0.0F, (float) this.input.textureWidth, (float) this.input.textureHeight, 0.0F, 0.1F, 1000.0F));
		return this;
	}

	public void draw()
	{

		this.input.endWrite();
		float f = (float) this.output.textureWidth;
		float g = (float) this.output.textureHeight;

		RenderSystem.viewport(0, 0, (int) f, (int) g);
		this.bind();
		GlStateManager._bindTexture(input.getColorAttachment());
//		MinecraftClient.getInstance().getTextureManager().bindTexture(new Identifier(ZeroPointClient.MOD_ID, "textures/ui/zero-logo.png"));
		GL20.glUniform1i(0, 0);
		GL20.glUniform1f(1, 1);
		RenderSystem.activeTexture(GL43.GL_TEXTURE0);

		setArgument("InSize", new Vec2f((float) this.input.textureWidth, (float) this.input.textureHeight));
		setArgument("OutSize", new Vec2f(f, g));
//		this.output.clear(MinecraftClient.IS_SYSTEM_MAC);
		this.output.beginWrite(false);
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
		this.output.endWrite();
		this.input.endRead();
	}

	@Override
	public void unBind()
	{
		GlStateManager._bindTexture(0);
		super.unBind();
	}
}
