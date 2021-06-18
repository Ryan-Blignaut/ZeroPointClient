package github.thesivlerecho.zeropoint.gui.old.button;

import github.thesivlerecho.zeropoint.gui.old.Box2d;
import github.thesivlerecho.zeropoint.gui.old.GuiHelper;
import net.minecraft.client.util.math.MatrixStack;

import java.awt.*;
import java.util.List;

public class DropdownButton extends AbstractMenuButton
{

	private final List<ClickableButton> buttons;
	private boolean open;

	public DropdownButton(float x, float y, float width, float height, List<ClickableButton> buttons)
	{
		super(x, y, width, height, null);
		this.buttons = buttons;
		open = false;
	}

	@Override
	public void render(MatrixStack matrices, int mouseX, int mouseY, float delta)
	{
		int tempCol;
		if (!open)
			tempCol = Color.DARK_GRAY.getRGB();
		else
			tempCol = Color.PINK.getRGB();
		GuiHelper.rectangle(matrices, 7, new Box2d(x, y, width, height), tempCol);
		buttons.forEach(clickableButton -> clickableButton.render(matrices, mouseX, mouseY, delta));

	}

	@Override
	protected void onClick(double mouseX, double mouseY)
	{
		open = !open;
		float tempHeight = height;
		if (open)
		{
			for (ClickableButton clickableButton : buttons)
				tempHeight += clickableButton.height;
		} else
			for (ClickableButton clickableButton : buttons)
				tempHeight -= clickableButton.height;

		//		this.height += buttons.size();
		//		else this.height -= buttons.size();

		this.height = tempHeight;

		super.onClick(mouseX, mouseY);
	}

}
