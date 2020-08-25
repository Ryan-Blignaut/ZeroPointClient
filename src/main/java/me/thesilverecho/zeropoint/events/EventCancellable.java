package me.thesilverecho.zeropoint.events;

public abstract class EventCancellable<T extends Listener> extends Event<T>
{
	private boolean cancelled = false;

	public void cancel()
	{
		cancelled = true;
	}

	public boolean isCancelled()
	{
		return cancelled;
	}

}
