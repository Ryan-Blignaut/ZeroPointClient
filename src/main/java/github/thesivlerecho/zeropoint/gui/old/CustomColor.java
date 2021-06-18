package github.thesivlerecho.zeropoint.gui.old;

public class CustomColor
{
	private final int r, g, b, a;

	public CustomColor(int r, int g, int b, int a)
	{
		this.r = r;
		this.g = g;
		this.b = b;
		this.a = a;
	}

	public CustomColor(int r, int g, int b)
	{
		this.r = r;
		this.g = g;
		this.b = b;
		this.a = 255;
	}

	public CustomColor(int col)
	{
		this.r = col >> 16 & 255;
		this.g = col >> 8 & 255;
		this.b = col & 255;
		this.a = col >> 24 & 255;
		/*	return new CustomColor(r, g, b, a);*/
	}

	public int getIntVal()
	{
		return (a & 255) << 24 | (r & 255) << 16 | (g & 255) << 8 | b & 255;
	}

	public int getRed()
	{
		return r;
	}

	public int getGreen()
	{
		return g;
	}

	public int getBlue()
	{
		return b;
	}

	public int getAlpha()
	{
		return a;
	}
}
