package github.thesivlerecho.zeropoint.mod.impl;

import github.thesivlerecho.zeropoint.config.Settings;
import github.thesivlerecho.zeropoint.registration.KeyBinds;
import net.minecraft.client.MinecraftClient;

public class ModPerspective
{

	private static final MinecraftClient m = MinecraftClient.getInstance();
	public static boolean perspectiveEnabled;
	public static float cameraPitch;
	public static float cameraYaw;
	private static boolean held;

	public static void tickPerspective()
	{
		if (Settings.PERSPECTIVE_HELD)
		{
			if (perspectiveEnabled = KeyBinds.PERSPECTIVE.isPressed() && !held)
			{
				held = true;
				cameraPitch = m.player.getPitch();
				cameraYaw = m.player.getYaw();
				m.options.setPerspective(net.minecraft.client.option.Perspective.THIRD_PERSON_BACK);
			}
		} else
		{
			if (KeyBinds.PERSPECTIVE.wasPressed())
			{
				perspectiveEnabled = !perspectiveEnabled;

				cameraPitch = m.player.getPitch();
				;
				cameraYaw = m.player.getYaw();

				m.options.setPerspective(perspectiveEnabled ? net.minecraft.client.option.Perspective.THIRD_PERSON_BACK : net.minecraft.client.option.Perspective.FIRST_PERSON);
			}
		}

		if (!perspectiveEnabled && held)
		{
			held = false;
			m.options.setPerspective(net.minecraft.client.option.Perspective.FIRST_PERSON);
		}

		if (perspectiveEnabled && m.options.getPerspective() != net.minecraft.client.option.Perspective.THIRD_PERSON_BACK)
			perspectiveEnabled = false;
	}

}


