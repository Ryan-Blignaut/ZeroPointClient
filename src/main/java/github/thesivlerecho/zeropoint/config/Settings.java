package github.thesivlerecho.zeropoint.config;


import github.thesivlerecho.zeropoint.ZeroPointClient;
import github.thesivlerecho.zeropoint.gui.CustomColor;
import github.thesivlerecho.zeropoint.gui.potionHud.EffectSetting;
import net.minecraft.util.math.Vec2f;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public class Settings
{
	private static final Settings instance = new Settings();

	@ConfigOption
	public static Boolean BOUNDING_BOX_ENABLED = true, CUSTOM_BOUNDING_BOX = true;

	@ConfigOption
	public static Integer wingRed = 100, wingGreen = 100, wingBlue = 100;

	@ConfigOption
	@BoolConfigOption(name = "test", category = SettingCategory.SPEED)
	public static AtomicBoolean FLIGHT = new AtomicBoolean(true);

	@ConfigOption
	public static AtomicBoolean renderIcons = new AtomicBoolean(true);
	public static Boolean renderLevelsAbove4 = true, showWhileChat = false, romanNumerals = true;
	@ConfigOption
	public static ArrayList<EffectSetting> EFFECT_SETTINGS = new ArrayList<>();
	@ConfigOption
	public static Float MENU_Y_OFFSET = 300f, MENU_X_OFFSET = 30f;
	@ConfigOption
	public static Boolean PERSPECTIVE_HELD = false;

	@ConfigOption
	public static int TOOLTIP_BACKGROUND_COLOUR = -804318950;
	@ConfigOption
	public static int TOOLTIP_BORDER_TOP_COLOUR = -258762834;
	@ConfigOption
	public static int TOOLTIP_BORDER_BOTTOM_COLOUR = -265079748;

	/*KEYSTROKES CONFIG*/
	@ConfigOption
	public static Vec2f KEY_STROKES_OFFSET = new Vec2f(0, 0);
	@ConfigOption
	public static Boolean KEY_STROKES_CHROMA_ENABLED = true;
	@ConfigOption
	public static CustomColor KEY_STROKES_COLOR = new CustomColor(51, 32, 45, 120),
			KEY_STROKES_COLOR_PRESSED = new CustomColor(52, 59, 73, 170);
	@ConfigOption
	public static Boolean KEY_STROKES_ARROWS = true;


	public static void create()
	{
		ZeroPointClient.CONFIG.register(instance);
	}

	public static void save()
	{
		ZeroPointClient.CONFIG.saveToFile(instance);
	}
}
