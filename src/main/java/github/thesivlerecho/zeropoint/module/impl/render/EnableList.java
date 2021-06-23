package github.thesivlerecho.zeropoint.module.impl.render;


import github.thesivlerecho.zeropoint.event.EventListener;
import github.thesivlerecho.zeropoint.event.events.Render2dEvent;
import github.thesivlerecho.zeropoint.module.BaseModule;
import github.thesivlerecho.zeropoint.module.ModCategory;
import github.thesivlerecho.zeropoint.module.ZPModule;

@ZPModule(name = "Enable List", category = ModCategory.RENDER, shouldDraw = false)
public class EnableList extends BaseModule
{
	@EventListener
	public void on2DRender(Render2dEvent event)
	{
	}
}
