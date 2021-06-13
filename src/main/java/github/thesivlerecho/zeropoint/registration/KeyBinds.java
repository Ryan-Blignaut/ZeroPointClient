package github.thesivlerecho.zeropoint.registration;

import net.fabricmc.fabric.impl.client.keybinding.KeyBindingRegistryImpl;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

import java.lang.reflect.Field;

public class KeyBinds
{

	public static final KeyBinding SWAP_ARMOUR = new KeyBinding("SwapArmor", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_H, "keys");
	public static final KeyBinding PERSPECTIVE = new KeyBinding("perspective", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_R, "keys");
	public static final KeyBinding MENU = new KeyBinding("menu", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_RIGHT_SHIFT, "keys");
	public static final KeyBinding ZOOM = new KeyBinding("zoom", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_Z, "keys");
	private static final String CATEGORY = "keys";
	public static final KeyBinding RELOAD = new KeyBinding("test", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_F4, CATEGORY);
	public static final KeyBinding BINDING = new KeyBinding("Reload", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_M, CATEGORY);
	public static final KeyBinding GLINT_SCREEN_KEY = new KeyBinding("GlintScreen", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_F10, CATEGORY);
	public static final KeyBinding BLOCK_KEY = new KeyBinding("block", InputUtil.Type.MOUSE, GLFW.GLFW_MOUSE_BUTTON_3, CATEGORY);

	public static void registerKeys()
	{
		for (Field declaredField : KeyBinds.class.getDeclaredFields())
			//safety first
			if (declaredField.getType() == KeyBinding.class)
				try
				{
					KeyBindingRegistryImpl.registerKeyBinding((KeyBinding) declaredField.get(KeyBinding.class));
				} catch (IllegalAccessException e)
				{
					e.printStackTrace();
				}

	}
}
