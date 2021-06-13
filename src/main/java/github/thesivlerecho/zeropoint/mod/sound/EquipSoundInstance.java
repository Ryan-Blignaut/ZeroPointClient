package github.thesivlerecho.zeropoint.mod.sound;

import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.sound.MovingSoundInstance;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;

public class EquipSoundInstance extends MovingSoundInstance
{
	ClientPlayerEntity player;

	public EquipSoundInstance(SoundEvent soundEvent, ClientPlayerEntity player)
	{
		super(soundEvent, SoundCategory.PLAYERS);
		this.player = player;
	}

	@Override
	public void tick()
	{
		if (!this.player.isRemoved())
		{
			this.x = (float) this.player.getX();
			this.y = (float) this.player.getY();
			this.z = (float) this.player.getZ();
		} else
			this.setDone();
	}
}
