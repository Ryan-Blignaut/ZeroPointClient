package github.thesivlerecho.zeropoint.module.impl.render;


import github.thesivlerecho.zeropoint.event.TargetEvent;
import github.thesivlerecho.zeropoint.event.events.Render2dEvent;
import github.thesivlerecho.zeropoint.module.BaseModule;
import github.thesivlerecho.zeropoint.module.ModCategory;

public class EnableList extends BaseModule
{

	public EnableList()
	{
		super("List", ModCategory.CLIENT);
	}

	@TargetEvent
	public void on2DRender(Render2dEvent event)
	{
	}
}
