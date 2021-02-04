package github.thesivlerecho.zeropoint;

import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.mojang.authlib.GameProfile;
import github.thesivlerecho.zeropoint.config.Config;
import github.thesivlerecho.zeropoint.config.Settings;
import github.thesivlerecho.zeropoint.cosmetic.User;
import github.thesivlerecho.zeropoint.gui.DrawingHelper;
import github.thesivlerecho.zeropoint.gui.font.FontLoader;
import github.thesivlerecho.zeropoint.gui.font.GlyphPageFontRenderer;
import github.thesivlerecho.zeropoint.gui.overlay.watermark.WatermarkType;
import github.thesivlerecho.zeropoint.gui.screen.MenuScreen;
import github.thesivlerecho.zeropoint.mod.GreenerGrass;
import github.thesivlerecho.zeropoint.mod.ModPerspective;
import github.thesivlerecho.zeropoint.mod.ToolEquip;
import github.thesivlerecho.zeropoint.mod.sound.Sounds;
import github.thesivlerecho.zeropoint.module.Module;
import github.thesivlerecho.zeropoint.module.ModuleManager;
import github.thesivlerecho.zeropoint.registration.KeyBinds;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.event.client.ClientSpriteRegistryCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.network.OtherClientPlayerEntity;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.BufferUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
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


	public static final String MOD_ID = "zero-point";
	public static final Config CONFIG = new Config(new File(MinecraftClient.getInstance().runDirectory, "Config" + File.separator + "Zero-point.json"));
	public static final Logger LOGGER = LogManager.getLogger("Zero Point");
	public static final WatermarkType WATERMARK_TYPE = WatermarkType.TOP;
	public static final DrawingHelper DRAWING_HELPER = new DrawingHelper();
	public static FloatBuffer cosmicUVs = BufferUtils.createFloatBuffer(4 * 10);
	public static Sprite[] sprites = new Sprite[10];


	public static Map<UUID, User> PLAYER_COSMETICS = new HashMap<>();
	public static GlyphPageFontRenderer RENDERER;
	public static FontLoader FONT;

	private static void onTick(MinecraftClient minecraftClient)
	{
		ClientPlayerEntity player = minecraftClient.player;
		ClientWorld world = minecraftClient.world;

		if (player == null || world == null)
			return;

		ModuleManager.ACTIVE_MODULES.forEach(Module::onTick);
		ModuleManager.ACTIVE_MODULES.forEach(Module::keyPressed);
		if (KeyBinds.MENU.wasPressed())
		{
			MinecraftClient.getInstance().openScreen(new MenuScreen());
		}


		if (KeyBinds.RELOAD.wasPressed())
		{
			String name = "MrMumbo";
			OtherClientPlayerEntity a = new OtherClientPlayerEntity(world, /*new GameProfile(UUID.fromString(getUuid(name)), name)*/new GameProfile(UUID.fromString("5bf66200-9748-497c-826a-ec8cfb494720"), name));
			a.copyPositionAndRotation(player);
			a.headYaw = player.headYaw;
			world.addEntity(-100, a);
		}

		ModPerspective.tickPerspective();
	}

	private static void accept(Map<UUID, User> userMap)
	{
		if (userMap != null)
			PLAYER_COSMETICS = userMap;
		else PLAYER_COSMETICS = Collections.emptyMap();
	}

	private static void registerSprites(SpriteAtlasTexture spriteAtlasTexture, ClientSpriteRegistryCallback.Registry registry)
	{
		for (int i = 0; i < ZeroPointClient.sprites.length; i++)
			registry.register(new Identifier(ZeroPointClient.MOD_ID, "cosmic/cosmic_" + i));
	}

	@Override
	public void onInitializeClient()
	{
		Settings.create();
		KeyBinds.registerKeys();
		GreenerGrass.registerGreenerColors();
		CompletableFuture.supplyAsync(this::getUserInfo).thenAcceptAsync(ZeroPointClient::accept, MinecraftClient.getInstance());

		ClientSpriteRegistryCallback.event(SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE).register(ZeroPointClient::registerSprites);
		Sounds.SOUNDS_MAP.forEach((soundCategory, soundEvent) -> Registry.register(Registry.SOUND_EVENT, soundEvent.getId(), soundEvent));
		ClientTickEvents.START_CLIENT_TICK.register(ZeroPointClient::onTick);
		ClientTickEvents.END_CLIENT_TICK.register(minecraftClient -> ToolEquip.update());
	}

	private Map<UUID, User> getUserInfo()
	{
		String url = "https://raw.githubusercontent.com/TheSilverEcho/ZeroPointClient/master/contributors.json";
		try (InputStreamReader reader = new InputStreamReader(new URL(url).openStream()))
		{
			return new GsonBuilder().create().fromJson(reader, new TypeToken<Map<UUID, User>>()
			{
			}.getType());
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		return null;
	}


}
