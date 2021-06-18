package github.thesivlerecho.zeropoint.gui.old.widgets;

import net.minecraft.client.util.math.MatrixStack;

public abstract class AbstractElement
{
	private final float x, y, w, h;

	public AbstractElement(float x, float y, float w, float h)
	{
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}

	public float getX()
	{
		return x;
	}

	public float getY()
	{
		return y;
	}

	public float getW()
	{
		return w;
	}

	public float getH()
	{
		return h;
	}

	public boolean inBounds(int mouseX, int mouseY)
	{
		return mouseX >= this.x && mouseX <= this.x + this.w && mouseY >= this.y && mouseY <= this.y + this.h;
	}

	public boolean mouseClicked(int mouseX, int mouseY, int button)
	{
		return false;
	}

	public void mouseInput()
	{
	}

	public boolean mouseScrolled(float mouseX, float mouseY, double amount)
	{
		return false;
	}

	public void mouseReleased(float mouseX, float mouseY, int button)
	{
	}

	public void keyTyped(char character, int integer)
	{
	}

	public void render(MatrixStack matrixStack, int mouseX, int mouseY, float delta)
	{
	}
}
