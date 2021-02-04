package github.thesivlerecho.zeropoint.mixin;

import net.minecraft.client.gl.ShaderEffect;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(GameRenderer.class)
public abstract class GameRendererMixin
{
	@Shadow
	private ShaderEffect shader;

	@Shadow
	protected abstract void loadShader(Identifier location);


}