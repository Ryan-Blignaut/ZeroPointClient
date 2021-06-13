package github.thesivlerecho.zeropoint.gui.screen;

import github.thesivlerecho.zeropoint.gui.GuiHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.texture.NativeImage;
import net.minecraft.client.texture.NativeImageBackedTexture;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.LiteralText;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class SlideOut extends Screen
{
	public static Identifier skin;
	private boolean wasPressed;
	private long lastPressTime;

	public SlideOut()
	{
		super(new LiteralText("slideOut"));
		this.wasPressed = false;
		this.lastPressTime = 0;

	}

	@Override
	protected void init()
	{
		super.init();
		TextFieldWidget a = new TextFieldWidget(MinecraftClient.getInstance().textRenderer, 0, 40, 20, 20, new LiteralText("A"));

		this.addDrawable(a);
		this.addDrawable(new ButtonWidget(0, 10, 20, 20, new LiteralText("skin"), e ->
		{
			System.out.println(a.getText());
			try (InputStream inputStream = new URL(a.getText()).openStream())
			{
				NativeImage read = NativeImage.read(inputStream);
				skin = MinecraftClient.getInstance().getTextureManager().registerDynamicTexture("skin", /*new NativeImageBackedTexture(image)*/new NativeImageBackedTexture(read));

			} catch (IOException malformedURLException)
			{
				malformedURLException.printStackTrace();
			}

		}));
	}

	@Override
	public void render(MatrixStack matrices, int mouseX, int mouseY, float delta)
	{
		super.render(matrices, mouseX, mouseY, delta);
		if (!wasPressed)
		{
			wasPressed = true;
			lastPressTime = Util.getMeasuringTimeMs();
		}

	/*	boolean isPressed = true;
		if (wasPressed != isPressed)
		{
			wasPressed = isPressed;
			lastPressTime = System.currentTimeMillis();
		}*/

		double tanh = Math.tanh((Util.getMeasuringTimeMs() - lastPressTime) / 1000d);

		GuiHelper.drawRect(matrices, 0, 0, (int) (tanh * (MinecraftClient.getInstance().getWindow().getScaledWidth() / 4)),
				MinecraftClient.getInstance().getWindow().getScaledHeight(), new Color(0, 0, 0, 100).getRGB());
	}

	@Override
	public void onClose()
	{

		super.onClose();
	}

	@Override
	public boolean isPauseScreen()
	{
		return false;
	}
}
