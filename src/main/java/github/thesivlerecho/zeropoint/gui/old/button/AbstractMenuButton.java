package github.thesivlerecho.zeropoint.gui.old.button;

import github.thesivlerecho.zeropoint.gui.old.GuiHelper;
import net.minecraft.client.gui.Drawable;
import net.minecraft.client.gui.Element;

public abstract class AbstractMenuButton extends GuiHelper implements Drawable, Element
{
	protected final String text;
	protected float x;
	protected float y;
	protected float width;
	protected float height;
	protected float xOffset;
	protected float yOffset;

	public AbstractMenuButton(float x, float y, float width, float height, String text)
	{
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.text = text;
	}

	public boolean mouseClicked(double mouseX, double mouseY, int button)
	{
		boolean bl = this.clicked(mouseX, mouseY);
		if (bl)
		{
			this.onClick(mouseX, mouseY);
			return true;
		} else
		{
			return false;
		}
	}

	protected boolean clicked(double mouseX, double mouseY)
	{
		return mouseX >= this.x && mouseY >= this.y && mouseX < (this.x + this.width) && mouseY < (this.y + this.height);
	}

	protected void onClick(double mouseX, double mouseY)
	{
	}

	public boolean mouseDragged(double mouseX, double mouseY, int button, double deltaX, double deltaY)
	{
		this.onDrag(mouseX, mouseY, deltaX, deltaY);
		return true;
	}

	protected void onDrag(double mouseX, double mouseY, double deltaX, double deltaY)
	{
	}

	@Override
	public boolean isMouseOver(double mouseX, double mouseY)
	{
		return mouseX >= this.x + xOffset && mouseY >= this.y + yOffset && mouseX < (this.x + this.width + xOffset) && mouseY < (this.y + this.height + yOffset);
	}

	public AbstractMenuButton setXOffset(float xOffset)
	{
		this.xOffset = xOffset;
		return this;
	}

	public AbstractMenuButton setYOffset(float yOffset)
	{
		this.yOffset = yOffset;
		return this;
	}

}
