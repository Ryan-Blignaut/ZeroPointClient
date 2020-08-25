package me.thesilverecho.zeropoint.gui.button;

import javafx.scene.image.PixelFormat;
import me.thesilverecho.zeropoint.gui.GuiHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.util.math.MatrixStack;
import org.lwjgl.system.windows.PIXELFORMATDESCRIPTOR;

import java.awt.*;

public class ClickableButton extends AbstractMenuButton
{
	protected final ClickableButton.PressAction onPress;

	public ClickableButton(float x, float y, float width, float height, String text, PressAction onPress)
	{
		super(x, y, width, height, text);
		this.onPress = onPress;
	}

	@Override public void render(MatrixStack matrices, int mouseX, int mouseY, float delta)
	{
		GuiHelper.fill(matrices, 7, x, y, x + width, y + height, new Color(20, 156, 219, 100).getRGB());
		TextRenderer tr = MinecraftClient.getInstance().textRenderer;
		tr.draw(matrices, text, x , y + tr.fontHeight/2f /*- (height/2 + 0*//*tr.fontHeight*//*)*/ , 1);
	}

	@Override protected void onClick(double mouseX, double mouseY)
	{
		this.onPress.onPress(this);
	}

	public interface PressAction
	{
		void onPress(ClickableButton button);
	}
}
