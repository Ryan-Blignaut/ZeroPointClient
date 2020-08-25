package me.thesilverecho.zeropoint.gui.overlay.keystrokes;

import com.google.common.collect.Lists;
import jchroma.utils.KeyboardKeys;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.MatrixStack;

import java.util.ArrayList;

public class KeystrokesRenderer
{

	private static ArrayList<Key> keyStream;
	private static KeystrokesRenderer renderer;

	private KeystrokesRenderer()
	{

		keyStream = Lists.newArrayList(new Key(MinecraftClient.getInstance().options.keyForward, 26, 2, KeyboardKeys.RZKEY_W),
				new Key(MinecraftClient.getInstance().options.keyBack, 26, 26, KeyboardKeys.RZKEY_S),
				new Key(MinecraftClient.getInstance().options.keyLeft, 2, 26, KeyboardKeys.RZKEY_A),
				new Key(MinecraftClient.getInstance().options.keyRight, 50, 26, KeyboardKeys.RZKEY_D));
	}

	public static KeystrokesRenderer getInstance()
	{
		if (renderer == null)
			renderer = new KeystrokesRenderer();
		return renderer;
	}

	public void renderKeystrokes(MatrixStack m)
	{
		keyStream.forEach(key -> key.renderKey(m));
	}

}
