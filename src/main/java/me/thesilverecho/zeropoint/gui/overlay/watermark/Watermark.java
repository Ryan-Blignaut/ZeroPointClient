package me.thesilverecho.zeropoint.gui.overlay.watermark;

import me.thesilverecho.zeropoint.ZeroPointClient;
import me.thesilverecho.zeropoint.gui.Icon;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class Watermark
{

	private static final Icon icon = new Icon(new Identifier("zero-point", "textures/ui/overlay.png"), 0, 0, 1, 1, true, false);

	public static void render(MatrixStack matrices, int x, int y, int backgroundWidth, int backgroundHeight, int width, int height)
	{
		matrices.push();
		switch (ZeroPointClient.WATERMARK_TYPE)
		{
		case TOP:
			ZeroPointClient.DRAWING_HELPER.drawIcon(matrices, icon, x + backgroundWidth / 2f - 60, y - 27 - 5, x + backgroundWidth / 2f+45, y - 1);
			break;
		case BOTTOM:
			ZeroPointClient.DRAWING_HELPER.drawIcon(matrices, icon, (backgroundWidth - icon.getLeft()) / 2f, backgroundHeight - icon.getTop(),
					(backgroundWidth - icon.getRight()), backgroundHeight - icon.getBottom());
			break;
		case LEFT:

			//			ZeroPointClient.DRAWING_HELPER.drawIcon(matrices, icon, (backgroundWidth - icon.getLeft()) / 2f, backgroundHeight - icon.getTop(),
			//					(backgroundWidth - icon.getRight()), backgroundHeight - icon.getBottom());
			break;
		}




		/*BadlionClient.badlionTextureManager.bindTextureMipmapped(GuiBadlionMainMenu.badlionClientHeader);
		BetterframesConfig.WatermarkLocation watermarkLocation7 = BadlionClient.getInstance().getWrapper().getActiveModProfile()
		                                                                       .getBetterframesConfig().getWatermarkLocation();

		if (watermarkLocation7 == BetterframesConfig.WatermarkLocation.ABOVE_INVENTORY && integer2 - 27 - 5 < 0)
		{
			watermarkLocation7 = BetterframesConfig.WatermarkLocation.BOTTOM_RIGHT;
		} else if (watermarkLocation7 == BetterframesConfig.WatermarkLocation.UNDER_INVENTORY && integer2 + integer4 + 5 + 27 > integer6)
		{
			watermarkLocation7 = BetterframesConfig.WatermarkLocation.BOTTOM_RIGHT;
		}
		switch (watermarkLocation7)
		{
		case ABOVE_INVENTORY:
		{
			BadlionClient.gui.a(integer1 + integer3 / 2 - 45, integer2 - 27 - 5, 0.0f, 0.0f, 90, 27, 90.0f, 27.0f);
			break;
		}
		case UNDER_INVENTORY:
		{
			BadlionClient.gui.a(integer1 + (integer3 - 90), integer2 + integer4 + 5, 0.0f, 0.0f, 90, 27, 90.0f, 27.0f);
			break;
		}
		case BOTTOM_RIGHT:
		{
			BadlionClient.gui.a(integer5 - 90 - 5, integer6 - 27 - 5, 0.0f, 0.0f, 90, 27, 90.0f, 27.0f);
			break;
		}
		}*/
		matrices.pop();
	}
}
