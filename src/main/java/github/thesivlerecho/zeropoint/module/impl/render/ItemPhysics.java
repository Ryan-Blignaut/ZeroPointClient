package github.thesivlerecho.zeropoint.module.impl.render;

import github.thesivlerecho.zeropoint.config.Settings;
import github.thesivlerecho.zeropoint.event.TargetEvent;
import github.thesivlerecho.zeropoint.event.events.TickEvent;
import github.thesivlerecho.zeropoint.module.BaseModule;
import github.thesivlerecho.zeropoint.module.ModCategory;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec3f;

import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

public class ItemPhysics extends BaseModule
{
	private static final Random RANDOM = new Random();
	private static long tickDelta;
	private static final MinecraftClient MINECRAFT_CLIENT = MinecraftClient.getInstance();

	public ItemPhysics()
	{
		super("Item physics", new AtomicBoolean(Settings.PERSPECTIVE_HELD), ModCategory.RENDER);
		this.onEnable();
	}

	public static void render(ItemEntity itemEntity, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i1, ItemStack itemStack, ItemRenderer itemRenderer, int itemAmount)
	{
		matrixStack.push();
		final ItemStack stack = itemEntity.getStack();
		int j = stack.isEmpty() ? 187 : Item.getRawId(stack.getItem()) + stack.getDamage();
		RANDOM.setSeed(j);
		final BakedModel bakedModel = itemRenderer.getHeldItemModel(itemStack, itemEntity.world, null, itemEntity.getId());
		final boolean hasDepth = bakedModel.hasDepth();

		float rotateAmount = (System.nanoTime() - tickDelta) / 200_000_000F;
		if (MINECRAFT_CLIENT.isPaused())
			rotateAmount = 0;
		matrixStack.multiply(Vec3f.POSITIVE_Y.getRadialQuaternion((float) (Math.PI / 2f)));
		matrixStack.multiply(Vec3f.POSITIVE_Z.getRadialQuaternion(itemEntity.getYaw()));
		if (itemEntity.isSubmergedInWater())
			rotateAmount /= 2 * 10;

		if (itemEntity.getItemAge() != 0 && (hasDepth || MINECRAFT_CLIENT.getEntityRenderDispatcher().gameOptions != null))
		{
			if (hasDepth)
			{
				if (!itemEntity.isOnGround())
				{
					rotateAmount *= 2;
					itemEntity.setPitch(itemEntity.getPitch() + rotateAmount);
				}
			} else if (!Double.isNaN(itemEntity.getX()) && !Double.isNaN(itemEntity.getY()) && !Double.isNaN(itemEntity.getZ()) && itemEntity.world != null)
			{
				if (itemEntity.isOnGround())
				{
					itemEntity.setPitch(0);
					matrixStack.multiply(Vec3f.POSITIVE_Z.getDegreesQuaternion(90));
					matrixStack.multiply(Vec3f.POSITIVE_X.getDegreesQuaternion(90));
					matrixStack.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(90));


				} else
				{
					rotateAmount *= 2;
					itemEntity.setPitch(itemEntity.getPitch() + rotateAmount);
				}
			}

			if (hasDepth)
				matrixStack.translate(0, -0.2, -0.08);

			else
				matrixStack.translate(0, 0, -0.04);

			double height = 0.2;
			if (hasDepth)
				matrixStack.translate(0, height, 0);
			matrixStack.multiply(Vec3f.POSITIVE_Y.getRadialQuaternion(itemEntity.getPitch()));
			if (hasDepth)
				matrixStack.translate(0, -height, 0);
		}

		if (!hasDepth)
		{
			float f7 = -0.0F * (itemAmount - 1) * 0.5F;
			float f8 = -0.0F * (itemAmount - 1) * 0.5F;
			float f9 = -0.09375F * (itemAmount - 1) * 0.5F;
			matrixStack.translate(f7, f8, f9);
		}

		for (int k = 0; k < itemAmount; ++k)
		{
			matrixStack.push();
			if (k > 0)
			{
				if (hasDepth)
				{
					float f11 = (RANDOM.nextFloat() * 2.0F - 1.0F) * 0.15F;
					float f13 = (RANDOM.nextFloat() * 2.0F - 1.0F) * 0.15F;
					float f10 = (RANDOM.nextFloat() * 2.0F - 1.0F) * 0.15F;
					matrixStack.translate(f11, f13, f10);
				}
			}
			MINECRAFT_CLIENT.getItemRenderer().renderItem(stack, ModelTransformation.Mode.GROUND, false, matrixStack, vertexConsumerProvider, i1,
					OverlayTexture.DEFAULT_UV, bakedModel);
			matrixStack.pop();
			if (!hasDepth)
				matrixStack.translate(0.0, 0.0, 0.05375F);
		}

		matrixStack.pop();
	}
	public static void render1(ItemEntity itemEntity, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i1, ItemStack itemStack, ItemRenderer itemRenderer, int itemAmount)
	{
		matrixStack.push();
		final ItemStack stack = itemEntity.getStack();
		int rand = stack.isEmpty() ? 187 : Item.getRawId(stack.getItem()) + stack.getDamage();
		RANDOM.setSeed(rand);
		final BakedModel bakedModel = itemRenderer.getHeldItemModel(itemStack, itemEntity.world, null, itemEntity.getId());
		final boolean hasDepth = bakedModel.hasDepth();

		float rotateAmount = (System.nanoTime() - tickDelta) / 200_000_000F;
		if (MINECRAFT_CLIENT.isPaused())
			rotateAmount = 0;
		matrixStack.multiply(Vec3f.POSITIVE_Y.getRadialQuaternion((float) (Math.PI / 2f)));
		matrixStack.multiply(Vec3f.POSITIVE_Z.getRadialQuaternion(itemEntity.getYaw()));
		if (itemEntity.isSubmergedInWater())
			rotateAmount /= 2 * 10;

		if (itemEntity.getItemAge() != 0 && (hasDepth || MINECRAFT_CLIENT.getEntityRenderDispatcher().gameOptions != null))
		{
			if (hasDepth)
			{
				if (!itemEntity.isOnGround())
				{
					rotateAmount *= 2;
					itemEntity.setPitch(itemEntity.getPitch() + rotateAmount);
				}
			} else if (!Double.isNaN(itemEntity.getX()) && !Double.isNaN(itemEntity.getY()) && !Double.isNaN(itemEntity.getZ()) && itemEntity.world != null)
			{
				if (itemEntity.isOnGround())
				{
					itemEntity.setPitch(0);
					matrixStack.multiply(Vec3f.POSITIVE_Z.getDegreesQuaternion(90));
					matrixStack.multiply(Vec3f.POSITIVE_X.getDegreesQuaternion(90));
					matrixStack.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(90));


				} else
				{
					rotateAmount *= 2;
					itemEntity.setPitch(itemEntity.getPitch() + rotateAmount);
				}
			}

			if (hasDepth)
				matrixStack.translate(0, -0.2, -0.08);

			else
				matrixStack.translate(0, 0, -0.04);

			double height = 0.2;
			if (hasDepth)
				matrixStack.translate(0, height, 0);
			matrixStack.multiply(Vec3f.POSITIVE_Y.getRadialQuaternion(itemEntity.getPitch()));
			if (hasDepth)
				matrixStack.translate(0, -height, 0);
		}

		if (!hasDepth)
		{
			float f7 = -0.0F * (itemAmount - 1) * 0.5F;
			float f8 = -0.0F * (itemAmount - 1) * 0.5F;
			float f9 = -0.09375F * (itemAmount - 1) * 0.5F;
			matrixStack.translate(f7, f8, f9);
		}

		for (int k = 0; k < itemAmount; ++k)
		{
			matrixStack.push();
			if (k > 0)
			{
				if (hasDepth)
				{
					float f11 = (RANDOM.nextFloat() * 2.0F - 1.0F) * 0.15F;
					float f13 = (RANDOM.nextFloat() * 2.0F - 1.0F) * 0.15F;
					float f10 = (RANDOM.nextFloat() * 2.0F - 1.0F) * 0.15F;
					matrixStack.translate(f11, f13, f10);
				}
			}
			MINECRAFT_CLIENT.getItemRenderer().renderItem(stack, ModelTransformation.Mode.GROUND, false, matrixStack, vertexConsumerProvider, i1,
					OverlayTexture.DEFAULT_UV, bakedModel);
			matrixStack.pop();
			if (!hasDepth)
				matrixStack.translate(0.0, 0.0, 0.05375F);
		}

		matrixStack.pop();
	}
	@TargetEvent
	public void tick(TickEvent event)
	{
		if (this.isEnabled())
			tickDelta = System.nanoTime();
	}

}
