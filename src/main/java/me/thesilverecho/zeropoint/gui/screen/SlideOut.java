package me.thesilverecho.zeropoint.gui.screen;

import me.thesilverecho.zeropoint.gui.GuiHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.LiteralText;
import net.minecraft.util.Util;

import java.awt.*;

public class SlideOut extends Screen
{
	private boolean wasPressed;
	private long lastPressTime;

	public SlideOut()
	{
		super(new LiteralText("slideOut"));
		this.wasPressed = false;
		this.lastPressTime = 0;
	}

	@Override public void render(MatrixStack matrices, int mouseX, int mouseY, float delta)
	{

		if (!wasPressed)
		{
			wasPressed = true;
			lastPressTime = Util.getMeasuringTimeMs();
		}

	/*	boolean isPressed = true;
		if (wasPressed != isPressed)
		{
			wasPressed = isPressed;
			lastPressTime = System.currentTimeMillis();
		}*/

		double tanh = Math.tanh((Util.getMeasuringTimeMs() - lastPressTime) / 1000d);

		GuiHelper.drawRect(matrices, 0, 0, (int) (tanh * (MinecraftClient.getInstance().getWindow().getScaledWidth() / 4)),
				MinecraftClient.getInstance().getWindow().getScaledHeight(), new Color(0, 0, 0, 100).getRGB());
	}

	@Override public void onClose()
	{

		super.onClose();
	}

	@Override public boolean isPauseScreen()
	{
		return false;
	}
}
