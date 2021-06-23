package github.thesivlerecho.zeropoint.config;


public class Settings
{


	public static void create()
	{
		Config.CLIENT_INSTANCE.register(new Settings());
	}

	public static void save()
	{
		Config.CLIENT_INSTANCE.save();
	}
}
