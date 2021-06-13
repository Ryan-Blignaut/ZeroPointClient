package github.thesivlerecho.zeropoint.gui.font;

import net.minecraft.util.math.Vec2f;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.HashMap;

import static org.lwjgl.opengl.GL11.*;

public class CustomFont
{
	private int fontId;
	private BufferedImage bufferedImage;
	private Vec2f imageSize;
	private Font font;
	private FontMetrics fontMetrics;
	private int i;
	private final HashMap<Character, Glyph> glyphCharacterMap = new HashMap<>();

	public CustomFont(String name, float size)
	{
		try
		{
			this.font = Font.createFont(Font.TRUETYPE_FONT, new File(""));
		} catch (FontFormatException | IOException e)
		{
			e.printStackTrace();
		}
	}

	public CustomFont(Font font)
	{
		this.font = font;
		genFont();
	}

	private void genFont()
	{
		bufferedImage = new BufferedImage(1024, 1024, BufferedImage.TYPE_INT_ARGB);

//		final GraphicsConfiguration configuration = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
//		final Graphics2D graphics = configuration.createCompatibleImage(1024, 1024, Transparency.TRANSLUCENT).createGraphics();
		final Graphics2D graphics = bufferedImage.createGraphics();
		graphics.setFont(font);
		fontMetrics = graphics.getFontMetrics();
		imageSize = new Vec2f(1024, 1024);
//		bufferedImage = graphics.getDeviceConfiguration().createCompatibleImage((int) imageSize.x, (int) imageSize.y, Transparency.TRANSLUCENT);

		fontId = glGenTextures();
		glEnable(GL_TEXTURE_2D);
		glBindTexture(GL_TEXTURE_2D, fontId);
		glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, (int) imageSize.x, (int) imageSize.y, 0, GL_RGBA, GL_UNSIGNED_BYTE, genImage());
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);

	}

	private ByteBuffer genImage()
	{
		Graphics2D graphics2D = (Graphics2D) bufferedImage.getGraphics();
		graphics2D.setFont(font);
		graphics2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

		drawChars(graphics2D);

		return createBuffer();
	}

	private ByteBuffer createBuffer()
	{
		int w = (int) imageSize.x;
		int h = (int) imageSize.y;
		int[] pixelData = new int[w * h];
		bufferedImage.getRGB(0, 0, w, h, pixelData, 0, w);
		ByteBuffer b = ByteBuffer.allocateDirect(w * h * 4);
		for (int pixelDatum : pixelData)
		{
			b.put((byte) (pixelDatum >> 16 & 255));
			b.put((byte) (pixelDatum >> 8 & 255));
			b.put((byte) pixelDatum);
			b.put((byte) (pixelDatum >> 24 & 255));
		}
		b.flip();
		return b;
	}

	private void drawChars(Graphics2D graphics2D)
	{
		int tempX = 0;
		int tempY = 0;
		float h = fontMetrics.getMaxAscent() + fontMetrics.getMaxDescent();
		for (int j = 0; j < 256; j++)
		{
			char c = (char) j;
			float charWidth = fontMetrics.charWidth(c);
			final float advance = charWidth + 5;

			if (tempX + advance > imageSize.x)
			{
				tempX = 0;
				tempY++;
			}

			glyphCharacterMap.put(c, new Glyph(tempX / imageSize.x, (tempY * h) / imageSize.y, charWidth / imageSize.x, h / imageSize.y));
			graphics2D.drawString(String.valueOf(c), tempX, fontMetrics.getMaxAscent() + h * tempY);
			tempX += advance;
		}


	}

	public int getFontId()
	{
		return fontId;
	}

	public HashMap<Character, Glyph> getGlyphCharacterMap()
	{
		return glyphCharacterMap;
	}

	public static class Glyph
	{
		private final float x;
		private final float y;
		private final float width;
		private final float height;

		Glyph(float x, float y, float width, float height)
		{
			this.x = x;
			this.y = y;
			this.width = width;
			this.height = height;
		}

		public float getX()
		{
			return x;
		}

		public float getY()
		{
			return y;
		}

		public float getWidth()
		{
			return width;
		}

		public float getHeight()
		{
			return height;
		}
	}
}
