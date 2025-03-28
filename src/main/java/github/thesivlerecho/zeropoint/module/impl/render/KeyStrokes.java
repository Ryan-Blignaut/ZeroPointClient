package github.thesivlerecho.zeropoint.module.impl.render;

import github.thesivlerecho.zeropoint.config.selector.ConfigOption;
import github.thesivlerecho.zeropoint.event.EventListener;
import github.thesivlerecho.zeropoint.event.events.Render2dEvent;
import github.thesivlerecho.zeropoint.event.events.TickEvent;
import github.thesivlerecho.zeropoint.guiv2.KeystrokeBase;
import github.thesivlerecho.zeropoint.module.BaseModule;
import github.thesivlerecho.zeropoint.module.ModCategory;
import github.thesivlerecho.zeropoint.module.ZPModule;
import github.thesivlerecho.zeropoint.render.widget.ContainerComponent;
import net.minecraft.client.option.GameOptions;
import net.minecraft.util.math.Vec2f;

@ZPModule(name = "Keystroke", category = ModCategory.HUD)
public class KeyStrokes extends BaseModule
{

	public static final float DIMENSIONS = 22;

	@ConfigOption
	public static Vec2f offset = new Vec2f(32, 32);

	private ContainerComponent<KeystrokeBase> container;


	@EventListener
	public void render(Render2dEvent event)
	{
		container.render(event.matrixStack());
	}

	@EventListener
	public void update(TickEvent event)
	{
		container = getContainer();
		container.getChildren().forEach(KeystrokeBase::updateKey);
	}

	private ContainerComponent<KeystrokeBase> getContainer()
	{

		if (container == null)
		{
			final GameOptions options = MINECRAFT_CLIENT.options;
			final ContainerComponent<KeystrokeBase> keystrokeBaseContainerComponent = new ContainerComponent<>();
			keystrokeBaseContainerComponent.addChildren(
					new KeystrokeBase(options.keyForward, DIMENSIONS, 0),
					new KeystrokeBase(options.keyBack, DIMENSIONS, DIMENSIONS),
					new KeystrokeBase(options.keyLeft, 0, DIMENSIONS),
					new KeystrokeBase(options.keyRight, DIMENSIONS * 2, DIMENSIONS),
					new KeystrokeBase(options.keyAttack, 0, DIMENSIONS * 2, DIMENSIONS * 3 / 2, DIMENSIONS),
					new KeystrokeBase(options.keyUse, DIMENSIONS * 3 / 2, DIMENSIONS * 2, DIMENSIONS * 3 / 2, DIMENSIONS),
					new KeystrokeBase(options.keyJump, 0, DIMENSIONS * 3, DIMENSIONS * 3, DIMENSIONS));
			return keystrokeBaseContainerComponent;
		}


		return container;
	}


}
