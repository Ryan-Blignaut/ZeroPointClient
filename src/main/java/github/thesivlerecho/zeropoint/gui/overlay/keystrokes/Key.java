package github.thesivlerecho.zeropoint.gui.overlay.keystrokes;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.render.BufferBuilder;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;

import java.awt.*;

import static github.thesivlerecho.zeropoint.config.Settings.KEY_STROKES_OFFSET;

public class Key extends BaseKey
{


	public Key(KeyBinding keyBinding, int xOffset, int yOffset)
	{
		this(keyBinding, xOffset, yOffset, 22, 22);
	}

	public Key(KeyBinding keyBinding, int xOffset, int yOffset, int width, int height)
	{
		super(xOffset, yOffset, width, height, keyBinding);
	}


	@Override
	protected void renderFg(MatrixStack matrixStack, boolean isPressed)
	{
		final Text name = getName(keyBinding);
		final float charWidth = minecraft.textRenderer.getWidth(name) / 2f;

		final Tessellator instance = Tessellator.getInstance();
		final BufferBuilder buffer = instance.getBuffer();
//		RenderSystem.pushMatrix();
//		RenderSystem.enableBlend();
//		RenderSystem.disableTexture();
//		RenderSystem.defaultBlendFunc();
//		RenderSystem.shadeModel(GL11.GL_SMOOTH);
//		buffer.begin(GL11.GL_TRIANGLES, VertexFormats.POSITION_COLOR);
		final float x = KEY_STROKES_OFFSET.x;
		final float y = KEY_STROKES_OFFSET.y;
//		buffer.vertex(matrixStack.peek().getModel(), x+xOffset/2f, y + yOffset / 2f, 0.0f).color(255, 0, 0, 255).next();
//		buffer.vertex(matrixStack.peek().getModel(), x + xOffset, y + yOffset + height / 2f, 0.0f).color(0, 255, 0, 255).next();
//		buffer.vertex(matrixStack.peek().getModel(), x + xOffset + width, y + yOffset + height / 2f, 0.0f).color(0, 0, 255, 255).next();
//		instance.draw();
//		RenderSystem.shadeModel(GL11.GL_FLAT);
//		RenderSystem.enableTexture();
//		RenderSystem.disableBlend();
//		RenderSystem.popMatrix();
		minecraft.textRenderer.draw(matrixStack, name, x + xOffset + width / 2f - charWidth / 2f, y + yOffset + height / 2f - minecraft.textRenderer.fontHeight / 2f,
				Color.white.getRGB());
	}


	protected final Text getName(KeyBinding keyBinding)
	{
		if (true)
		{
			String letter = null;

			final GameOptions keys = MinecraftClient.getInstance().options;


			if (keyBinding.equals(keys.keyForward))
				letter = "▲";
			else if (keyBinding.equals(keys.keyBack))
				letter = "▼";
			else if (keyBinding.equals(keys.keyLeft))
				letter = "◀";
			else if (keyBinding.equals(keys.keyRight))
				letter = "▶";

			if (letter != null)
				return new LiteralText(letter);
		}

		return new LiteralText(keyBinding.getBoundKeyLocalizedText().asString().toUpperCase());
	}

}
