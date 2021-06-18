package github.thesivlerecho.zeropoint.mod;

import java.util.concurrent.atomic.AtomicBoolean;

public class ClientMod
{
	private AtomicBoolean enabled;
	private final String name;
	private final ModCategory category;

	public ClientMod(String name, AtomicBoolean on, ModCategory category)
	{
		this.name = name;
		this.category = category;
		this.enabled = on;
	}

	public ClientMod(String name, ModCategory category)
	{
		this.name = name;
		this.category = category;
	}

	public void toggle()
	{
		if (isEnabled()) onDisable();
		else onEnable();
	}

	public void onEnable()
	{
		enabled.set(true);
	}

	public void onDisable()
	{
		enabled.set(false);
	}

	public boolean isEnabled()
	{
		return enabled.get();
	}


}
