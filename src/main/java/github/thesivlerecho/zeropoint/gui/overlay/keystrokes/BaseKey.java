package github.thesivlerecho.zeropoint.gui.overlay.keystrokes;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.MatrixStack;

public abstract class BaseKey
{
	protected final MinecraftClient minecraft = MinecraftClient.getInstance();
	protected final int xOffset;
	protected final int yOffset;

	/*protected final int getColor() {
		return mod.getSettings().isChroma() ? Color.HSBtoRGB((float) ((System.currentTimeMillis() - (xOffset * 10) - (yOffset * 10)) % 2000) / 2000.0F,
				0.8F, 0.8F) : new Color(mod.getSettings().getRed(), mod.getSettings().getGreen(), mod.getSettings().getBlue()).getRGB();
	}*/
	public BaseKey(int xOffset, int yOffset)
	{
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}

	protected abstract void renderKey(MatrixStack matrixStack);

	protected final void drawCenteredString(MatrixStack matrixStack, String text, int x, int y, int color)
	{
		minecraft.textRenderer.draw(matrixStack, text, (float) (x - minecraft.textRenderer.getWidth(text) / 2), (float) y, color);
	}

}
