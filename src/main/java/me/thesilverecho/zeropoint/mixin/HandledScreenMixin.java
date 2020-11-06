package me.thesilverecho.zeropoint.mixin;

import me.thesilverecho.zeropoint.gui.button.ClickableButton;
import me.thesilverecho.zeropoint.gui.overlay.watermark.Watermark;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.CreativeInventoryScreen;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import net.minecraft.client.gui.screen.ingame.ScreenHandlerProvider;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

@Mixin(HandledScreen.class) public abstract class HandledScreenMixin<T extends ScreenHandler> extends Screen implements ScreenHandlerProvider<T>
{
	private ArrayList<ClickableButton> menuButtons;
	private final HandledScreen<?> screen = (HandledScreen<?>) (Object) this;

	@Shadow protected int x;

	@Shadow protected int y;

	@Shadow protected int backgroundWidth;

	@Shadow protected int backgroundHeight;

	protected HandledScreenMixin(Text title)
	{
		super(title);
	}

	@Inject(method = "init", at = @At("RETURN")) private void initNewButtons(CallbackInfo ci)
	{
		menuButtons = new ArrayList<>();

		if (screen instanceof InventoryScreen)
		{
			AtomicInteger x = new AtomicInteger();
			AtomicInteger y = new AtomicInteger();
			ClientWorld world = MinecraftClient.getInstance().world;
			ClientPlayerEntity player = MinecraftClient.getInstance().player;
				/*	MinecraftClient.getInstance().interactionManager.interactBlock(player, world, Hand.MAIN_HAND,
										new BlockHitResult(new Vec3d(pos.getX(), pos.getY(), pos.getZ()), Direction.EAST, pos, false));*/
			//			ZeroPointClient.BLOCK_POS_HASH.re
//			ZeroPointClient.BLOCK_POS_HASH.forEach((pos, blockState) -> menuButtons
//					.add(new ClickableButton(x.addAndGet(20), y.addAndGet(20), 10, 10, blockState.getBlock().getName().asString(),
//							button -> MinecraftClient.getInstance().interactionManager.interactBlock(player, world, Hand.MAIN_HAND,
//									new BlockHitResult(new Vec3d(pos.getX(), pos.getY(), pos.getZ()), Direction.EAST, pos, false)))));
			/*ZeroPointClient.BLOCK_POS.removeIf(blockPos -> player.getBlockPos().getSquaredDistance(blockPos) < 3);
			ZeroPointClient.BLOCK_POS.forEach(
					pos -> menuButtons.add(new ClickableButton(x.addAndGet(20), y.addAndGet(20), 10, 10, world.getBlockState(pos).getBlock().getName()
					                                                                                          .asString(),
							button -> MinecraftClient.getInstance().interactionManager.interactBlock(player, world, Hand.MAIN_HAND,
									new BlockHitResult(new Vec3d(pos.getX(), pos.getY(), pos.getZ()), Direction.EAST, pos, false)))));*/
		}

	}

	@Inject(method = "render", at = @At("HEAD")) private void newRender(MatrixStack matrices, int mouseX, int mouseY, float delta, CallbackInfo ci)
	{

		if (screen instanceof InventoryScreen)
		{
			menuButtons.forEach(socialButtons -> socialButtons.render(matrices, mouseX, mouseY, delta));
			//			GuiHelper.fill(matrices, 7, 0, 0, 100, 100, -1);
		}
		if (screen instanceof CreativeInventoryScreen)
		{
			Watermark.render(matrices, this.x, this.y - 24, this.backgroundWidth, this.backgroundHeight + 48, this.width, this.height);
		} else
		{
			Watermark.render(matrices, this.x, this.y, this.backgroundWidth, this.backgroundHeight, this.width, this.height);
		}
	}

	@Inject(method = "mouseClicked", at = @At("TAIL")) public void mouseClicked(double mouseX, double mouseY, int button,
			CallbackInfoReturnable<Boolean> cir)
	{
		menuButtons.forEach(menuButton -> menuButton.mouseClicked(mouseX, mouseY, button));
	}

}
