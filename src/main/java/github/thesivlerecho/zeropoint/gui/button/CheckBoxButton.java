package github.thesivlerecho.zeropoint.gui.button;

import github.thesivlerecho.zeropoint.gui.GuiHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.MatrixStack;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class CheckBoxButton extends ClickableButton
{
	private static boolean checked;

	public CheckBoxButton(float x, float y, float width, float height, String text, AtomicBoolean setting)
	{
		super(x, y, width, height, text, button ->
				checked = !setting.getAndSet(!setting.get()));
		checked = setting.get();
	}


	@Override
	public void render(MatrixStack matrixStack, int mouseX, int mouseY, float delta)
	{
		Color color = checked ? new Color(178, 178, 184) : new Color(73, 74, 82);
//		if (checked)
//			color = new Color(178, 178, 184, 255);
//		else
//			color = new Color(73, 74, 82, 255);


		fill(matrixStack, GL11.GL_QUADS, this.x - 0.5f + xOffset, this.y - 0.5f + yOffset, this.x + this.width + 0.5f + xOffset, this.y + this.height + 0.5f + yOffset, new Color(26, 26, 32).getRGB());
		GuiHelper.drawRoundedRect(matrixStack,
				(int) (this.x + xOffset),
				(int) (this.y + yOffset),
				(int) (this.x + this.width + xOffset),
				(int) (this.y + this.height + yOffset),
				1,
				color.getRGB());

		MinecraftClient.getInstance().textRenderer.draw(matrixStack, text, x + width + xOffset, yOffset + (y / 2), -1);
//		ZeroPointClient.RENDERER.drawString(text, x + width + xOffset, yOffset + (y / 2 /*- ZeroPointClient.RENDERER.getFontHeight() / 2f*/
//		), -1, false, 1);
	}


	public float getHeight()
	{
		return Math.max(MinecraftClient.getInstance().textRenderer.fontHeight, height);
	}

	public float getWidth()
	{
		return (float) ((MinecraftClient.getInstance().textRenderer.getWidth(text) + width) * MinecraftClient.getInstance().getWindow().getScaleFactor());
	}

}
