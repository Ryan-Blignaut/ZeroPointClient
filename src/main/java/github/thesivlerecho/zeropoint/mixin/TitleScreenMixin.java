package github.thesivlerecho.zeropoint.mixin;

import com.mojang.blaze3d.systems.RenderSystem;
import github.thesivlerecho.zeropoint.ZeroPointClient;
import github.thesivlerecho.zeropoint.gui.old.button.AbstractMenuButton;
import github.thesivlerecho.zeropoint.gui.old.button.ClickableButton;
import github.thesivlerecho.zeropoint.gui.old.button.SocialButtons;
import github.thesivlerecho.zeropoint.render.font.CustomFonts;
import github.thesivlerecho.zeropoint.render.particle.SnowParticle;
import github.thesivlerecho.zeropoint.render.shader.FrameBufferCustom;
import github.thesivlerecho.zeropoint.render.shader.ShaderManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.screen.multiplayer.MultiplayerScreen;
import net.minecraft.client.gui.screen.option.OptionsScreen;
import net.minecraft.client.gui.screen.world.SelectWorldScreen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.awt.*;
import java.util.ArrayList;

@Mixin(TitleScreen.class)
public abstract class TitleScreenMixin extends Screen
{
	private static final Identifier GIT_HUB = new Identifier(ZeroPointClient.MOD_ID, "textures/ui/social/github.png");
	private static final Identifier YOUTUBE = new Identifier(ZeroPointClient.MOD_ID, "textures/ui/social/youtube.png");
	private ArrayList<AbstractMenuButton> menuButtons;
	private Identifier background;

	protected TitleScreenMixin(Text title)
	{
		super(title);
	}

	@Inject(method = "init", at = @At(value = "HEAD"), cancellable = true)
	public void init(CallbackInfo ci)
	{
		if (this.client == null)
			return;
		menuButtons = new ArrayList<>();
		menuButtons.add(new SocialButtons(width - 30, height - 30, 25, 25, "https://github.com/TheSilverEcho/ZeroPointClient", GIT_HUB, new Color(83, 39, 125)));
		menuButtons.add(new SocialButtons(width - 60, height - 30, 25, 25, "https://www.youtube.com/channel/UCXGYBsaz8RlVWMmo5mGZaaQ", YOUTUBE, new Color(150, 0, 0)));
		menuButtons.add(new ClickableButton(10, 10, 30, 10, "Play", b -> this.client.openScreen(new SelectWorldScreen(this))));
		menuButtons.add(new ClickableButton(10, 25, 30, 10, "Multiplayer", b -> this.client.openScreen(new MultiplayerScreen(this))));
		menuButtons.add(new ClickableButton(10, 40, 30, 10, "Options", b -> this.client.openScreen(new OptionsScreen(this, this.client.options))));
		menuButtons.add(new ClickableButton(10, 55, 30, 10, "Quit", b -> /*this.client.scheduleStop()*/{
//			NotificationManager.INSTANCE.addNotification(new Notification("test", "test info", 50, 50));
			ShaderManager.reload(MinecraftClient.getInstance().getResourceManager());
		}));

		background = new Identifier(ZeroPointClient.MOD_ID, "textures/ui/zero-logo.png");
		SnowParticle.init();
	}

	private FrameBufferCustom frameBufferCustom = new FrameBufferCustom();

	@Inject(method = "render", at = @At(value = "HEAD"), cancellable = true)
	public void renderCustom(MatrixStack matrices, int mouseX, int mouseY, float delta, CallbackInfo ci)
	{
		ci.cancel();
		RenderSystem.setShaderTexture(0, background);
		drawTexture(matrices, 0, 0, this.width, this.height, mouseX * 2, mouseY, 3440 - 640 * 2, 1440 - 360, 3440, 1440);
//		SnowParticle.render(matrices);
//		DrawingUtil.drawBlurRectWithShader(new Component2d(50, 50, 50, 50).setColour(Color.PINK.getRGB()), component2d -> DrawingUtil.drawBoxWithShader(matrices, component2d));
		CustomFonts.CLIENT_FONT.getFont().drawString(matrices, 32, 100, "Ryan Test 123");

		menuButtons.forEach(btn -> btn.render(matrices, mouseX, mouseY, delta));
	}

	@Inject(method = "mouseClicked", at = @At("HEAD"), cancellable = true)
	public void mouseClicked(double mouseX, double mouseY, int button, CallbackInfoReturnable<Boolean> cir)
	{
		menuButtons.forEach(menuButton -> menuButton.mouseClicked(mouseX, mouseY, button));
		if (super.mouseClicked(mouseX, mouseY, button))
			cir.setReturnValue(true);
	}
}
