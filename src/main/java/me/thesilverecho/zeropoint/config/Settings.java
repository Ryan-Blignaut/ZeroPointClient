package me.thesilverecho.zeropoint.config;

import me.thesilverecho.zeropoint.ZeroPointClient;
import me.thesilverecho.zeropoint.gui.potionHud.EffectSetting;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public class Settings
{
	private static final Settings instance = new Settings();

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

	public static void create()
	{
		ZeroPointClient.CONFIG.register(instance);
	}

	public static void save()
	{
		ZeroPointClient.CONFIG.save();
	}
}
