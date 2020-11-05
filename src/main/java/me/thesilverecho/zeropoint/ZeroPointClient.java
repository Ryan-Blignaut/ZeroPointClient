package me.thesilverecho.zeropoint;

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
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.BufferUtils;

import java.io.File;
import java.nio.FloatBuffer;
import java.util.HashMap;

@Environment(EnvType.CLIENT)
public class ZeroPointClient implements ClientModInitializer
{
	public final static HashMap<BlockPos, BlockState> BLOCK_POS_HASH = new HashMap<>();

	public static final Logger LOGGER = LogManager.getLogger("Zero Point");
	public static final DrawingHelper DRAWING_HELPER = new DrawingHelper();
	public static final WatermarkType WATERMARK_TYPE = WatermarkType.TOP;
	public static final Config CONFIG = new Config(new File(MinecraftClient.getInstance().runDirectory, "Config" + File.separator + "Zero-point.json"));
	public static FloatBuffer cosmicUVs = BufferUtils.createFloatBuffer(4 * 10);
	public static Sprite[] sprites = new Sprite[10];

//	private Gson gson = new GsonBuilder().setPrettyPrinting().create();
/*	static final Type COSMETIC_SELECT_TYPE = new TypeToken<Map<UUI D, PlayerCosmeticData>>()
	{
	}.getType();*/

	@Override
	public void onInitializeClient()
	{
		KeyBinds.registerKeys();
	/*	CompletableFuture.supplyAsync(() ->
		{
			try (Reader reader = new InputStreamReader(new URL("a").openStream()))
			{
				return gson.fromJson(reader, )
			} catch (IOException e)
			{
				e.printStackTrace();
			}

			return null;
		}).thenApply(u ->)*/

		ClientSpriteRegistryCallback.event(SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE).

				register((spriteAtlasTexture, registry) ->
				{
					for (int i = 0; i < ZeroPointClient.sprites.length; i++)
					{
						Identifier t = new Identifier("zero-point", "cosmic/cosmic_" + i);
						registry.register(t);
					}
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
}
