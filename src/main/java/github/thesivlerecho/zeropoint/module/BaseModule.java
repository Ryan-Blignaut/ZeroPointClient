package github.thesivlerecho.zeropoint.module;

import net.minecraft.client.MinecraftClient;

public class BaseModule
{
	protected static final MinecraftClient MINECRAFT_CLIENT = MinecraftClient.getInstance();

	private final String name = getModuleInfo().name();
	private final ModCategory category = getModuleInfo().category();
	private boolean active = getModuleInfo().active();

	public void toggle()
	{
		if (isEnabled()) onDisable();
		else onEnable();
	}

	public void onEnable()
	{
		active = true;
	}

	public void onDisable()
	{
		active = false;
	}

	public boolean isEnabled()
	{
		return active;
	}


	private ZPModule getModuleInfo()
	{
		return getClass().getAnnotation(ZPModule.class);
	}

}
