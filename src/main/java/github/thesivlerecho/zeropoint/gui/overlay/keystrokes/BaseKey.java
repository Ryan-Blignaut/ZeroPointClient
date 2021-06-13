package github.thesivlerecho.zeropoint.gui.overlay.keystrokes;

import github.thesivlerecho.zeropoint.config.Settings;
import github.thesivlerecho.zeropoint.gui.GuiHelper;
import github.thesivlerecho.zeropoint.render.widget.CircleComponent;
import github.thesivlerecho.zeropoint.util.CircleManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.math.MatrixStack;

import java.awt.*;


public abstract class BaseKey
{
	protected final MinecraftClient minecraft = MinecraftClient.getInstance();
	protected final int xOffset;
	protected final int yOffset;
	protected final int width;
	protected final int height;

	protected final KeyBinding keyBinding;
	private boolean wasPressed;
	private long lastPressTime;

	public static float MAX_WIDTH, MAX_HEIGHT;
	private CircleManager circleManager = new CircleManager();

	public BaseKey(int xOffset, int yOffset, int width, int height, KeyBinding keyBinding)
	{
		this.xOffset = xOffset;
		this.yOffset = yOffset;
		this.width = width;
		this.height = height;
		this.keyBinding = keyBinding;
		this.wasPressed = true;
		this.lastPressTime = -1;
		if (MAX_WIDTH < width + xOffset)
			MAX_WIDTH = width + xOffset;
		if (MAX_HEIGHT < height + yOffset)
			MAX_HEIGHT = height + yOffset;

	}


/*
	protected final void drawCenteredString(MatrixStack matrixStack, String text, int x, int y, int color)
	{
		minecraft.textRenderer.draw(matrixStack, text, (float) (x - minecraft.textRenderer.getWidth(text) / 2), (float) y, color);
	}
*/


	protected final int getColor()
	{
		return Settings.KEY_STROKES_CHROMA_ENABLED ? Color.HSBtoRGB((float) ((System.currentTimeMillis() - (xOffset * 10L) - (yOffset * 10L)) % 2000) / 2000.0F, 0.8F, 0.8F) : Settings.KEY_STROKES_COLOR.getIntVal();
	}

	public final int getColorDown()
	{
		return Settings.KEY_STROKES_CHROMA_ENABLED ? new Color(255, 255, 255).getRGB() : Settings.KEY_STROKES_COLOR_PRESSED.getIntVal();
	}

	protected abstract void renderFg(MatrixStack matrixStack, boolean isPressed);


	public void renderKey(MatrixStack matrixStack)
	{
		int gap = 1;
		boolean isPressed = keyBinding.isPressed();
		if (wasPressed != isPressed)
		{
			wasPressed = isPressed;
			lastPressTime = System.currentTimeMillis();
			circleManager.addCircle(new CircleComponent(Settings.KEY_STROKES_OFFSET.x + xOffset, Settings.KEY_STROKES_OFFSET.y + yOffset, width, height, height, 5));
		}

		double delay = 400d;
		matrixStack.push();
		renderBG(matrixStack, isPressed, delay, gap);
		renderFg(matrixStack, isPressed);
		matrixStack.pop();
	}


	protected void renderBG(MatrixStack matrixStack, boolean isPressed, double delay, int gap)
	{
		float gap1 = 0.5f;

		float radius;
		int col;
		double tanh = Math.tanh((System.currentTimeMillis() - lastPressTime) / delay);

		if (isPressed)
		{
			radius = (float) (tanh * width);
			col = GuiHelper.lerp(getColorDown(), getColor(), tanh).getRGB();
		} else
		{
			radius = 0;//(int) (20 - tanh * 20);
			col = GuiHelper.lerp(getColor(), getColorDown(), tanh).getRGB();
		}

		final float posX = Settings.KEY_STROKES_OFFSET.x + xOffset + gap1;
		final float posY = Settings.KEY_STROKES_OFFSET.y + yOffset + gap1;
		final float posW = posX + width - gap1 * 2;
		final float posH = posY + height - gap1 * 2;

		GuiHelper.fill(matrixStack, 7, posX, posY, posW, posH, col);
//		CircleShader.INST.bind();
//		CircleShader.INST.setCenter(posX + (width - gap1) / 2f, posY + (height - gap1) / 2f);
//		CircleShader.INST.setRadius(radius);
		GuiHelper.fill(matrixStack, 7, posX, posY, posW, posH, Color.white.getRGB() & (80 & 255) << 24);
//		CircleShader.INST.unBind();
	}

	public void updateKey()
	{
		circleManager.updateCircles();
	}
}
