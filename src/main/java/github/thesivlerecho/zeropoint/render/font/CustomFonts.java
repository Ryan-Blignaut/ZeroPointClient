package github.thesivlerecho.zeropoint.render.font;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public enum CustomFonts
{
	CLIENT_FONT("font.ttf");


	private Font fontAws;
	private final boolean aa, fm;
	private CFont font;

	CustomFonts(String fontAws)
	{
		this(fontAws, true, true);
	}

	CustomFonts(String fontAws, boolean aa, boolean fm)
	{
		this.aa = aa;
		this.fm = fm;
		final InputStream stream = CustomFonts.class.getResourceAsStream("/assets/zero-point/font/" + fontAws);
		try
		{
			assert stream != null;
			this.fontAws = Font.createFont(Font.TRUETYPE_FONT, stream).deriveFont(29f);
		} catch (FontFormatException | IOException e)
		{
			e.printStackTrace();
		}
	}

	public void initFont()
	{
		this.font = new CFont(fontAws, aa, fm);
	}

	public CFont getFont()
	{
		return font;
	}
}
