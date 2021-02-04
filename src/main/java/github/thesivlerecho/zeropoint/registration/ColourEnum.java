package github.thesivlerecho.zeropoint.registration;

import java.awt.*;

public enum ColourEnum
{
	DEFAULT(254, 254, 254), BLACK(25, 25, 25), BLUE(178, 76, 51), BROWN(51, 76, 102), CYAN(153, 127, 76), GRAY(76, 76, 76), GREEN(51, 127,
		102), LIGHT_BLUE(216, 153, 102), LIME(25, 204, 127), MAGENTA(216, 76, 178), ORANGE(51, 127, 216), PINK(165, 127, 242), PURPLE(178, 63,
		127), RED(51, 51, 153), LIGHT_GRAY(153, 153, 153), WHITE(255, 255, 255), YELLOW(51, 229, 229);

	private final int r, g, b;

	ColourEnum(int r, int g, int b)
	{
		this.r = r;
		this.g = g;
		this.b = b;
	}

	public static ColourEnum fromName(String string)
	{
		for (final ColourEnum c : values())
		{
			if (c.name().equalsIgnoreCase(string))
			{
				return c;
			}
		}
		return DEFAULT;
	}

	public int getColor(int alpha)
	{
		if (alpha < 0)
		{
			alpha = 0;
		}
		if (alpha > 255)
		{
			alpha = 255;
		}
		if (this == ColourEnum.DEFAULT)
		{
			alpha = 255;
		}
		return new Color(r, g, b, alpha).getRGB();
	}
}
