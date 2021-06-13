package github.thesivlerecho.zeropoint.mod;

import com.google.common.collect.ListMultimap;
import com.google.common.collect.Multimaps;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.EnumMap;

public class Variants
{

	public static void setup()
	{
		ListMultimap<VariantTextureType, Identifier> textures = Multimaps.newListMultimap(new EnumMap<>(VariantTextureType.class), ArrayList::new);

//		EntityRendererRegistry.INSTANCE.register(EntityType.COW, (entityRenderDispatcher, context) -> new VariantCow(entityRenderDispatcher));
	}

	public enum VariantTextureType
	{
		COW, PIG, CHICKEN, LLAMA, RABBIT
	}

}
