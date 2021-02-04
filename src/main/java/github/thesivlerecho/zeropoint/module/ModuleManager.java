package github.thesivlerecho.zeropoint.module;

import github.thesivlerecho.zeropoint.module.movement.Sprint;
import github.thesivlerecho.zeropoint.module.movement.Velocity;
import github.thesivlerecho.zeropoint.module.player.Brightness;
import github.thesivlerecho.zeropoint.module.render.Esp;

import java.util.ArrayList;

public class ModuleManager
{
	public static ArrayList<Module> ACTIVE_MODULES = new ArrayList<>();

	static
	{
		ACTIVE_MODULES.add(new Sprint());
		ACTIVE_MODULES.add(new Velocity());
		ACTIVE_MODULES.add(new Brightness());
		ACTIVE_MODULES.add(new Esp());

	}

	public ArrayList<Module> getModules()
	{
		return ACTIVE_MODULES;
	}

	public Module getModuleByName(String string)
	{
		return ACTIVE_MODULES.stream().filter(module -> module.getName().equalsIgnoreCase(string)).findFirst().orElse(null);
	}

}
