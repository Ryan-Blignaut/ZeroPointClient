package github.thesivlerecho.zeropoint.module;

import net.minecraft.client.MinecraftClient;

import java.util.concurrent.atomic.AtomicBoolean;

public class BaseModule
{
	protected static final MinecraftClient MINECRAFT_CLIENT = MinecraftClient.getInstance();
	private AtomicBoolean enabled;
	private final String name;
	private final github.thesivlerecho.zeropoint.module.ModCategory category;

	public BaseModule(String name, AtomicBoolean on, ModCategory category)
	{
		this.name = name;
		this.category = category;
		this.enabled = on;
	}

	public BaseModule(String name, ModCategory category)
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
