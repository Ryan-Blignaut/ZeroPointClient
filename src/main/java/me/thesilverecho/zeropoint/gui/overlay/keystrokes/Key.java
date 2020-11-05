package me.thesilverecho.zeropoint.gui.overlay.keystrokes;

import me.thesilverecho.zeropoint.gui.GuiHelper;
import net.minecraft.client.options.KeyBinding;
import net.minecraft.client.util.math.MatrixStack;
import org.lwjgl.opengl.GL11;

import java.awt.*;

public class Key extends BaseKey
{
	private final KeyBinding keyBinding;
	private boolean wasPressed;
	private long lastPressTime;

	public Key(KeyBinding keyBinding, int xOffset, int yOffset)
	{
		super(xOffset, yOffset);
		this.keyBinding = keyBinding;
		this.wasPressed = true;
		this.lastPressTime = 0;

	}

	@Override
	protected void renderKey(MatrixStack matrixStack)
	{

		float x = 122;
		float y = 122;
		boolean isPressed = keyBinding.isPressed();
		if (wasPressed != isPressed)
		{
			wasPressed = isPressed;
			lastPressTime = System.currentTimeMillis();
		}

		double delay = 900d;

		int radius;
		int col;
		double tanh = Math.tanh((System.currentTimeMillis() - lastPressTime) / delay);

		Color colorDown = new Color(0, 255, 255, 150);
		Color colorUp = new Color(255, 175, 175, 150);

		if (isPressed)
		{
			radius = (int) (tanh * 20);
			col = GuiHelper.lerp(colorDown, colorUp, tanh).getRGB();
		} else
		{
			radius = 0;//(int) (20 - tanh * 20);
			col = GuiHelper.lerp(colorUp, colorDown, tanh).getRGB();
		}

		GuiHelper.fill(matrixStack, 7, x + xOffset, y + yOffset, x + xOffset + 22, y + yOffset + 22, col);
//		ZeroPointClient.effect.setKeyColor(rCode, ColorRef.fromRGB(col));

		GuiHelper.clipStart(x + xOffset, y + yOffset, 22, 22);
		GuiHelper.fillEllipse(matrixStack, GL11.GL_TRIANGLE_FAN, (x + xOffset + 11), (y + yOffset + 11), radius, radius,
				new Color(255, 255, 255, 100).getRGB());
		GuiHelper.clipEnd();

		int keyWidth = 22;

		float xPos = x + xOffset + 8;
		float yPos = y + yOffset + 8;

		minecraft.textRenderer.draw(matrixStack, keyBinding.getBoundKeyLocalizedText().asString().toUpperCase(), (int) xPos, (int) yPos,
				Color.white.getRGB());
		GL11.glLineWidth(1);

	}

}
