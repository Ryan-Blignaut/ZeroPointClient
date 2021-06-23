package github.thesivlerecho.zeropoint.util;

public class ZPColour
{

	private final int r, g, b, a;

	public ZPColour(int color)
	{
		this.r = color >> 16 & 255;
		this.g = color >> 8 & 255;
		this.b = color & 255;
		this.a = color >> 24 & 255;
	}

	public int getR()
	{
		return r;
	}

	public int getG()
	{
		return g;
	}

	public int getB()
	{
		return b;
	}

	public int getA()
	{
		return a;
	}

}
