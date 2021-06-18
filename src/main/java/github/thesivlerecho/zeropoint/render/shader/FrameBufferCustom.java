package github.thesivlerecho.zeropoint.render.shader;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.Window;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL14;
import org.lwjgl.opengl.GL30;
import org.lwjgl.opengl.GL32;

import java.nio.ByteBuffer;

public class FrameBufferCustom
{
	private int refractionFrameBuffer;
	private int refractionTexture;
	private int refractionDepthTexture;

	public FrameBufferCustom()
	{//call when loading the game
		initialiseRefractionFrameBuffer();
	}

	public void bindRefractionFrameBuffer()
	{//call before rendering to this FBO
		final Window window = MinecraftClient.getInstance().getWindow();
		bindFrameBuffer(refractionFrameBuffer, window.getFramebufferWidth(), window.getFramebufferHeight());
	}

	public void unbindCurrentFrameBuffer()
	{//call to switch to default frame buffer
		GL30.glBindFramebuffer(GL30.GL_FRAMEBUFFER, 0);
		final Window window = MinecraftClient.getInstance().getWindow();
		GL11.glViewport(0, 0, window.getFramebufferWidth(), window.getFramebufferHeight());
	}

	public int getRefractionTexture()
	{//get the resulting texture
		return refractionTexture;
	}

	public int getRefractionDepthTexture()
	{//get the resulting depth texture
		return refractionDepthTexture;
	}

	private void initialiseRefractionFrameBuffer()
	{
		refractionFrameBuffer = createFrameBuffer();
		final Window window = MinecraftClient.getInstance().getWindow();
		refractionTexture = createTextureAttachment(window.getWidth(), window.getHeight());
		refractionDepthTexture = createDepthTextureAttachment(window.getWidth(), window.getHeight());
		unbindCurrentFrameBuffer();
	}

	private void bindFrameBuffer(int frameBuffer, int width, int height)
	{
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);//To make sure the texture isn't bound
		GL30.glBindFramebuffer(GL30.GL_FRAMEBUFFER, frameBuffer);
		GL11.glViewport(0, 0, width, height);
	}

	private int createFrameBuffer()
	{
		int frameBuffer = GL30.glGenFramebuffers();
		//generate name for frame buffer
		GL30.glBindFramebuffer(GL30.GL_FRAMEBUFFER, frameBuffer);
		//create the framebuffer
		GL11.glDrawBuffer(GL30.GL_COLOR_ATTACHMENT0);
		//indicate that we will always render to color attachment 0
		return frameBuffer;
	}

	private int createTextureAttachment(int width, int height)
	{
		int texture = GL11.glGenTextures();
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture);
		GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGB, width, height,
				0, GL11.GL_RGB, GL11.GL_UNSIGNED_BYTE, (ByteBuffer) null);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
		GL32.glFramebufferTexture(GL30.GL_FRAMEBUFFER, GL30.GL_COLOR_ATTACHMENT0,
				texture, 0);
		return texture;
	}

	private int createDepthTextureAttachment(int width, int height)
	{
		int texture = GL11.glGenTextures();
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture);
		GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL14.GL_DEPTH_COMPONENT32, width, height,
				0, GL11.GL_DEPTH_COMPONENT, GL11.GL_FLOAT, (ByteBuffer) null);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
		GL32.glFramebufferTexture(GL30.GL_FRAMEBUFFER, GL30.GL_DEPTH_ATTACHMENT,
				texture, 0);
		return texture;
	}

}
