package me.thesilverecho.zeropoint.config;

import me.thesilverecho.zeropoint.ZeroPointClient;

public class Settings
{
	private static final Settings instance = new Settings();

	@ConfigOption
	public static String SLOWNESS = "123";


	public static void create()
	{
		ZeroPointClient.CONFIG.register(instance);
	}

	public static void save()
	{
		ZeroPointClient.CONFIG.save();
	}
}
