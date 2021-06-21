package github.thesivlerecho.zeropoint.render.font;

import static org.lwjgl.opengl.GL45.*;

public class Texture2D
{

	private final int textId;

	public Texture2D()
	{
		this.textId = glCreateTextures(GL_TEXTURE_2D);
	}

	public void bindTexture(int target, int texture)
	{
		glBindTexture(target, texture);
	}

	public void bondTexture()
	{
		bindTexture(GL_TEXTURE_2D, textId);
	}

	public void init(int internalFormat, int width, int height, int mipmapLevel)
	{
		glTextureParameteri(textId, GL_TEXTURE_BASE_LEVEL, 0);
		glTextureParameteri(textId, GL_TEXTURE_MAX_LEVEL, mipmapLevel);
		glTextureParameteri(textId, GL_TEXTURE_MIN_LOD, 0);
		glTextureParameteri(textId, GL_TEXTURE_MAX_LOD, mipmapLevel);
		glTextureParameterf(textId, GL_TEXTURE_LOD_BIAS, 0.0f);
		glTextureStorage2D(textId, mipmapLevel, internalFormat, width, height);
	}

	public void upload(int level, int x, int y, int width, int height, int rowLength, int skipRows,
			int skipPixels, int alignment, int format, int type, long pixels)
	{
		glPixelStorei(GL_UNPACK_ROW_LENGTH, rowLength);
		glPixelStorei(GL_UNPACK_SKIP_ROWS, skipRows);
		glPixelStorei(GL_UNPACK_SKIP_PIXELS, skipPixels);
		glPixelStorei(GL_UNPACK_ALIGNMENT, alignment);
		nglTextureSubImage2D(textId, level, x, y, width, height, format, type, pixels);
	}

}
