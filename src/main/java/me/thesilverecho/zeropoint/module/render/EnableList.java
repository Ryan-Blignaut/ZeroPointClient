package me.thesilverecho.zeropoint.module.render;

import me.thesilverecho.zeropoint.module.Module;
import me.thesilverecho.zeropoint.module.ModuleCategory;

public class EnableList extends Module
{

	public EnableList()
	{
		super("List", null, ModuleCategory.RENDER);
	}

	@Override public void onEnable()
	{
		super.onEnable();
	}

	@Override public void onDisable()
	{
	}

	@Override public void on2DRender()
	{
		if (isToggled())
		{
			drawList();
		}
	}

	private void drawList()
	{
	}
}
