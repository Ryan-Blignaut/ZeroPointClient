package github.thesivlerecho.zeropoint.module;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;

import java.util.ArrayList;
import java.util.List;

public abstract class Module
{
	protected final MinecraftClient mc = MinecraftClient.getInstance();
	private final String name;
	private final String displayName;

	private final ModuleCategory moduleCategory;
	private final KeyBinding keyBinding;
	public int anim;
	public float anim1;
	public boolean visible;
	List<Module> mods;
	private long lastPressTime;
	private boolean toggled;
	private int color;

	public Module(String name, KeyBinding keyBinding, ModuleCategory moduleCategory)
	{
		mods = new ArrayList<>();
		this.name = name;
		this.displayName = name;
		this.moduleCategory = moduleCategory;
		this.keyBinding = keyBinding;

		this.toggled = false;
		this.anim = 0;
		this.lastPressTime = 0;

	}

	public Module(String name, String displayName, KeyBinding keyBinding, ModuleCategory moduleCategory)
	{
		this.name = name;
		this.displayName = displayName;
		this.moduleCategory = moduleCategory;
		this.keyBinding = keyBinding;
	}

	public String getName()
	{
		return name;
	}

	public ModuleCategory getModuleCategory()
	{
		return moduleCategory;
	}

	public boolean isToggled()
	{
		return toggled;
	}

	public Module setToggled(boolean b)
	{
		this.toggled = b;
		if (b)
			onEnable();
		else
			onDisable();
		return this;
	}

	public Module toggleEnable()
	{
		setToggled(!toggled);
		return this;
	}

	public void keyPressed()
	{
		if (keyBinding != null)
			if (keyBinding.wasPressed())
				toggleEnable();
	}

	public void onToggle()
	{
		if (this.anim != 0)
			this.anim = 0;
	}

	public void onEnable()
	{
		if (this.anim != 0)
			this.anim = 0;
	}

	public void onDisable()
	{
		if (this.anim != 0)
			this.anim = 0;
	}

	public void onTick()
	{
	}

	public void on2DRender()
	{
	}

	public long getLastPressTime()
	{
		return lastPressTime;
	}

	public Module setLastPressTime(long lastPressTime)
	{
		this.lastPressTime = lastPressTime;
		return this;
	}
}
