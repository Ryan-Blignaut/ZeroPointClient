package github.thesivlerecho.zeropoint.mixin;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(ItemStack.class)
public class ItemStackMixin
{
	private static ItemStack stack;

	@Inject(method = "getTooltip", at = @At(value = "HEAD"), cancellable = true)
	private void renderCustomTooltip(PlayerEntity player, TooltipContext context, CallbackInfoReturnable<List<Text>> cir)
	{

	}


}
