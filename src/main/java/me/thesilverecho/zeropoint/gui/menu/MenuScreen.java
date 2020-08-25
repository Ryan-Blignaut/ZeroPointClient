package me.thesilverecho.zeropoint.gui.menu;

import me.thesilverecho.zeropoint.gui.Box2d;
import me.thesilverecho.zeropoint.gui.GuiHelper;
import net.minecraft.client.util.math.MatrixStack;

import java.awt.*;

public class MenuScreen
{
	public static void renderScreen(MatrixStack matrixStack)
	{
		GuiHelper.rectangle(matrixStack, 7, new Box2d(0, 0, 550, 656), new Color(27, 27, 33).getRGB());
		GuiHelper.rectangle(matrixStack, 7, new Box2d(1, 1, 549, 549), new Color(32, 32, 38).getRGB());
		GuiHelper.rectangle(matrixStack, 7, new Box2d(10, 46, 540, 639), new Color(36, 36, 41).getRGB());
		GuiHelper.rectangle(matrixStack, 7, new Box2d(24, 47, 539, 638), new Color(22, 22, 28).getRGB());

	}

}
