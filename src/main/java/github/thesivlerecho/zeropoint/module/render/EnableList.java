package github.thesivlerecho.zeropoint.module.render;

import github.thesivlerecho.zeropoint.module.Module;
import github.thesivlerecho.zeropoint.module.ModuleCategory;

public class EnableList extends Module
{

	public EnableList()
	{
		super("List", null, ModuleCategory.RENDER);
	}

	@Override
	public void onEnable()
	{
		super.onEnable();
	}

	@Override
	public void onDisable()
	{
	}

	@Override
	public void on2DRender()
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
