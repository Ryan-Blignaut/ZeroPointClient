package github.thesivlerecho.zeropoint.mixin;

import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.mojang.blaze3d.systems.RenderSystem;
import github.thesivlerecho.zeropoint.ZeroPointClient;
import github.thesivlerecho.zeropoint.config.Settings;
import github.thesivlerecho.zeropoint.event.EventManager;
import github.thesivlerecho.zeropoint.event.events.RenderTooltipEvent;
import github.thesivlerecho.zeropoint.gui.old.GuiHelper;
import github.thesivlerecho.zeropoint.render.DrawingUtil;
import github.thesivlerecho.zeropoint.render.widget.PositioningComponent;
import net.minecraft.block.ShulkerBoxBlock;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.tooltip.TooltipComponent;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.text.*;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.Language;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.awt.*;
import java.util.List;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Mixin(Screen.class)
public abstract class ScreenMixin
{
	private static final int TOOLTIP_SPACE = 12;
	private static final int H_BORDER = 5;
	private static final int V_BORDER = 4;
	private static final int LINE_HEIGHT = 10;
	private static final int TITLE_GAP = 2;
	private final int background = Settings.TOOLTIP_BACKGROUND_COLOUR;
	private int borderTop = Settings.TOOLTIP_BORDER_TOP_COLOUR;
	private int borderBottom = Settings.TOOLTIP_BORDER_BOTTOM_COLOUR;

	@Shadow protected TextRenderer textRenderer;
	@Shadow public int width;
	@Shadow public int height;
	@Shadow protected ItemRenderer itemRenderer;


	@Inject(method = "renderTooltipFromComponents", at = @At(value = "HEAD"), cancellable = true)
	private void renderCustomTooltip123(MatrixStack matrices, List<TooltipComponent> components, int x, int y, CallbackInfo ci)
	{
		RenderTooltipEvent renderTooltipEvent = new RenderTooltipEvent(matrices, components, x, y, textRenderer, itemRenderer, width, height, ci);
		EventManager.call(renderTooltipEvent);
	}


	private List<ItemStack> stacks = new ArrayList<>();
	private ItemStack stack;

	private static ItemStack cachedTooltipStack = ItemStack.EMPTY;

	private static void setItemStack(ItemStack stack)
	{
		cachedTooltipStack = stack;
	}


	private List<? extends Text> lines = new ArrayList<>();


	@Inject(method = "renderTooltip(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/item/ItemStack;II)V", at = @At(value = "HEAD"), cancellable = true)
	private void renderCustomTooltip(MatrixStack matrices, ItemStack stack, int x, int y, CallbackInfo ci)
	{
//		tooltips: food(hunger,saturation), armour(base and hardness),tools(speed,damage),fuel(burn time)
		this.stack = stack;
		ScreenMixin.setItemStack(stack);
		if (Screen.hasShiftDown())
		{
			if (stack.getItem() instanceof BlockItem)
				if (((BlockItem) stack.getItem()).getBlock() instanceof ShulkerBoxBlock)
				{
					final DyeColor color = ((ShulkerBoxBlock) ((BlockItem) stack.getItem()).getBlock()).getColor();
					if (this.stack != stack)
					{
						stacks = compute(stack);
						this.stack = stack;
					}
					if (stacks.isEmpty())
						return;

					int tooltipX = x + TOOLTIP_SPACE;
					int tooltipY = y - TOOLTIP_SPACE;
					int tooltipWidth = Math.min(stacks.size(), 9) * 18;
					int tooltipHeight = (int) (Math.ceil(stacks.size() / 9f) * 18);

					if (tooltipX + tooltipWidth > this.width)
						tooltipX -= 28 + tooltipWidth;

					if (tooltipY + tooltipHeight + 6 > this.height)
						tooltipY = this.height - tooltipHeight - 6;


					RenderSystem.disableDepthTest();
					RenderSystem.enableBlend();
					RenderSystem.defaultBlendFunc();
					matrices.push();
					GuiHelper.drawRoundedRect(matrices, tooltipX - H_BORDER, tooltipY - V_BORDER, tooltipX + tooltipWidth + H_BORDER, tooltipY + tooltipHeight + V_BORDER, 3, background);
					final int i = color != null ? new Color(color.getColorComponents()[0], color.getColorComponents()[1], color.getColorComponents()[2], 1).getRGB() | 240 << 24 : borderTop;
					GuiHelper.drawGradientBoardedRect(matrices, tooltipX - H_BORDER, tooltipY - V_BORDER, tooltipX + tooltipWidth + H_BORDER, tooltipY + tooltipHeight + V_BORDER, 3, i, i);

					RenderSystem.enableDepthTest();
					RenderSystem.disableBlend();


					matrices.push();
					matrices.translate(0, 0, 400);
					int dx = 0, dy = 0;
					for (ItemStack itemStack : stacks)
					{
						itemRenderer.renderInGuiWithOverrides(itemStack, tooltipX + dx * 18, tooltipY + dy * 18);
						itemRenderer.renderGuiItemOverlay(textRenderer, itemStack, tooltipX + dx * 18, tooltipY + dy * 18);
						dx++;
						if (dx == 9)
						{
							dx = 0;
							dy++;
						}

					}
					matrices.pop();
					ci.cancel();
				}
		}

	}


	@Inject(method = "renderTooltip(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/text/Text;II)V", at = @At(value = "HEAD"))
	private void renderCustomTooltip(MatrixStack matrices, Text text, int x, int y, CallbackInfo ci)
	{
		this.lines = Collections.singletonList(text);
		final TextColor color = text.getStyle().getColor();
		if (color != null && !color.getName().equals(Formatting.WHITE.getName()))
			this.borderBottom = this.borderTop = color.getRgb() | 240 << 24;
		else
		{
			this.borderTop = Settings.TOOLTIP_BORDER_TOP_COLOUR;
			this.borderBottom = Settings.TOOLTIP_BORDER_BOTTOM_COLOUR;
		}
	}

	@Inject(method = "renderTooltip(Lnet/minecraft/client/util/math/MatrixStack;Ljava/util/List;II)V", at = @At(value = "HEAD"))
	private void renderCustomTooltip1(MatrixStack matrices, List<Text> lines, int x, int y, CallbackInfo ci)
	{
		this.lines = lines;


		if (!lines.isEmpty())
		{
			final TextColor color = lines.get(0).getStyle().getColor();
			if (color != null && !color.getName().equals(Formatting.WHITE.getName()))
				this.borderBottom = this.borderTop = color.getRgb() | 240 << 24;
			else
			{
				this.borderTop = Settings.TOOLTIP_BORDER_TOP_COLOUR;
				this.borderBottom = Settings.TOOLTIP_BORDER_BOTTOM_COLOUR;
			}
		}

	}


	private List<ItemStack> compute(ItemStack stack)
	{
		final ArrayList<ItemStack> stacks = new ArrayList<>();
		HashMap<Item, Integer> map = new HashMap<>();

		if (!stack.hasTag() || stack.getTag() == null || !stack.getTag().contains("BlockEntityTag") || !stack.getTag().getCompound("BlockEntityTag").contains("Items", 9))
			return stacks;

		final NbtList list = stack.getTag().getCompound("BlockEntityTag").getList("Items", 10);
		for (NbtElement tag : list)
		{
			if (tag instanceof NbtCompound)
			{
				final NbtCompound compoundTag = (NbtCompound) tag;

				final ItemStack itemStack = ItemStack.fromNbt(compoundTag);
				map.putIfAbsent(itemStack.getItem(), 0);
				map.put(itemStack.getItem(), map.get(itemStack.getItem()) + itemStack.getCount());
			}
		}

		map.forEach((item, integer) -> stacks.add(new ItemStack(item, integer)));
		return stacks;
	}


	@ModifyConstant(method = "renderBackground(Lnet/minecraft/client/util/math/MatrixStack;I)V", constant = @Constant(intValue = -1072689136))
	private int startColor(int c)
	{
		return new Color(0, 50, 80, 100).getRGB();
	}

	@ModifyConstant(method = "renderBackground(Lnet/minecraft/client/util/math/MatrixStack;I)V", constant = @Constant(intValue = -804253680))
	private int endColor(int c)
	{
		return new Color(0, 50, 80, 100).getRGB();
	}

	@Inject(method = "renderOrderedTooltip", at = @At(value = "HEAD"), cancellable = true)
	private void renderCustomTooltip(MatrixStack matrixStack, List<? extends OrderedText> lines, int x, int y, CallbackInfo ci)
	{

		ci.cancel();
		float tooltipX = x + TOOLTIP_SPACE;
		float tooltipY = y - TOOLTIP_SPACE;
		int tooltipWidth = 0;
		int tooltipHeight = V_BORDER * 2;


		ArrayList<String> arrayList = new ArrayList<>();
		arrayList.add("attribute.name.generic.attack_damage");
		arrayList.add("attribute.name.generic.attack_speed");
		HashMap<String, String> hashMap = new HashMap<>();

		if (!this.lines.isEmpty())
		{
			for (String s : arrayList)
			{
				int index = -1;
				for (int i = 0; i < this.lines.size(); i++)
				{
					final StringVisitable orderedText = this.lines.get(i);
					if (orderedText.getString().contains(new TranslatableText(s).getString()))
					{
						index = i;
						hashMap.put(s, orderedText.getString().substring(0, orderedText.getString().indexOf(" ")));
						break;
					}
				}
				if (index < 0)
					continue;
				this.lines.remove(index - 1); // Remove blank space
				this.lines.remove(index - 1); // Remove actual line
				tooltipHeight += 6;

			}
			for (Text line : this.lines)
				tooltipWidth = Math.max(tooltipWidth, textRenderer.getWidth(line));
			if (this.lines.size() > 1)
				tooltipHeight += 2 + (this.lines.size() - 1) * LINE_HEIGHT;
		} else
		{
//			vanilla draw where we don't affect the lines
			for (OrderedText line : lines)
				tooltipWidth = Math.max(tooltipWidth, textRenderer.getWidth(line));
			if (lines.size() > 1)
				tooltipHeight += 2 + (lines.size() - 1) * LINE_HEIGHT;
		}


		if (tooltipX + tooltipWidth > this.width)
			tooltipX -= 28 + tooltipWidth;

		if (tooltipY + tooltipHeight + 6 > this.height)
			tooltipY = this.height - tooltipHeight - 6;

		drawBackground(matrixStack, tooltipX, tooltipY, tooltipWidth, tooltipHeight);
		float finalTooltipY = drawText(lines, matrixStack, tooltipX, tooltipY);
//		float finalTooltipY = tooltipY;
		float finalTooltipX = tooltipX;
		AtomicInteger integer = new AtomicInteger();
		ArrayList<String> test = Lists.newArrayList("fa233e1c-4180-4865-b01b-bcce9785aca3", "cb3f55d3-645c-4f38-a497-9c13a33db5cf");
		if (stack != null)
		{
			Multimap<EntityAttribute, EntityAttributeModifier> multi = stack.getAttributeModifiers(EquipmentSlot.MAINHAND);
			for (Map.Entry<EntityAttribute, EntityAttributeModifier> entry : multi.entries())
			{
				System.out.println(entry.getValue().getId());
				for (String s : test)
				{
					if (entry.getValue().getId().equals(UUID.fromString(s)))
					{

						RenderSystem.setShaderTexture(0, new Identifier(ZeroPointClient.MOD_ID, "textures/i.png"));
						DrawableHelper.drawTexture(matrixStack, ((int) finalTooltipX + integer.get()), ((int) finalTooltipY), 0, 0, 8, 8, 8, 8);
						MinecraftClient.getInstance().textRenderer.draw(matrixStack, String.format("%.2f", entry.getValue().getValue() + 4f), finalTooltipX + integer.get() + 9, finalTooltipY, -1);
						final int i = MinecraftClient.getInstance().textRenderer.getWidth(String.format("%.2f", entry.getValue().getValue() + 4f)) + 9;
						integer.set(i);
					}

				}

//				if (entry.getValue().getId().equals(UUID.fromString("fa233e1c-4180-4865-b01b-bcce9785aca3")) || entry.getValue().getId().equals(UUID.fromString("cb3f55d3-645c-4f38-a497-9c13a33db5cf")))
//				{
//					MinecraftClient.getInstance().getTextureManager().bindTexture(new Identifier(ZeroPointClient.MOD_ID, "textures/i.png"));
//					DrawableHelper.drawTexture(matrixStack, ((int) finalTooltipX + integer.get()), ((int) finalTooltipY), 0, 0, 8, 8, 8, 8);
//					MinecraftClient.getInstance().textRenderer.draw(matrixStack, String.format("%.2f", entry.getValue().getValue() + 4f), finalTooltipX + 9, finalTooltipY, -1);
//					final int i = MinecraftClient.getInstance().textRenderer.getWidth(String.format("%.2f", entry.getValue().getValue() + 4f)) + 9;
//					integer.addAndGet(i);
//				}

			}
		}


		matrixStack.pop();
	}


	private void drawBackground(MatrixStack matrixStack, float tooltipX, float tooltipY, int tooltipWidth, int tooltipHeight)
	{
		RenderSystem.disableDepthTest();
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		matrixStack.push();
		final PositioningComponent positioningComponent = new PositioningComponent(tooltipX - H_BORDER, tooltipY - V_BORDER, tooltipWidth + H_BORDER, tooltipHeight + V_BORDER);
//		positioningComponent.setColour(background);
		DrawingUtil.drawRectWithShader(positioningComponent, 3, matrixStack);
//		GuiHelper.drawRoundedRect(matrixStack, tooltipX - H_BORDER, tooltipY - V_BORDER, tooltipX + tooltipWidth + H_BORDER, tooltipY + tooltipHeight + V_BORDER, 3, background);
//		GuiHelper.drawGradientBoardedRect(matrixStack,
//				tooltipX - H_BORDER, tooltipY - V_BORDER, tooltipX + tooltipWidth + H_BORDER,
//				tooltipY + tooltipHeight + V_BORDER, 3,
//				borderTop,
//				borderBottom);

		RenderSystem.enableDepthTest();
		RenderSystem.disableBlend();
	}

	private void drawLine(MatrixStack matrixStack, VertexConsumerProvider.Immediate immediate, StringVisitable line, float tooltipX, float tooltipY)
	{
		this.textRenderer.draw(Language.getInstance().reorder(line), tooltipX, tooltipY, -1, true, matrixStack.peek().getModel(), immediate, false, 0, 15728880);
	}

	private void drawLine(MatrixStack matrixStack, VertexConsumerProvider.Immediate immediate, OrderedText line, float tooltipX, float tooltipY)
	{
		this.textRenderer.draw(line, tooltipX, tooltipY, -1, true, matrixStack.peek().getModel(), immediate, false, 0, 15728880);
	}


	private float drawText(List<? extends OrderedText> texts, MatrixStack matrixStack, float tooltipX, float tooltipY)
	{
		VertexConsumerProvider.Immediate immediate = VertexConsumerProvider.immediate(Tessellator.getInstance().getBuffer());

		matrixStack.translate(0.0D, 0.0D, 400.0D);

		for (int i = 0; i < texts.size(); ++i)
		{
			if (!lines.isEmpty())
			{
				final StringVisitable line = this.lines.get(i);
				if (line != null)
					drawLine(matrixStack, immediate, line, tooltipX, tooltipY);


			} else
			{
//				safety for instances where lines would be empty eg. chat advancement screen
				final OrderedText line = texts.get(i);
				if (line != null)
					drawLine(matrixStack, immediate, line, tooltipX, tooltipY);

			}
			if (i == 0)
				tooltipY += 2;
			tooltipY += 10;


		}
		immediate.draw();
		return tooltipY;

	}


}
