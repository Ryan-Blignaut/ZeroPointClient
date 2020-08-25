package me.thesilverecho.zeropoint.mixin;

import com.google.common.collect.Lists;
import me.thesilverecho.zeropoint.ZeroPointClient;
import me.thesilverecho.zeropoint.gui.CustomColor;
import me.thesilverecho.zeropoint.gui.RenderUtil;
import me.thesilverecho.zeropoint.gui.button.ClickableButton;
import me.thesilverecho.zeropoint.gui.button.DropdownButton;
import me.thesilverecho.zeropoint.gui.button.SocialButtons;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
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

@Mixin(TitleScreen.class) public abstract class TitleScreenMixin extends Screen
{
	private ArrayList<SocialButtons> menuButtons;
	private ArrayList<DropdownButton> testButtons;

	private Identifier background;

	protected TitleScreenMixin(Text title)
	{
		super(title);
	}

	private final Identifier gitHub = new Identifier("zero-point", "textures/ui/social/github.png");
	private final Identifier youtube = new Identifier("zero-point", "textures/ui/social/youtube.png");

	@Inject(method = "init", at = @At(value = "HEAD"), cancellable = true) public void init(CallbackInfo ci)
	{
		menuButtons = new ArrayList<>();
		testButtons = new ArrayList<>();

		menuButtons.add(new SocialButtons(width - 30, height - 30, 25, 25, "https://github.com/TheSilverEcho/ZeroPointClient", gitHub,
				new Color(83, 39, 125)));
		menuButtons.add(new SocialButtons(width - 60, height - 30, 25, 25, "https://www.youtube.com/channel/UCXGYBsaz8RlVWMmo5mGZaaQ", youtube,
				new Color(150, 0, 0)));

		testButtons.add(new DropdownButton(50, 50, 40, 10,
				Lists.newArrayList(new ClickableButton(50, 50, 40, 10, "test", e -> ZeroPointClient.LOGGER.info("test btn 1")),
						new ClickableButton(50, 50, 40, 10, "test", e -> ZeroPointClient.LOGGER.info("test btn 2")))));

	/*	if (client != null)
		{
			this.menuButtons.add(
					new MenuButton(30, 10, 55, 10, "Play", button -> this.client.openScreen(new SelectWorldScreen(this))).setShader(shader));
			this.menuButtons.add(
					new MenuButton(30, 25, 55, 10, "Multiplayer", button -> this.client.openScreen(new MultiplayerScreen(this))).setShader(shader));
			this.menuButtons.add(
					new MenuButton(30, 40, 55, 10, "Options", button -> this.client.openScreen(new OptionsScreen(this, this.client.options)))
							.setShader(shader));
			this.menuButtons.add(new MenuButton(30, 55, 55, 10, "Quit", button -> this.client.scheduleStop()).setShader(shader));
		}*/
		background = new Identifier("zero-point", "textures/ui/zero-logo.png");

	}

	@Inject(method = "render", at = @At(value = "HEAD"), cancellable = true) public void renderCustom(MatrixStack matrices, int mouseX, int mouseY,
			float delta, CallbackInfo ci)
	{
		ci.cancel();
		MinecraftClient.getInstance().getTextureManager().bindTexture(background);
		drawTexture(matrices, 0, 0, this.width, this.height, mouseX * 2, mouseY, 3440 - 640 * 2, 1440 - 360, 3440, 1440);

		RenderUtil.drawRect(matrices, 10, 10, 12, 12, Color.red.getRGB());
		RenderUtil.drawTorus(matrices, 0, 360, 321, 12, 24, new CustomColor(Color.red.getRGB()), new CustomColor(Color.BLUE.getRGB()));
		RenderUtil.drawRectCorners(matrices, 20, 20, 30, 30, 4, Color.YELLOW.getRGB());
		menuButtons.forEach(socialButtons -> socialButtons.render(matrices, mouseX, mouseY, delta));
		testButtons.forEach(dropdownButton -> dropdownButton.render(matrices, mouseX, mouseY, delta));
	}

	@Inject(method = "mouseClicked", at = @At("HEAD"), cancellable = true) public void mouseClicked(double mouseX, double mouseY, int button,
			CallbackInfoReturnable<Boolean> cir)
	{
		testButtons.forEach(dropdownButton -> dropdownButton.mouseClicked(mouseX, mouseY, button));
		menuButtons.forEach(menuButton -> menuButton.mouseClicked(mouseX, mouseY, button));
		if (super.mouseClicked(mouseX, mouseY, button))
		{
			cir.setReturnValue(true);
		}
	}

}
