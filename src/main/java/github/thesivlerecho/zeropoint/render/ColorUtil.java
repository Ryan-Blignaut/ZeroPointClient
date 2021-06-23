package github.thesivlerecho.zeropoint.render;

import github.thesivlerecho.zeropoint.util.ZPColour;

public class ColorUtil
{
	public int getIntColor(int r, int g, int b, int a)
	{
		return (a & 255) << 24 | (r & 255) << 16 | (g & 255) << 8 | b & 255;
	}

	public static ZPColour getColor(int color)
	{
		return new ZPColour(color);
	}

}
