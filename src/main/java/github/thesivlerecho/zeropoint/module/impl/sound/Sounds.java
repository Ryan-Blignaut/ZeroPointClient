package github.thesivlerecho.zeropoint.module.impl.sound;

import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

import java.util.HashMap;

public class Sounds
{
//	public static final Identifier MY_SOUND_ID = new Identifier("zero-point:sword_draw");
//	public static SoundEvent MY_SOUND_EVENT = new SoundEvent(MY_SOUND_ID);


	public static HashMap<SoundCategory, SoundEvent> SOUNDS_MAP = new HashMap<>();

	static
	{
		SOUNDS_MAP.put(SoundCategory.SWORD, (new SoundEvent(new Identifier("zero-point:items/sword_draw"))));
		SOUNDS_MAP.put(SoundCategory.UTIL, (new SoundEvent(new Identifier("zero-point:items/utility_draw"))));
		SOUNDS_MAP.put(SoundCategory.TOOL, (new SoundEvent(new Identifier("zero-point:items/tool_draw"))));
	}


}
