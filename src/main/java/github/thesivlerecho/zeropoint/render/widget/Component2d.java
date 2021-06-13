package github.thesivlerecho.zeropoint.render.widget;

public class Component2d
{
	private float x, y;
	private float w, h;
	private int colour = 0;
	protected boolean visible = true;

	public Component2d(float x, float y, float width, float height)
	{
		this.x = x;
		this.y = y;
		this.w = width;
		this.h = height;

	}

	public void setX(float x)
	{
		this.x = x;
	}

	public void setY(float y)
	{
		this.y = y;
	}

	public boolean inMouseOver(int mouseX, int mouseY)
	{
		return mouseX >= this.x && mouseX <= this.x + this.w && mouseY >= this.y && mouseY <= this.y + this.h;
	}

	public int getColour()
	{
		return colour;
	}

	public Component2d setColour(int colour)
	{
		this.colour = colour;
		return this;
	}

	public Component2d resizeComponent(float size)
	{
		this.x += size;
		this.y += size;
		this.w += -size;
		this.h += -size;
		return this;
	}

	public Component2d resizeComponent(float x, float y, float width, float height)
	{
		this.x += x;
		this.y += y;
		this.w += width;
		this.h += height;
		return this;
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
		return x + w;
	}

	public float getH()
	{
		return y + h;
	}


	public Component2d setW(float w)
	{
		this.w = w;
		return this;
	}

	public Component2d setH(float h)
	{
		this.h = h;
		return this;
	}

	public float getCenterX()
	{
		return w / 2;
	}

	public float getCenterY()
	{
		return h / 2;
	}


	public void addToX(float x)
	{
		this.x += x;
	}
}
