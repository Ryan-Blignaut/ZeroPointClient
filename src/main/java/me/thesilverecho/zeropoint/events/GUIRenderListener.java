package me.thesilverecho.zeropoint.events;

import java.util.ArrayList;

public interface GUIRenderListener extends Listener
{
	public void onRenderGUI(float partialTicks);

	public static class GUIRenderEvent extends Event<GUIRenderListener>
	{
		private final float partialTicks;

		public GUIRenderEvent(float partialTicks)
		{
			this.partialTicks = partialTicks;
		}

		@Override public void fire(ArrayList<GUIRenderListener> listeners)
		{
			for (GUIRenderListener listener : listeners)
				listener.onRenderGUI(partialTicks);
		}

		@Override public Class<GUIRenderListener> getListenerType()
		{
			return GUIRenderListener.class;
		}
	}
}
