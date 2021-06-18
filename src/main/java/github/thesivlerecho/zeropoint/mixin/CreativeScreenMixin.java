package github.thesivlerecho.zeropoint.mixin;

import github.thesivlerecho.zeropoint.event.events.RenderTooltipEvent;
import net.minecraft.client.gui.screen.ingame.AbstractInventoryScreen;
import net.minecraft.client.gui.screen.ingame.CreativeInventoryScreen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(CreativeInventoryScreen.class)
public abstract class CreativeScreenMixin extends AbstractInventoryScreen<CreativeInventoryScreen.CreativeScreenHandler>
{

	public CreativeScreenMixin(CreativeInventoryScreen.CreativeScreenHandler screenHandler, PlayerInventory playerInventory, Text text)
	{
		super(screenHandler, playerInventory, text);
	}

	@Inject(method = "renderTooltip", at = @At("HEAD"))
	private void renderTooltip(MatrixStack matrices, ItemStack stack, int x, int y, CallbackInfo ci)
	{
		RenderTooltipEvent.setItemStack(stack);
	}


}
