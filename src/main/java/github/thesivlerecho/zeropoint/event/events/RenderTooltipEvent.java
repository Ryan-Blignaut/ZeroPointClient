package github.thesivlerecho.zeropoint.event.events;

import github.thesivlerecho.zeropoint.event.BaseEvent;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.tooltip.TooltipComponent;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

public record RenderTooltipEvent(MatrixStack matrices, List<TooltipComponent> components, int x, int y,
                                 TextRenderer textRenderer, ItemRenderer itemRenderer, int width, int height,
                                 CallbackInfo callbackInfo) implements BaseEvent
{
	private static ItemStack itemStack;

	public ItemStack getItemStack()
	{
		return itemStack;
	}

	public static void setItemStack(ItemStack itemStack)
	{
		RenderTooltipEvent.itemStack = itemStack;
	}
}
