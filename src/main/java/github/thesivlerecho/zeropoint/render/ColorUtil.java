package github.thesivlerecho.zeropoint.render;

public class ColorUtil
{
	public int getIntColor(int r, int g, int b, int a)
	{
		return (a & 255) << 24 | (r & 255) << 16 | (g & 255) << 8 | b & 255;
	}

	public static ColorHolder getColor(int color)
	{
		return new ColorHolder(color);
	}

	public static class ColorHolder
	{
		private final int r, g, b, a;

		public ColorHolder(int color)
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
}
