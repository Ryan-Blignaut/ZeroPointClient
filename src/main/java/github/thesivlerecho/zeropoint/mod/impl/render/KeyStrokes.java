package github.thesivlerecho.zeropoint.mod.impl.render;

import github.thesivlerecho.zeropoint.event.TargetEvent;
import github.thesivlerecho.zeropoint.event.events.Render2dEvent;
import github.thesivlerecho.zeropoint.event.events.TickEvent;
import github.thesivlerecho.zeropoint.guiv2.KeystrokeManager;
import github.thesivlerecho.zeropoint.mod.ClientMod;
import github.thesivlerecho.zeropoint.mod.ModCategory;

import java.util.concurrent.atomic.AtomicBoolean;

public class KeyStrokes extends ClientMod
{

	public KeyStrokes()
	{
		super("Keystroke", new AtomicBoolean(true), ModCategory.PLAYER);
	}

	@TargetEvent
	public void render(Render2dEvent event)
	{
		KeystrokeManager.getInstance().renderKeystrokes(event.matrixStack());
	}

	@TargetEvent
	public void update(TickEvent event)
	{
		KeystrokeManager.getInstance().updateKeystrokes();
	}


}
