package me.thesilverecho.zeropoint.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Environment(EnvType.CLIENT) public class ZeroPointClient implements ClientModInitializer
{

	public static final Logger LOGGER = LogManager.getLogger("Zero Point");

	@Override public void onInitializeClient()
	{
		LOGGER.info("Hello From ZeroPoint Client");
		ClientTickEvents.START_CLIENT_TICK.register(minecraftClient -> LOGGER.info("run"));
	}
}
