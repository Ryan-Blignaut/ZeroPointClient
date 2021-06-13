package github.thesivlerecho.zeropoint.guiv2;

import com.google.common.collect.Lists;
import github.thesivlerecho.zeropoint.gui.overlay.keystrokes.BaseKey;
import github.thesivlerecho.zeropoint.gui.overlay.keystrokes.Key;
import github.thesivlerecho.zeropoint.gui.overlay.keystrokes.SpaceKey;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.util.math.MatrixStack;

import java.util.ArrayList;

public class KeystrokeManager
{

	private static ArrayList<BaseKey> keyStream;
	private static KeystrokeManager renderer;

	private KeystrokeManager()
	{
		final GameOptions options = MinecraftClient.getInstance().options;


		keyStream = Lists.newArrayList(
				new Key(options.keyForward, 22, 0),
				new Key(options.keyBack, 22, 22),
				new Key(options.keyLeft, 0, 22),
				new Key(options.keyRight, 44, 22),
//				new MouseKey(options.keyAttack, 2, 26),
//				new MouseKey(options.keyUse, 2, 26),
				new SpaceKey(options.keyJump, 0, 44, 66, 22)
		);
	}

	public static KeystrokeManager getInstance()
	{
		if (renderer == null) renderer = new KeystrokeManager();
		return renderer;
	}

	public void renderKeystrokes(MatrixStack m)
	{
		keyStream.forEach(key -> key.renderKey(m));
	}

	public void updateKeystrokes()
	{
		keyStream.forEach(BaseKey::updateKey);
	}


}
