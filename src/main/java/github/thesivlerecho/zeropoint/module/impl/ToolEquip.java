package github.thesivlerecho.zeropoint.module.impl;

import github.thesivlerecho.zeropoint.module.impl.sound.EquipSoundInstance;
import github.thesivlerecho.zeropoint.module.impl.sound.SoundCategory;
import github.thesivlerecho.zeropoint.module.impl.sound.Sounds;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.sound.SoundManager;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolItem;
import net.minecraft.sound.SoundEvent;

public class ToolEquip
{
	public static ItemStack old = ItemStack.EMPTY;

	public static void update()
	{
		ClientPlayerEntity player = MinecraftClient.getInstance().player;
		if (player == null)
			return;
		ItemStack newStack = player.getMainHandStack();
		if (old == newStack)
			return;

		old = newStack;
		playSound(player, newStack);

	}

	private static void playSound(ClientPlayerEntity player, ItemStack newStack)
	{
		SoundManager soundManager = MinecraftClient.getInstance().getSoundManager();
		EquipSoundInstance sound = new EquipSoundInstance(getSoundForItem(newStack), player);
		if (!soundManager.isPlaying(sound))
			soundManager.play(sound);
	}

	private static SoundEvent getSoundForItem(ItemStack itemStack)
	{
		if (itemStack.getItem() instanceof SwordItem)
			return Sounds.SOUNDS_MAP.get(SoundCategory.SWORD);
		if (itemStack.getItem() instanceof ToolItem)
			return Sounds.SOUNDS_MAP.get(SoundCategory.TOOL);
		return Sounds.SOUNDS_MAP.get(SoundCategory.UTIL);
	}


}
