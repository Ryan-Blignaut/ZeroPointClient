package github.thesivlerecho.zeropoint;

import com.google.common.collect.Maps;
import github.thesivlerecho.zeropoint.config.Config;
import github.thesivlerecho.zeropoint.config.Settings;
import github.thesivlerecho.zeropoint.gui.DrawingHelper;
import github.thesivlerecho.zeropoint.gui.overlay.keystrokes.KeystrokesRenderer;
import github.thesivlerecho.zeropoint.gui.overlay.watermark.WatermarkType;
import github.thesivlerecho.zeropoint.gui.screen.PositioningScreen;
import github.thesivlerecho.zeropoint.mod.GreenerGrass;
import github.thesivlerecho.zeropoint.mod.ModPerspective;
import github.thesivlerecho.zeropoint.mod.ToolEquip;
import github.thesivlerecho.zeropoint.mod.sound.Sounds;
import github.thesivlerecho.zeropoint.module.Module;
import github.thesivlerecho.zeropoint.module.ModuleManager;
import github.thesivlerecho.zeropoint.registration.KeyBinds;
import github.thesivlerecho.zeropoint.render.shader.ShaderManager;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.item.Item;
import net.minecraft.tag.ItemTags;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.registry.Registry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.Map;

@Environment(EnvType.CLIENT)
public class ZeroPointClient implements ClientModInitializer
{


	public static final String MOD_ID = "zero-point";
	public static final Config CONFIG = new Config(new File(MinecraftClient.getInstance().runDirectory, "Config" + File.separator + "Zero-point.json"));
	public static final Logger LOGGER = LogManager.getLogger("Zero Point");
	public static final WatermarkType WATERMARK_TYPE = WatermarkType.TOP;
	public static final DrawingHelper DRAWING_HELPER = new DrawingHelper();
	//	public static Map<UUID, User> PLAYER_COSMETICS = new HashMap<>();
//	public static GlyphPageFontRenderer renderer;

	private static void onTick(MinecraftClient minecraftClient)
	{
		ClientPlayerEntity player = minecraftClient.player;
		ClientWorld world = minecraftClient.world;

		if (player == null || world == null)
			return;
		KeystrokesRenderer.getInstance().updateKeystrokes();
		ModuleManager.ACTIVE_MODULES.forEach(Module::onTick);
		ModuleManager.ACTIVE_MODULES.forEach(Module::keyPressed);
		if (KeyBinds.MENU.wasPressed())
		{
//			MinecraftClient.getInstance().openScreen(new MenuScreen());
			MinecraftClient.getInstance().openScreen(new PositioningScreen());
		}


		ModPerspective.tickPerspective();
	}

//	private static void accept(Map<UUID, User> userMap)
//	{
//		if (userMap != null)
//			PLAYER_COSMETICS = userMap;
//		else PLAYER_COSMETICS = Collections.emptyMap();
//	}

	/*	private static void registerSprites(SpriteAtlasTexture spriteAtlasTexture, ClientSpriteRegistryCallback.Registry registry)
		{
			for (int i = 0; i < ZeroPointClient.sprites.length; i++)
				registry.register(new Identifier(ZeroPointClient.MOD_ID, "cosmic/cosmic_" + i));
		}*/

	@Override
	public void onInitializeClient()
	{
		Settings.create();
		ShaderManager.initShaders();
		KeyBinds.registerKeys();
		GreenerGrass.registerGreenerColors();
//		CompletableFuture.supplyAsync(this::getUserInfo).thenAcceptAsync(ZeroPointClient::accept, MinecraftClient.getInstance());
		Sounds.SOUNDS_MAP.forEach((soundCategory, soundEvent) -> Registry.register(Registry.SOUND_EVENT, soundEvent.getId(), soundEvent));
		ClientTickEvents.START_CLIENT_TICK.register(ZeroPointClient::onTick);
		ClientTickEvents.END_CLIENT_TICK.register(minecraftClient -> ToolEquip.update());
		Map<Item, Integer> hashMap = Maps.newLinkedHashMap();
		ItemTooltipCallback.EVENT.register((itemStack, tooltipContext, list) ->
		{
			final Item item = itemStack.getItem();
			if (ItemTags.getTagGroup().getTags().size() > 0)
			{
				if (hashMap.isEmpty())
					hashMap.putAll(AbstractFurnaceBlockEntity.createFuelTimeMap());

				int burnTime = hashMap.getOrDefault(item, -1);
				if (burnTime > -1)
					list.add(new TranslatableText("tooltip.zero-point.burnTime", burnTime).formatted(Formatting.DARK_GRAY));
			}
		});

	}


}
