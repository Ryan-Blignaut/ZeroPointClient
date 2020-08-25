package me.thesilverecho.zeropoint.mixin;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.Drawable;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.widget.AbstractButtonWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractButtonWidget.class) public abstract class AbstractButtonWidgetMixin extends DrawableHelper implements Drawable, Element
{

	private final Identifier newButtonID = new Identifier("zero-point", "textures/ui/button.png");
	private final Identifier newButtonIDHover = new Identifier("zero-point", "textures/ui/button-hover.png");

	@Shadow protected float alpha;

	@Shadow protected abstract int getYImage(boolean hovered);

	@Shadow public abstract boolean isHovered();

	@Shadow public int x;
	@Shadow public int y;
	@Shadow protected int width;
	@Shadow protected int height;

	@Shadow protected abstract void renderBg(MatrixStack matrices, MinecraftClient client, int mouseX, int mouseY);

	@Shadow public boolean active;

	@Shadow public abstract Text getMessage();

	@Shadow public abstract boolean isMouseOver(double mouseX, double mouseY);

	@Shadow
	@Final
	public static Identifier WIDGETS_LOCATION;

	@Inject(at = @At("HEAD"), method = "render", cancellable = true) public void renderButton(MatrixStack matrices, int mouseX, int mouseY,
			float delta, CallbackInfo callbackInfo)
	{
		callbackInfo.cancel();
		MinecraftClient minecraftClient = MinecraftClient.getInstance();
		TextRenderer textRenderer = minecraftClient.textRenderer;
		RenderSystem.color4f(1.0F, 1.0F, 1.0F, this.alpha);
		minecraftClient.getTextureManager().bindTexture(WIDGETS_LOCATION);

		this.getYImage(this.isHovered());

		if (mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height || this.isHovered())
		{
			minecraftClient.getTextureManager().bindTexture(newButtonIDHover);
		} else
		{
			minecraftClient.getTextureManager().bindTexture(newButtonID);
		}

		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		RenderSystem.enableDepthTest();

		int newWidth = this.height * 5;
		drawTexture(matrices, this.x, this.y, 0.0f, 0.0f, 5, this.height, newWidth, this.height);
		drawTexture(matrices, this.x + 5, this.y, 5.0f, 0.0f, this.width - 10, this.height, this.width, this.height);
		drawTexture(matrices, this.x + this.width - 5, this.y, (float) (newWidth - 5), 0.0f, 5, this.height, newWidth, this.height);
		RenderSystem.disableBlend();

		this.renderBg(matrices, minecraftClient, mouseX, mouseY);
		int j = this.active ? 16777215 : 10526880;
		drawCenteredText(matrices, textRenderer, this.getMessage(), this.x + this.width / 2, this.y + (this.height - 8) / 2,
				j | MathHelper.ceil(this.alpha * 255.0F) << 24);
	}
}
