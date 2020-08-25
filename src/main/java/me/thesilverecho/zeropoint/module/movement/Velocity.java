package me.thesilverecho.zeropoint.module.movement;

import me.thesilverecho.zeropoint.module.Module;
import me.thesilverecho.zeropoint.module.ModuleCategory;
import net.minecraft.client.options.KeyBinding;
import net.minecraft.util.math.Vec3d;
import org.lwjgl.glfw.GLFW;

public class Velocity extends Module
{

	public Velocity()
	{
		super("Velocity", new KeyBinding("a", GLFW.GLFW_KEY_R, ""), ModuleCategory.MOVEMENT);
	}

	@Override public void onTick()
	{

		if (!isToggled())
			return;
		if (mc.player.isSneaking() || !mc.player.isOnGround() || mc.player.forwardSpeed == 0 && mc.player.sidewaysSpeed == 0)
			return;

		if (mc.player.forwardSpeed > 0 && !mc.player.horizontalCollision)
			mc.player.setSprinting(true);

		Vec3d v = mc.player.getVelocity();
		mc.player.setVelocity(v.x * 1.8, v.y + 0.1, v.z * 1.8);

		v = mc.player.getVelocity();
		double currentSpeed = Math.sqrt(Math.pow(v.x, 2) + Math.pow(v.z, 2));

		double maxSpeed = 0.66F;

		if (currentSpeed > maxSpeed)
			mc.player.setVelocity(v.x / currentSpeed * maxSpeed, v.y, v.z / currentSpeed * maxSpeed);

	}
}
