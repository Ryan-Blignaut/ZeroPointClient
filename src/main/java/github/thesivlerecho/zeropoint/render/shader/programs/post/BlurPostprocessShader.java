package github.thesivlerecho.zeropoint.render.shader.programs.post;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import github.thesivlerecho.zeropoint.render.shader.Shader;
import github.thesivlerecho.zeropoint.render.widget.PositioningComponent;
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
	private static final ArrayList<PositioningComponent> BLUR_AREAS = new ArrayList<>();
	private Framebuffer input;
	public Framebuffer output;
	private float radius;
	private Vec2f dir;

	public void setUp(Framebuffer input, Framebuffer output, float radius, Vec2f dir)
	{
		this.input = input;
		this.output = output;
		this.radius = radius;
		this.dir = dir;
	}

	public static void addBlurArea(PositioningComponent positioningComponent)
	{
		BLUR_AREAS.add(positioningComponent);
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
		GL20.glUniform1f(1, radius);
		GL20.glUniform2f(2, dir.x, dir.y);
		output.beginWrite(false);
		RenderSystem.depthFunc(519);

		return this;
	}


	//	TODO: implement a texture draw shader, it will be possible to use other shaders, eg round rect and thus blur in a round rect
	public void draw1()
	{
		this.bind();
		BufferBuilder bufferBuilder = Tessellator.getInstance().getBuffer();
		BLUR_AREAS.forEach(component2d -> draw(bufferBuilder, component2d));
		BLUR_AREAS.clear();
		this.unBind();

	}


	private void draw(BufferBuilder bufferBuilder, PositioningComponent positioningComponent)
	{
//		component2d = new Component2d(128, 128, 128, 128);
		bufferBuilder.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION);
		final Window window = MinecraftClient.getInstance().getWindow();
		final double s = window.getScaleFactor();


		float y1 = (float) (input.textureHeight - positioningComponent.getY() * s);
		float y2 = (float) (input.textureHeight - positioningComponent.getH() * s);

		if (y1 < y2)
		{
			float j = y1;
			y1 = y2;
			y2 = j;
		}

		bufferBuilder.vertex(positioningComponent.getX() * s, y2, 500.0D).next();
		bufferBuilder.vertex(positioningComponent.getW() * s, y2, 500.0D).next();
		bufferBuilder.vertex(positioningComponent.getW() * s, y1, 500.0D).next();
		bufferBuilder.vertex(positioningComponent.getX() * s, y1, 500.0D).next();
		bufferBuilder.end();
		BufferRenderer.postDraw(bufferBuilder);
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
