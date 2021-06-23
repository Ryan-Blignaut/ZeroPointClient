package github.thesivlerecho.zeropoint.mixin;

import github.thesivlerecho.zeropoint.util.Transition;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.screen.ingame.ScreenHandlerProvider;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.Util;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

//import javafx.scene.media.MediaPlayer;

@Mixin(HandledScreen.class)
public abstract class HandledScreenMixin<T extends ScreenHandler> extends Screen implements ScreenHandlerProvider<T>
{
	private final HandledScreen<?> screen = (HandledScreen<?>) (Object) this;
	@Shadow
	protected int x;
	@Shadow
	protected int y;

	protected HandledScreenMixin(Text title)
	{
		super(title);
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

//		TODO: screen animation transitions

	/*	if (screen instanceof GenericContainerScreen)
			matrices.translate(0, (float) slide, 0);*/

/*		if (screen instanceof InventoryScreen)
		{
			matrices.scale((float) tanh, (float) tanh, 1);
		} else
		{
		}*/

//		TODO: watermark render
	/*	if (screen instanceof CreativeInventoryScreen)
		{
			Watermark.render(matrices, this.x, this.y - 24, this.backgroundWidth, this.backgroundHeight + 48, this.width, this.height);
		} else
		{
			Watermark.render(matrices, this.x, this.y, this.backgroundWidth, this.backgroundHeight, this.width, this.height);
		}*/
	}

	@Inject(method = "mouseClicked", at = @At("TAIL"))
	public void mouseClicked(double mouseX, double mouseY, int button,
			CallbackInfoReturnable<Boolean> cir)
	{
//		menuButtons.forEach(menuButton -> menuButton.mouseClicked(mouseX, mouseY, button));
	}

}
