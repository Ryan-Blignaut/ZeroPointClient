package github.thesivlerecho.zeropoint.module;

import github.thesivlerecho.zeropoint.module.impl.render.*;

import java.util.concurrent.CopyOnWriteArrayList;

public class ClientModManager
{
	private static final CopyOnWriteArrayList<BaseModule> CLIENT_MODS = new CopyOnWriteArrayList<>();

	public static CopyOnWriteArrayList<BaseModule> getClientMods()
	{
		return CLIENT_MODS;
	}

	public static void registerMods()
	{
		CLIENT_MODS.add(new ItemPhysics());
		CLIENT_MODS.add(new KeyStrokes());
		CLIENT_MODS.add(new ChestEsp());
		CLIENT_MODS.add(new CustomTooltips());
		CLIENT_MODS.add(new ShulkerTooltip());

	}

}
