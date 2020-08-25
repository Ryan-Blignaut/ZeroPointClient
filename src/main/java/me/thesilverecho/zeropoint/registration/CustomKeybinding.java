package me.thesilverecho.zeropoint.registration;

import net.minecraft.client.options.KeyBinding;
import net.minecraft.client.util.InputUtil;

public class CustomKeybinding extends KeyBinding
{
	private Runnable runTask;

	public CustomKeybinding(String translationKey, InputUtil.Type type, int code, Runnable runnable)
	{
		super(translationKey, type, code, "keys");
		runTask = runnable;
	}

	@Override public boolean isPressed()
	{
		runTask.run();
		return super.isPressed();
	}

	@Override public boolean wasPressed()
	{
		runTask.run();
		return super.wasPressed();
	} 
}
