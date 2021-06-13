package github.thesivlerecho.zeropoint.gui.screen;

import github.thesivlerecho.zeropoint.config.Settings;
import github.thesivlerecho.zeropoint.gui.GuiHelper;
import github.thesivlerecho.zeropoint.gui.overlay.keystrokes.BaseKey;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.math.Vec2f;
import org.lwjgl.opengl.GL11;

import java.awt.*;

import static github.thesivlerecho.zeropoint.config.Settings.KEY_STROKES_OFFSET;

public class PositioningScreen extends Screen
{

	private boolean dragging;
	private Vec2f lastPos = Vec2f.ZERO;

	public PositioningScreen()
	{
		super(new TranslatableText("zp.positionScreen"));
	}

	@Override
	public void render(MatrixStack matrices, int mouseX, int mouseY, float delta)
	{
		if (dragging)
			KEY_STROKES_OFFSET = new Vec2f(KEY_STROKES_OFFSET.x + mouseX - lastPos.x, KEY_STROKES_OFFSET.y + mouseY - lastPos.y);
		lastPos = new Vec2f(mouseX, mouseY);

		GuiHelper.fill(matrices, GL11.GL_LINE_LOOP, KEY_STROKES_OFFSET.x, KEY_STROKES_OFFSET.y, KEY_STROKES_OFFSET.x + BaseKey.MAX_WIDTH, KEY_STROKES_OFFSET.y + BaseKey.MAX_HEIGHT, Color.PINK.getRGB());
	}

	@Override
	public void onClose()
	{
		super.onClose();
		Settings.save();
	}


	@Override
	public boolean mouseClicked(double mouseX, double mouseY, int button)
	{
		if (isMouseOver(mouseX, mouseY))
			dragging = true;

		return super.mouseClicked(mouseX, mouseY, button);
	}

	@Override
	public boolean mouseReleased(double mouseX, double mouseY, int button)
	{
		dragging = false;
		return super.mouseReleased(mouseX, mouseY, button);
	}


	@Override
	public boolean isMouseOver(double mouseX, double mouseY)
	{
		int scale = 1;
		return mouseX >= KEY_STROKES_OFFSET.x / scale && mouseY >= KEY_STROKES_OFFSET.y / scale && mouseX < (KEY_STROKES_OFFSET.x + BaseKey.MAX_WIDTH / scale) && mouseY < (KEY_STROKES_OFFSET.y + BaseKey.MAX_HEIGHT / scale);
	}


}
