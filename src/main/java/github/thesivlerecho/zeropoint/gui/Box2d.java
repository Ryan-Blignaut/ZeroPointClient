package github.thesivlerecho.zeropoint.gui;

public class Box2d
{
	private final float left, top, right, bottom;

	public Box2d(float left, float top, float right, float bottom)
	{
		this.left = left;
		this.top = top;
		this.right = left + right;
		this.bottom = top + bottom;
	}

	public float getLeft()
	{
		return left;
	}

	public float getTop()
	{
		return top;
	}

	public float getRight()
	{
		return right;
	}

	public float getBottom()
	{
		return bottom;
	}
}
