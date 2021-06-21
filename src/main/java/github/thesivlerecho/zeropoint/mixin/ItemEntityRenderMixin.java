package github.thesivlerecho.zeropoint.mixin;

import github.thesivlerecho.zeropoint.module.impl.render.ItemPhysics;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.ItemEntityRenderer;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemEntityRenderer.class)
public abstract class ItemEntityRenderMixin
{
	@Shadow @Final private ItemRenderer itemRenderer;

	@Shadow
	protected abstract int getRenderedAmount(ItemStack stack);

	@Inject(method = "render", at = @At(value = "HEAD"), cancellable = true)
	private void renderItemWithPhysics(ItemEntity itemEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, CallbackInfo ci)
	{
		ci.cancel();

		final ItemStack itemStack = itemEntity.getStack();
		final ItemRenderer itemRenderer = this.itemRenderer;
		final int renderedAmount = this.getRenderedAmount(itemStack);

		ItemPhysics.render(itemEntity, matrixStack, vertexConsumerProvider, i,itemStack,itemRenderer,renderedAmount);
	}
}
