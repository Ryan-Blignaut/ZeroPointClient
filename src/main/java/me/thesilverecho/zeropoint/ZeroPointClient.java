package me.thesilverecho.zeropoint;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import me.thesilverecho.zeropoint.config.Config;
import me.thesilverecho.zeropoint.gui.DrawingHelper;
import me.thesilverecho.zeropoint.gui.overlay.watermark.WatermarkType;
import me.thesilverecho.zeropoint.gui.screen.SlideOut;
import me.thesilverecho.zeropoint.module.Module;
import me.thesilverecho.zeropoint.module.ModuleManager;
import me.thesilverecho.zeropoint.registration.KeyBinds;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.event.client.ClientSpriteRegistryCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.BufferUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.URL;
import java.nio.FloatBuffer;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Environment(EnvType.CLIENT)
public class ZeroPointClient implements ClientModInitializer
{
	public static final Logger LOGGER = LogManager.getLogger("Zero Point");
	public static final DrawingHelper DRAWING_HELPER = new DrawingHelper();
	public static final WatermarkType WATERMARK_TYPE = WatermarkType.TOP;
	public static final Config CONFIG = new Config(new File(MinecraftClient.getInstance().runDirectory, "Config" + File.separator + "Zero-point.json"));

	public static FloatBuffer cosmicUVs = BufferUtils.createFloatBuffer(4 * 10);
	public static Sprite[] sprites = new Sprite[10];


	public static Map<UUID, User> PLAYER_COSMETICS = new HashMap<>();
	private final Gson gson = new GsonBuilder().create();
	private String url = "https://raw.githubusercontent.com/TheSilverEcho/ZeroPointClient/master/contributors.json";
	private final Type COSMETIC_SELECT_TYPE = new TypeToken<Map<UUID, User>>()
	{
	}.getType();

	@Override
	public void onInitializeClient()
	{
		KeyBinds.registerKeys();
		CompletableFuture.supplyAsync(this::getUserInfo).thenAcceptAsync(userMap ->
		{
			if (userMap != null)
				PLAYER_COSMETICS = userMap;
			else PLAYER_COSMETICS = Collections.emptyMap();
		}, MinecraftClient.getInstance());

		ClientSpriteRegistryCallback.event(SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE).register((spriteAtlasTexture, registry) ->
		{
			for (int i = 0; i < ZeroPointClient.sprites.length; i++)
				registry.register(new Identifier("zero-point", "cosmic/cosmic_" + i));
		});
		ClientTickEvents.START_CLIENT_TICK.register(minecraftClient ->
		{
			ClientPlayerEntity player = minecraftClient.player;
			ClientWorld world = minecraftClient.world;

			if (player == null || world == null)
				return;

			ModuleManager.ACTIVE_MODULES.forEach(Module::onTick);
			ModuleManager.ACTIVE_MODULES.forEach(Module::keyPressed);

			if (KeyBinds.MENU.wasPressed())
				MinecraftClient.getInstance().openScreen(new SlideOut());
		});

	}

	private Map<UUID, User> getUserInfo()
	{
		try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new URL(url).openStream())))
		{
			return gson.fromJson(bufferedReader, COSMETIC_SELECT_TYPE);
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		return null;
	}
}
