package me.thesilverecho.zeropoint.gui.overlay.keystrokes;

import com.google.common.collect.ImmutableBiMap;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.MatrixStack;

import java.util.ArrayList;

public class KeystrokesRenderer
{

	private static ArrayList<Key> keyStream;
	private static KeystrokesRenderer renderer;

	private KeystrokesRenderer()
	{
//ImmutableList<Key> immutableList =
		keyStream = Lists.newArrayList(new Key(MinecraftClient.getInstance().options.keyForward, 26, 2),
				new Key(MinecraftClient.getInstance().options.keyBack, 26, 26),
				new Key(MinecraftClient.getInstance().options.keyLeft, 2, 26),
				new Key(MinecraftClient.getInstance().options.keyRight, 50, 26));
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
