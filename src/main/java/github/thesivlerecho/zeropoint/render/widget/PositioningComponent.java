package github.thesivlerecho.zeropoint.render.widget;

public class PositioningComponent
{
	private float x, y, w, h;

	public PositioningComponent(float x, float y, float width, float height)
	{
		this.x = x;
		this.y = y;
		this.w = width;
		this.h = height;
	}

	public PositioningComponent(PositioningComponent other)
	{
		this.x = other.x;
		this.y = other.y;
		this.w = other.w;
		this.h = other.h;
	}

	public PositioningComponent clone()
	{
		return new PositioningComponent(this);
	}


	public void setX(float x)
	{
		this.x = x;
	}

	public void setY(float y)
	{
		this.y = y;
	}


	public PositioningComponent staticResizeComponent(float size)
	{
		return new PositioningComponent(this.x - size, this.y - size, this.w + size, this.h + size);
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

	public float getActualWidth()
	{
		return w;
	}

	public float getActualHeight()
	{
		return h;
	}

	public PositioningComponent setW(float w)
	{
		this.w = w;
		return this;
	}

	public PositioningComponent setH(float h)
	{
		this.h = h;
		return this;
	}
}
