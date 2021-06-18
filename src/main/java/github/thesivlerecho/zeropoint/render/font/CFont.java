package github.thesivlerecho.zeropoint.render.font;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.texture.NativeImage;
import net.minecraft.client.texture.NativeImageBackedTexture;
import net.minecraft.client.util.Window;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import org.lwjgl.opengl.GL11;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.*;

import static org.lwjgl.opengl.GL11C.*;

public class CFont
{
	private final int imgSize = 512;
	protected CharData[] chars = new CharData[256];
	protected Font font;
	protected boolean antiAlias;
	protected boolean fractionalMetrics;
	protected int fontHeight = -1;
	protected int charOffset = 0;
	private final Identifier texture;


	public CFont(Font font, boolean antiAlias, boolean fractionalMetrics)
	{
		this.font = font;
		this.antiAlias = antiAlias;
		this.fractionalMetrics = fractionalMetrics;

		final BufferedImage textureSheet = generateFontTextureSheet(font, antiAlias, fractionalMetrics);
		texture = bufferedImageToTexture(textureSheet);


	}

	public void drawString(MatrixStack matrixStack, int x, int y, String s)
	{
		int posX = x;
		final char[] chars = s.toCharArray();
		for (char aChar : chars)
			posX += drawChar(matrixStack, posX, y, aChar);
//		PostProcessShader postProcessShader = new PostProcessShader();
	}

	public double drawChar(MatrixStack matrixStack, int x, int y, char letter)
	{
		final CharData charData = chars[letter];

		float renderSRCX = (float) charData.x / imgSize;
		float renderSRCY = (float) charData.x / imgSize;
		float renderSRCWidth = (float) charData.width / imgSize;
		float renderSRCHeight = (float) charData.height / imgSize;

		RenderSystem.enableBlend();
		RenderSystem.setShaderTexture(0, texture);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
		final Window w = MinecraftClient.getInstance().getWindow();
		Screen.drawTexture(matrixStack, x, y, (int) (charData.width / w.getScaleFactor()), (int) (charData.height / w.getScaleFactor()), charData.x, charData.y, charData.width, charData.height, 512, 512);
		RenderSystem.disableBlend();
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
		return charData.width / w.getScaleFactor();
	}

	private Identifier bufferedImageToTexture(BufferedImage image)
	{
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		try
		{
			ImageIO.write(image, "png", os);
			ImageIO.write(image, "png", new File("saved.png"));
			InputStream inputStream = new ByteArrayInputStream(os.toByteArray());
			final NativeImage read = NativeImage.read(inputStream);
			return MinecraftClient.getInstance().getTextureManager().registerDynamicTexture("font", new NativeImageBackedTexture(read));
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		return null;
	}


	public BufferedImage generateFontTextureSheet(Font font, boolean antiAlias, boolean fractionalMetrics)
	{
		final BufferedImage bufferedImage = new BufferedImage(imgSize, imgSize, BufferedImage.TYPE_INT_ARGB);
		final Graphics2D g = (Graphics2D) bufferedImage.getGraphics();
		g.setFont(font);
		g.setBackground(new Color(0, 0, 0, 0));
		g.clearRect(0, 0, imgSize, imgSize);
		g.setColor(Color.WHITE);
		g.setComposite(AlphaComposite.Src);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
		g.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, fractionalMetrics ? RenderingHints.VALUE_FRACTIONALMETRICS_ON : RenderingHints.VALUE_FRACTIONALMETRICS_OFF);
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, antiAlias ? RenderingHints.VALUE_TEXT_ANTIALIAS_ON : RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);

		FontMetrics fontMetrics = g.getFontMetrics();
		int charHeight = 0;
		int positionX = 0;
		int positionY = 1;


		for (int i = 0; i < chars.length; i++)
		{
			char ch = (char) i;

			final CharData charData = new CharData();
			final Rectangle2D dimensions = fontMetrics.getStringBounds(String.valueOf(ch), g);
			final int padding = 8;
			charData.width = (dimensions.getBounds().width + padding);
			charData.height = (dimensions.getBounds().height);


			if (positionX + charData.width >= imgSize)
			{
				positionX = 0;
				positionY += charHeight;
				charHeight = 0;
			}

			if (charData.height > charHeight)
				charHeight = charData.height;

			if (charData.height > this.fontHeight)
				this.fontHeight = charData.height;

			charData.x = positionX;
			charData.y = positionY;


			chars[i] = charData;
			g.drawString(String.valueOf(ch), positionX + 2, positionY + fontMetrics.getAscent());
			positionX += charData.width;
		}

		return bufferedImage;


	}


	private static class CharData
	{
		private int width;
		private int height;
		private int x;
		private int y;
	}
}
