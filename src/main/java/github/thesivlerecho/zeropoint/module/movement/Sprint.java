package github.thesivlerecho.zeropoint.module.movement;

import github.thesivlerecho.zeropoint.module.Module;
import github.thesivlerecho.zeropoint.module.ModuleCategory;
import net.minecraft.client.options.KeyBinding;
import org.lwjgl.glfw.GLFW;

public class Sprint extends Module
{

	public Sprint()
	{
		super("sprint", new KeyBinding(null, GLFW.GLFW_KEY_ENTER, null), ModuleCategory.PLAYER);
	}

	@Override
	public void onTick()
	{
		if (!this.isToggled())
			return;

		mc.player.setSprinting(true);
	}
}
