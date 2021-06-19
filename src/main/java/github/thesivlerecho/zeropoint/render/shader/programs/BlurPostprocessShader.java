package github.thesivlerecho.zeropoint.render.shader.programs;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import github.thesivlerecho.zeropoint.render.shader.Shader;
import github.thesivlerecho.zeropoint.render.widget.Component2d;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gl.Framebuffer;
import net.minecraft.client.render.*;
import net.minecraft.client.util.Window;
import net.minecraft.util.math.Matrix4f;
import net.minecraft.util.math.Vec2f;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL43;

import java.util.ArrayList;

import static org.lwjgl.opengl.GL20.glUseProgram;

public class BlurPostprocessShader extends Shader
{
	private static final ArrayList<Component2d> BLUR_AREAS = new ArrayList<>();

	public static void addBlurArea(Component2d component2d)
	{
		BLUR_AREAS.add(component2d);
	}

	public BlurPostprocessShader()
	{
		super("post/blur", "post/defaultpp");
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

//		GL11.glScissor(0, 0, 900, 100);
//
//		GL11.glEnable(GL11.GL_SCISSOR_TEST);
//		draw1(input, output, 10, new Vec2f(0, 1));
//		GL11.glDisable(GL11.GL_SCISSOR_TEST);
	}

	//	TODO: implement a texture draw shader, it will be possible to use other shaders, eg round rect and thus blur in a round rect
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
		GL20.glUniform1f(1, radius);
		GL20.glUniform2f(2, dir.x, dir.y);


//		output.clear(MinecraftClient.IS_SYSTEM_MAC);
		output.beginWrite(false);
		RenderSystem.depthFunc(519);
		BufferBuilder bufferBuilder = Tessellator.getInstance().getBuffer();

		BLUR_AREAS.forEach(component2d -> draw(bufferBuilder, component2d));
		BLUR_AREAS.clear();

		RenderSystem.depthFunc(515);
		this.unBind();
		output.endWrite();
		input.endRead();
	}

	private static final Framebuffer FB = MinecraftClient.getInstance().getFramebuffer();

	private void draw(BufferBuilder bufferBuilder, Component2d component2d)
	{
//		component2d = new Component2d(128, 128, 128, 128);
		bufferBuilder.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION);
		final Window window = MinecraftClient.getInstance().getWindow();
		final double s = window.getScaleFactor();


		float y1 = (float) (FB.textureHeight - component2d.getY() * s);
		float y2 = (float) (FB.textureHeight - component2d.getH() * s);

		if (y1 < y2)
		{
			float j = y1;
			y1 = y2;
			y2 = j;
		}

		bufferBuilder.vertex(component2d.getX() * s, y2, 500.0D).next();
		bufferBuilder.vertex(component2d.getW() * s, y2, 500.0D).next();
		bufferBuilder.vertex(component2d.getW() * s, y1, 500.0D).next();
		bufferBuilder.vertex(component2d.getX() * s, y1, 500.0D).next();
//		bufferBuilder.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION);
//		bufferBuilder.vertex(0.0D, 0.0D, 500.0D).next();
//		bufferBuilder.vertex(width, 0.0D, 500.0D).next();
//		bufferBuilder.vertex(width, height, 500.0D).next();
//		bufferBuilder.vertex(0.0D, height, 500.0D).next();
		bufferBuilder.end();
		BufferRenderer.postDraw(bufferBuilder);
	}

	@Override
	public void unBind()
	{
//		GlStateManager._bindTexture(0);
		super.unBind();
	}
}
