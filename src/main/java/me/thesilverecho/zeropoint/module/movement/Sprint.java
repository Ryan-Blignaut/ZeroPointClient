package me.thesilverecho.zeropoint.module.movement;

import me.thesilverecho.zeropoint.module.Module;
import me.thesilverecho.zeropoint.module.ModuleCategory;
import net.minecraft.client.options.KeyBinding;
import org.lwjgl.glfw.GLFW;

public class Sprint extends Module
{

	public Sprint()
	{
		super("sprint", new KeyBinding(null, GLFW.GLFW_KEY_ENTER, null), ModuleCategory.PLAYER);
	}

	@Override public void onTick()
	{
		if (!this.isToggled())
			return;

		mc.player.setSprinting(true);
	}
}
