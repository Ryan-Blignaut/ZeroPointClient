package github.thesivlerecho.zeropoint.module.impl.render;

import github.thesivlerecho.zeropoint.config.selector.ConfigOption;
import github.thesivlerecho.zeropoint.module.BaseModule;
import github.thesivlerecho.zeropoint.module.ModCategory;

public class Brightness extends BaseModule
{
	@ConfigOption
	private double previousBrightness;

	public Brightness()
	{
		super("Brightness", ModCategory.RENDER);
	}

	@Override
	public void onEnable()
	{
		super.onEnable();
		previousBrightness = (MINECRAFT_CLIENT.options.gamma);
		MINECRAFT_CLIENT.options.gamma = 100;
	}

	@Override
	public void onDisable()
	{
		super.onDisable();
		MINECRAFT_CLIENT.options.gamma = previousBrightness;
	}
}
