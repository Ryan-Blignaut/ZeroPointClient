package github.thesivlerecho.zeropoint.gui.old.button;

import github.thesivlerecho.zeropoint.ZeroPointClient;
import github.thesivlerecho.zeropoint.gui.old.Icon;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

import java.awt.*;

public class SocialButtons extends AbstractMenuButton
{

	private final Identifier id;
	private final String url;
	private final Color colour;
	private boolean wasPressed;
	private long lastPressTime;
	private float pos = 0;

	public SocialButtons(float x, float y, float width, float height, String url, Identifier id, Color colour)
	{
		super(x, y, width, height, null);
		this.wasPressed = true;
		this.lastPressTime = 0;
		this.id = id;
		this.url = url;
		this.colour = colour;
	}

	@Override
	public boolean mouseClicked(double mouseX, double mouseY, int button)
	{
		if (isMouseOver(mouseX, mouseY))
			Util.getOperatingSystem().open(url);
		return this.isMouseOver(mouseX, mouseY);
	}

	@Override
	public void render(MatrixStack matrices, int mouseX, int mouseY, float delta)
	{
		Color tempCol;
		boolean isPressed = isMouseOver(mouseX, mouseY);
		if (wasPressed != isPressed)
		{
			wasPressed = isPressed;
			lastPressTime = System.currentTimeMillis();
		}
		double tanh = Math.tanh((System.currentTimeMillis() - lastPressTime) / 300d);
		if (isMouseOver(mouseX, mouseY))
			pos = (float) (tanh * height) + 2;
		else
			pos = (float) (pos - tanh * pos / 5);

		if (pos > 2)
			tempCol = colour;
		else
			tempCol = new Color(63, 63, 63);

		//		MinecraftClient.getInstance().getTextureManager().bindTexture(id);
		//		drawTexture(matrices, ((int) x), ((int) y), ((int) width), ((int) height), 0, 0, 512, 512, 512, 512);
		ZeroPointClient.DRAWING_HELPER.setRGB(tempCol).drawIcon(matrices, new Icon(id, 0, 0, 1, 1, true, true), x, y, x + width, y + height);
		fill(matrices, 7, x + xOffset, y + yOffset + pos - 2, x + xOffset + width, y + yOffset + pos, tempCol.getRGB()/* col*/);

	}

}
