package github.thesivlerecho.zeropoint.gui.overlay.keystrokes;

import github.thesivlerecho.zeropoint.gui.GuiHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.math.MatrixStack;

import java.awt.*;

import static github.thesivlerecho.zeropoint.config.Settings.KEY_STROKES_OFFSET;

public class SpaceKey extends BaseKey
{
	public SpaceKey(KeyBinding keyBinding, int xOffset, int yOffset, int width, int height)
	{
		super(xOffset, yOffset, width, height, keyBinding);
	}

	@Override
	protected void renderFg(MatrixStack matrixStack, boolean isPressed)
	{
		GuiHelper.fill(matrixStack, 7, KEY_STROKES_OFFSET.x + xOffset + 10, KEY_STROKES_OFFSET.y + yOffset + 20, KEY_STROKES_OFFSET.x + xOffset+ width - 10, KEY_STROKES_OFFSET.y + yOffset + height - 20, Color.white.getRGB());
	}
}