/*
package github.thesivlerecho.zeropoint.guiv2;

import github.thesivlerecho.zeropoint.render.widget.ContainerComponent;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.util.math.MatrixStack;

public class KeystrokeManager
{

	private static final ContainerComponent COMPONENT = new ContainerComponent();
	private static KeystrokeManager manager;

	public KeystrokeManager()
	{
		final GameOptions options = MinecraftClient.getInstance().options;
		final int keyDimensions = KeystrokeBase.BASE_KEY_SIZE;
		COMPONENT.addChildren();

		keys.add(new KeystrokeBase(options.keyForward, keyDimensions, 0));
		keys.add(new KeystrokeBase(options.keyBack, keyDimensions, keyDimensions));
		keys.add(new KeystrokeBase(options.keyLeft, 0, keyDimensions));
		keys.add(new KeystrokeBase(options.keyRight, keyDimensions * 2, keyDimensions));
		keys.add(new KeystrokeBase(options.keyAttack, 0, keyDimensions * 2, keyDimensions * 3 / 2, keyDimensions));
		keys.add(new KeystrokeBase(options.keyUse, keyDimensions * 3 / 2, keyDimensions * 2, keyDimensions * 3 / 2, keyDimensions));
		keys.add(new KeystrokeBase(options.keyJump, 0, keyDimensions * 3, keyDimensions * 3, keyDimensions));
	}

	public static KeystrokeManager getInstance()
	{
		if (manager == null)
			manager = new KeystrokeManager();
		return manager;
	}

	public void renderKeystrokes(MatrixStack m)
	{
		keys.forEach(key -> key.renderKey(m));
	}

	public void updateKeystrokes()
	{
		keys.forEach(KeystrokeBase::updateKey);
	}


}
*/
