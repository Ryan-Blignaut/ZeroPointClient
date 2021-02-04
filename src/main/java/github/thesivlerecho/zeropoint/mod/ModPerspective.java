package github.thesivlerecho.zeropoint.mod;

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
				cameraPitch = m.player.pitch;
				cameraYaw = m.player.yaw;
				m.options.setPerspective(net.minecraft.client.options.Perspective.THIRD_PERSON_BACK);
			}
		} else
		{
			if (KeyBinds.PERSPECTIVE.wasPressed())
			{
				perspectiveEnabled = !perspectiveEnabled;

				cameraPitch = m.player.pitch;
				cameraYaw = m.player.yaw;

				m.options.setPerspective(perspectiveEnabled ? net.minecraft.client.options.Perspective.THIRD_PERSON_BACK : net.minecraft.client.options.Perspective.FIRST_PERSON);
			}
		}

		if (!perspectiveEnabled && held)
		{
			held = false;
			m.options.setPerspective(net.minecraft.client.options.Perspective.FIRST_PERSON);
		}

		if (perspectiveEnabled && m.options.getPerspective() != net.minecraft.client.options.Perspective.THIRD_PERSON_BACK)
			perspectiveEnabled = false;
	}

}


