package github.thesivlerecho.zeropoint.util;

import java.awt.*;

public class ColourUtil
{
	private final float r, g, b, a;

	public ColourUtil(float r, float g, float b, float a)
	{
		this.r = r;
		this.g = g;
		this.b = b;
		this.a = a;
	}

	public ColourUtil(int r, int g, int b, int a)
	{
		this.r = r / 255f;
		this.g = g / 255f;
		this.b = b / 255f;
		this.a = a / 255f;
	}

	public ColourUtil(Color color)
	{
		this.r = color.getRed();
		this.g = color.getGreen();
		this.b = color.getBlue();
		this.a = color.getAlpha();
	}


	public int getColour()
	{

		int red = Math.round(255 * r);
		int green = Math.round(255 * g);
		int blue = Math.round(255 * b);
		int alpha = Math.round(255 * a);

		alpha = (alpha << 24) & 0xFF/*0x00FF0000*/;
		red = (red << 16) & 0xFF/*0x00FF0000*/;
		green = (green << 8) & 0xFF/*0x0000FF00*/;
		blue = blue & 0xFF/*0x000000FF*/;

		return alpha | red | green | blue;
	}

	public float getR()
	{
		return r;
	}

	public float getG()
	{
		return g;
	}

	public float getB()
	{
		return b;
	}

	public float getA()
	{
		return a;
	}
}
