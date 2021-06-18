package github.thesivlerecho.zeropoint.mixin;

import github.thesivlerecho.zeropoint.Transition;
import github.thesivlerecho.zeropoint.gui.old.button.ClickableButton;
import github.thesivlerecho.zeropoint.gui.old.overlay.watermark.Watermark;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.*;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.Util;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

//import javafx.scene.media.MediaPlayer;

@Mixin(HandledScreen.class)
public abstract class HandledScreenMixin<T extends ScreenHandler> extends Screen implements ScreenHandlerProvider<T>
{
	private final HandledScreen<?> screen = (HandledScreen<?>) (Object) this;
	@Shadow
	protected int x;
	@Shadow
	protected int y;
	@Shadow
	protected int backgroundWidth;
	@Shadow
	protected int backgroundHeight;
	private ArrayList<ClickableButton> menuButtons;

	protected HandledScreenMixin(Text title)
	{
		super(title);
	}

	@Inject(method = "init", at = @At("RETURN"))
	private void initNewButtons(CallbackInfo ci)
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

	private long lastPressTime;

	@Inject(method = "<init>", at = @At("RETURN"))
	private void init(CallbackInfo ci)
	{
		//init last press time when the screen is opened
		lastPressTime = -1;
	}

//	private static MediaPlayer mediaPlayer;

	@Inject(method = "render", at = @At("HEAD"))
	private void newRender(MatrixStack matrices, int mouseX, int mouseY, float delta, CallbackInfo ci)
	{
		matrices.push();
		String name = "Test";
		float pos = 0;
//		glEnable(GL_TEXTURE_2D);
//		GlStateManager.texParameter(3553, 10241, 9986);
//		GlStateManager.texParameter(3553, 10240, 9728);
		for (int i = 0; i < name.toCharArray().length; i++)
		{
//			pos += ZeroPointClient.font.drawChar(name.toCharArray()[i], ((int) pos), 20);
		}

		matrices.pop();
//		RenderSystem.popMatrix();
		if (lastPressTime == -1)
			lastPressTime = Util.getMeasuringTimeMs();
//		1 tenth of a second
		final double delay = 1000d;
//		this var will change from 0->1 over the delay time
		final double tanh = Transition.easeInElastic(Math.tanh((Util.getMeasuringTimeMs() - lastPressTime) / delay));
//		final double tanh = Math.tanh((Util.getMeasuringTimeMs() - lastPressTime) / delay);
//      reverse this so that we interpolate from 1->0
		final double reverse = 1 - tanh;

		final double slide = (y - 80) * -reverse;
//		translate the matrix stack(seems to only move gui)
//		matrices.translate(0, slide, 0);
//

		if (screen instanceof GenericContainerScreen)
//			RenderSystem.translatef(0, ((float) slide), 0);
			matrices.translate(0, (float) slide, 0);

		if (screen instanceof InventoryScreen)
		{
			matrices.scale((float) tanh, (float) tanh, 1);
//			RenderSystem.scalef((float) tanh, (float) tanh, 1);
		} else
		{
		}
		if (screen instanceof CreativeInventoryScreen)
		{
			Watermark.render(matrices, this.x, this.y - 24, this.backgroundWidth, this.backgroundHeight + 48, this.width, this.height);
		} else
		{
			Watermark.render(matrices, this.x, this.y, this.backgroundWidth, this.backgroundHeight, this.width, this.height);
		}
	}

	@Inject(method = "mouseClicked", at = @At("TAIL"))
	public void mouseClicked(double mouseX, double mouseY, int button,
	                         CallbackInfoReturnable<Boolean> cir)
	{
		menuButtons.forEach(menuButton -> menuButton.mouseClicked(mouseX, mouseY, button));
	}

}
