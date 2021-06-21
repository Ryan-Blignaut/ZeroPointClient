package github.thesivlerecho.zeropoint;

import github.thesivlerecho.zeropoint.event.EventManager;
import github.thesivlerecho.zeropoint.event.events.InitialisedEvent;
import github.thesivlerecho.zeropoint.event.events.TickEvent;
import github.thesivlerecho.zeropoint.gui.old.DrawingHelper;
import github.thesivlerecho.zeropoint.gui.old.overlay.watermark.WatermarkType;
import github.thesivlerecho.zeropoint.gui.old.screen.PositioningScreen;
import github.thesivlerecho.zeropoint.module.ModuleManager;
import github.thesivlerecho.zeropoint.module.impl.ToolEquip;
import github.thesivlerecho.zeropoint.module.impl.sound.Sounds;
import github.thesivlerecho.zeropoint.util.KeyBinds;
import github.thesivlerecho.zeropoint.render.shader.ShaderManager;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.registry.Registry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Environment(EnvType.CLIENT)
public class ZeroPointClient implements ClientModInitializer
{


	public static final String MOD_ID = "zero-point";
	public static final Logger LOGGER = LogManager.getLogger("Zero Point");
	public static final WatermarkType WATERMARK_TYPE = WatermarkType.TOP;
	public static final DrawingHelper DRAWING_HELPER = new DrawingHelper();

	private static void onTick(MinecraftClient minecraftClient)
	{
		ClientPlayerEntity player = minecraftClient.player;
		ClientWorld world = minecraftClient.world;

		if (player == null || world == null)
			return;
		EventManager.call(new TickEvent());

//		KeystrokesRenderer.getInstance().updateKeystrokes();
		if (KeyBinds.MENU.wasPressed())
		{
			MinecraftClient.getInstance().openScreen(new PositioningScreen());
		}


//		ModPerspective.tickPerspective();
	}

	@Override
	public void onInitializeClient()
	{
		ModuleManager.registerModules("github.thesivlerecho.zeropoint.module", "impl");
		EventManager.call(new InitialisedEvent());

//		Settings.create();
		ShaderManager.initShaders();
		KeyBinds.registerKeys();

		Sounds.SOUNDS_MAP.forEach((soundCategory, soundEvent) -> Registry.register(Registry.SOUND_EVENT, soundEvent.getId(), soundEvent));
		ClientTickEvents.START_CLIENT_TICK.register(ZeroPointClient::onTick);
		ClientTickEvents.END_CLIENT_TICK.register(minecraftClient -> ToolEquip.update());
	}


}
