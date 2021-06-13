package github.thesivlerecho.zeropoint.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Quaternion;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemRenderer.class)
public abstract class ItemRendererMixin
{
	private static ItemStack stack;

	@Inject(method = "getDirectItemGlintConsumer", at = @At(value = "HEAD"), cancellable = true)
	private static void render(VertexConsumerProvider vertexConsumerProvider, RenderLayer layer, boolean bl, boolean glint, CallbackInfoReturnable<VertexConsumer> cir)
	{
//		if (stack.getItem() instanceof PotionItem)
//		{
//			cir.setReturnValue(VertexConsumers.dual(vertexConsumerProvider.getBuffer(ModRenderLayer.POT_OVERLAY), vertexConsumerProvider.getBuffer(layer)));
//		}

//		if (stack.getItem() instanceof SwordItem)
//		{
//			cir.setReturnValue(VertexConsumers.dual(vertexConsumerProvider.getBuffer(ModRenderLayer.EXTREME_OVER), vertexConsumerProvider.getBuffer(layer)));
//		}
	}

	@Inject(method = "renderItem(Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/render/model/json/ModelTransformation$Mode;ZLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;IILnet/minecraft/client/render/model/BakedModel;)V", at = @At(value = "HEAD"), cancellable = true)
	private void render(ItemStack stack, ModelTransformation.Mode renderMode, boolean leftHanded, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay, BakedModel model, CallbackInfo ci)
	{
		ItemRendererMixin.stack = stack;
//		CosmicShader.inventoryRender = renderMode == ModelTransformation.Mode.GUI;

		/*if (stack.getItem() instanceof PickaxeItem)
		{
			BakedModel bakedModel = new BuiltinBakedModel(ModelTransformation.NONE, null, ZeroPointClient.sprites[0], false);
			renderBakedItemModel(bakedModel, stack, light, overlay, matrices, vertexConsumers.getBuffer(RenderLayers.getItemLayer(stack, false)));
		}*/
	}

	@Inject(method = "renderItem(Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/render/model/json/ModelTransformation$Mode;ZLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;IILnet/minecraft/client/render/model/BakedModel;)V", at = @At(value = "HEAD"), cancellable = true)
	private void customRender(ItemStack stack, ModelTransformation.Mode renderMode, boolean leftHanded, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay, BakedModel model, CallbackInfo ci)
	{
		if (renderMode == ModelTransformation.Mode.FIRST_PERSON_RIGHT_HAND)
			if (MinecraftClient.getInstance().options.keyUse.isPressed())
			{
				float swingProgress = MinecraftClient.getInstance().player.getHandSwingProgress(MinecraftClient.getInstance().getTickDelta());
//				matrices.translate(0.2f, 0.1f, -0.4f);
//				matrices.multiply(new Quaternion(-8, 0, 0, false));
//				matrices.translate(-1.0f, 0.4f, 0);
				matrices.multiply(new Quaternion(swingProgress * 360, 0, 0, true));
				matrices.multiply(new Quaternion(-30, 0, 45, true));

				matrices.translate(1.0f, -0.4f, 0);
				matrices.translate(-0.15f, 0.15f, -0.2f);

//				matrices.translate(-0.15f, 0.2f, -0.2f);
				matrices.translate(0, 0, -2);
				if (-swingProgress > -0.5)
				{
					matrices.translate(0, -swingProgress, 0);
				} else
				{
					matrices.translate(0, swingProgress - 1f, 0);
				}
			}


	}


}

