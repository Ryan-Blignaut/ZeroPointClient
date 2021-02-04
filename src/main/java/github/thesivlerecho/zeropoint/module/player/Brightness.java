package github.thesivlerecho.zeropoint.module.player;

import github.thesivlerecho.zeropoint.module.Module;
import github.thesivlerecho.zeropoint.module.ModuleCategory;
import net.minecraft.client.options.KeyBinding;
import org.lwjgl.glfw.GLFW;

public class Brightness extends Module
{
	private double previousBrightness;

	public Brightness()
	{
		super("Brightness", new KeyBinding(null, GLFW.GLFW_KEY_N, null), ModuleCategory.PLAYER);
	}

	@Override
	public void onEnable()
	{
		super.onEnable();
		previousBrightness = (mc.options.gamma);
		mc.options.gamma = 100;
	}

	@Override
	public void onDisable()
	{
		super.onDisable();
		mc.options.gamma = previousBrightness;
	}
}
