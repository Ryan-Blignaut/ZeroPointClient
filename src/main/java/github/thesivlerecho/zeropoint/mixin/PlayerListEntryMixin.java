package github.thesivlerecho.zeropoint.mixin;

import com.mojang.authlib.GameProfile;
import net.minecraft.client.network.PlayerListEntry;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerListEntry.class)
public abstract class PlayerListEntryMixin
{

	@Shadow
	@Final
	private GameProfile profile;

	@Inject(method = "getModel", at = @At("HEAD"))
	private void render(CallbackInfoReturnable<String> cir)
	{
		/*if (Config2.nikHider.isOn() && Config2.hideSkins.isOn())
		{
			if (profile.getId().equals(MinecraftClient.getInstance().player.getUuid()))
			{
				if (Config2.useRealSkinForSelf.isOn() && MinecraftClient.getInstance().skinManager.a() != null)
				{
					return MinecraftClient.getInstance().getSkinProvider().toString();
				}
			} else if (eb2.hideOtherSkins.isTrue() && eb2.usePlayerSkinForAll.isTrue() && eb.skinManager.a() != null)
			{
				return eb.skinManager.b();
			}
		}
		return null;*/

	}

}
