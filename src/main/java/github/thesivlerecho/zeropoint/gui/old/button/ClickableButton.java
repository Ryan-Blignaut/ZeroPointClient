package github.thesivlerecho.zeropoint.gui.old.button;

import github.thesivlerecho.zeropoint.render.DrawingUtil;
import github.thesivlerecho.zeropoint.render.widget.Component2d;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.util.math.MatrixStack;

import java.awt.*;

public class ClickableButton extends AbstractMenuButton
{
	protected final ClickableButton.PressAction onPress;

	public ClickableButton(float x, float y, float width, float height, String text, PressAction onPress)
	{
		super(x, y, width, height, text);
		this.onPress = onPress;
	}

	@Override
	public void render(MatrixStack matrices, int mouseX, int mouseY, float delta)
	{
		final Component2d component = new Component2d(x, y, width, height);
		DrawingUtil.drawRectWithShader(component.setColour(new Color(39, 40, 47).getRGB()), 2, matrices);
		DrawingUtil.drawRectWithShader(component.setColour(new Color(59, 66, 82).getRGB()).resizeComponent(2), 2, 3, matrices);

//		GuiHelper.drawRoundedRect(matrices, (int) (x - 2), (int) (y - 2), (int) (x + width + 2), (int) (y + height + 2), 2, new Color(39, 40, 47).getRGB());
//		GuiHelper.drawGradientRoundedRect(matrices, (int) (x - 1), (int) (y - 1), (int) (x + width + 1), (int) (y + height + 1), 2, new Color(59, 66, 82).getRGB(), new Color(67, 76, 94).getRGB());
		TextRenderer tr = MinecraftClient.getInstance().textRenderer;
		width = tr.getWidth(text) + 2;
		tr.draw(matrices, text, x + 1, y + 1 /*+ tr.fontHeight*/ /*+ height*/ /*/ 2f*/ /*- (height/2 + 0*//*tr.fontHeight*//*)*/, new Color(216, 222, 233).getRGB());
	}

	@Override
	public void onClick(double mouseX, double mouseY)
	{
		if (isMouseOver(mouseX, mouseY))
			this.onPress.onPress(this);
	}

	public interface PressAction
	{
		void onPress(ClickableButton button);
	}
}
