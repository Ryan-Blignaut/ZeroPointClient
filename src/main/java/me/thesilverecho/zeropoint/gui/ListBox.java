package me.thesilverecho.zeropoint.gui;

import me.thesilverecho.zeropoint.config.SettingCategory;
import me.thesilverecho.zeropoint.gui.button.CheckBoxButton;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.MatrixStack;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.util.ArrayList;

public class ListBox
{
	private float width, height;
	private float x, y;
	private static final float scale = (float) MinecraftClient.getInstance().getWindow().getScaleFactor();
	ArrayList<CheckBoxButton> checkboxes = new ArrayList<>();
	private float menuXOffset;
	private float menuYOffset;
	private SettingCategory settingCategory;

	public ListBox(ArrayList<CheckBoxButton> checkboxes, float x, float y)
	{
		this.x = x;
		this.y = y;
		checkboxes.forEach(checkbox ->
				{
					if (checkbox.getWidth() > width)
						width = checkbox.getWidth();
					height = checkbox.getHeight() + 5;
				}
		);
		this.checkboxes = checkboxes;
	}

	public ListBox(float x, float y, SettingCategory category)
	{
		this.x = x;
		this.y = y;
		this.settingCategory = category;

	}

	public void addCheckbox(CheckBoxButton checkBoxButton)
	{
		checkboxes.add(checkBoxButton);
		resize(checkboxes);
	}

	private void resize(ArrayList<CheckBoxButton> buttons)
	{
		buttons.forEach(checkbox ->
		{
			if (checkbox.getWidth() > width)
				width = checkbox.getWidth();
			height = checkbox.getHeight() + 5;
		});
	}


	public void onClick(double mouseX, double mouseY)
	{
		checkboxes.forEach(checkBoxButton -> checkBoxButton.onClick(mouseX, mouseY));
	}


	public void render(MatrixStack matrixStack, int mouseX, int mouseY, float delta)
	{
		GuiHelper.fill(matrixStack, GL11.GL_QUADS, menuXOffset + (x) / scale, menuYOffset + y / scale, menuXOffset + (width) / scale, menuYOffset + (height) / scale, new Color(20, 20, 20).getRGB());
		GuiHelper.fill(matrixStack, GL11.GL_QUADS, menuXOffset + (x + 1) / scale, menuYOffset + (y + 1) / scale, menuXOffset + (width - 1) / scale, menuYOffset + (height - 1) / scale, new Color(73, 73, 86).getRGB());
		GuiHelper.fill(matrixStack, GL11.GL_QUADS, menuXOffset + (x + 2) / scale, menuYOffset + (y + 2) / scale, menuXOffset + (width - 2) / scale, menuYOffset + (height - 2) / scale, new Color(33, 33, 39).getRGB());
		MinecraftClient.getInstance().textRenderer.draw(matrixStack, settingCategory.name(), (menuXOffset + (x / 2) / scale + (width / 2) / scale), height, -1);
		checkboxes.forEach(checkbox -> checkbox.setXOffset(menuXOffset + (x + 5) / scale).setYOffset(menuYOffset + (y + 5) / scale).render(matrixStack, mouseX, mouseY, delta));
	}

	public ListBox setMenuXOffset(float menuXOffset)
	{
		this.menuXOffset = menuXOffset;
		return this;
	}

	public ListBox setMenuYOffset(float menuYOffset)
	{
		this.menuYOffset = menuYOffset;
		return this;
	}


	//	private void box(MatrixStack matrixStack, int x, int y, int w, int height)
//	{
//	}

}
