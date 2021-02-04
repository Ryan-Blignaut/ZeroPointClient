package github.thesivlerecho.zeropoint.gui.font;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.texture.NativeImage;
import net.minecraft.client.texture.NativeImageBackedTexture;
import net.minecraft.util.Identifier;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.HashMap;

import static org.lwjgl.opengl.GL11.*;

public class FontLoader
{
	private Font font;
	private boolean usesFractionalMetrics;
	private boolean isAntiAliased;
	private int sheetSize;
	private int maxHeight = -1;
	private BufferedImage image;
	private HashMap<Character, FontPositionalHolder> glyphCharacterMap = new HashMap<>();
	private Identifier fontIdentifier;

	/**
	 * @param font                  the aws font that will be used
	 * @param isAntiAliased         if the font must use antiAliasing
	 * @param usesFractionalMetrics if the font must use fractionalMetrics
	 */
	public FontLoader(Font font, boolean usesFractionalMetrics, boolean isAntiAliased)
	{
		this.font = font;
		this.isAntiAliased = isAntiAliased;
		this.usesFractionalMetrics = usesFractionalMetrics;
	}

	/**
	 * This method will generate a font page with the characters supplied
	 *
	 * @param chars character that will be generated
	 */
	public void generatePage(char[] chars)
	{
		FontRenderContext context = new FontRenderContext(new AffineTransform(), isAntiAliased, usesFractionalMetrics);

		double baseHeight = -1;
		double baseWidth = -1;

		for (Character c : chars)
		{
			Rectangle2D rectangle2D = font.getStringBounds(c.toString(), context);
//			make sure that we get the biggest width and height for the char
			if (baseWidth < rectangle2D.getWidth())
				baseWidth = rectangle2D.getWidth();
			if (baseHeight < rectangle2D.getHeight())
				baseHeight = rectangle2D.getHeight();
		}

		sheetSize = (int) Math.ceil(Math.max(
				Math.ceil(Math.sqrt(baseWidth * baseWidth * chars.length) / baseWidth),
				Math.ceil(Math.sqrt(baseHeight * baseHeight * chars.length) / baseHeight))
				* Math.max(baseWidth, baseHeight)) + 1;

		image = new BufferedImage(sheetSize, sheetSize, BufferedImage.TYPE_INT_ARGB);

//		((Graphics2D) bufferedImage.getGraphics())
		final Graphics2D graphics = image.createGraphics();

		graphics.setColor(new Color(0, 0, 0, 0));
		graphics.setFont(font);
		graphics.fillRect(0, 0, sheetSize, sheetSize);
		graphics.setColor(Color.white);
		graphics.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, usesFractionalMetrics ? RenderingHints.VALUE_FRACTIONALMETRICS_ON : RenderingHints.VALUE_FRACTIONALMETRICS_OFF);
		graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, isAntiAliased ? RenderingHints.VALUE_ANTIALIAS_OFF : RenderingHints.VALUE_ANTIALIAS_ON);
		graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, isAntiAliased ? RenderingHints.VALUE_TEXT_ANTIALIAS_ON : RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
		final FontMetrics metrics = graphics.getFontMetrics(font);
		int posX = 0;
		int posY = 1;


		for (Character c : chars)
		{

			Rectangle2D rectangle2D = font.getStringBounds(c.toString(), context);

			final int width = rectangle2D.getBounds().width;
			final int height = rectangle2D.getBounds().height;
			if (posX + width >= sheetSize)
			{
				posY += height;
				posX = 0;
			}
			final FontPositionalHolder box = new FontPositionalHolder(posX, posY, width, height);

			graphics.drawString(c.toString(), posX, posY + metrics.getAscent());
			posX += width;

			glyphCharacterMap.put(c, box);
		}

	}

	public void loadFontImage()
	{
		fontIdentifier = MinecraftClient.getInstance().getTextureManager().registerDynamicTexture("font", loadToNative(image));
	}

	private NativeImageBackedTexture loadToNative(BufferedImage image)
	{
		try
		{
			ByteArrayOutputStream os = new ByteArrayOutputStream();

			ImageIO.write(image, "png", os);
			InputStream inputStream = new ByteArrayInputStream(os.toByteArray());
			final NativeImage read = NativeImage.read(inputStream);
//			GlStateManager.texParameter(3553, 0x2801, 0x2702);
//			GlStateManager.texParameter(3553, 0x2800, 0x2600);
//			9728
//			0x2800
//			GL_LINEAR  = 0x2601;
//			GL_TEXTURE_MIN_FILTER = 0x2801,
//			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
//			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);


			final NativeImageBackedTexture nativeImageBackedTexture = new NativeImageBackedTexture(read);
			nativeImageBackedTexture.setFilter(true, true);
			return nativeImageBackedTexture;
		} catch (Exception exception)
		{
			throw new IllegalStateException("error loading buffered image to native image");
		}
	}

	public void bindFontTexture()
	{
		MinecraftClient.getInstance().getTextureManager().bindTexture(fontIdentifier);
	}

	public float drawChar(Character c, int x, int y)
	{
		final FontPositionalHolder fontPositionalHolder = glyphCharacterMap.get(c);
		float pageX = fontPositionalHolder.getX() / (float) sheetSize;
		float pageY = fontPositionalHolder.getY() / (float) sheetSize;

		float pageWidth = fontPositionalHolder.getW() / (float) sheetSize;
		float pageHeight = fontPositionalHolder.getH() / (float) sheetSize;

		float width = fontPositionalHolder.getW();
		float height = fontPositionalHolder.getH();
		glBegin(GL_TRIANGLES);

		glTexCoord2f(pageX + pageWidth, pageY);
		glVertex2f(x + width, y);

		glTexCoord2f(pageX, pageY);
		glVertex2f(x, y);

		glTexCoord2f(pageX, pageY + pageHeight);
		glVertex2f(x, y + height);

		glTexCoord2f(pageX, pageY + pageHeight);
		glVertex2f(x, y + height);

		glTexCoord2f(pageX + pageWidth, pageY + pageHeight);
		glVertex2f(x + width, y + height);

		glTexCoord2f(pageX + pageWidth, pageY);
		glVertex2f(x + width, y);


		glEnd();
		return width;
	}


}
