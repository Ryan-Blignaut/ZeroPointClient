package github.thesivlerecho.zeropoint.gui.screen;

import github.thesivlerecho.zeropoint.config.Settings;
import github.thesivlerecho.zeropoint.gui.GuiHelper;
import github.thesivlerecho.zeropoint.gui.button.CheckBoxButton;
import github.thesivlerecho.zeropoint.gui.menu.MenuPage;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gl.Framebuffer;
import net.minecraft.client.gl.PostProcessShader;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.LiteralText;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.util.ArrayList;

public class MenuScreen extends Screen
{
	private static final float scale = (float) MinecraftClient.getInstance().getWindow().getScaleFactor();
	private final ArrayList<CheckBoxButton> menuButtonList = new ArrayList<>();
	private final MenuPage menuPage = new MenuPage();
	PostProcessShader blurShaderHorz = null;
	private boolean keyDown;
	private boolean dragging;
	private int lastX;
	private int lastY;
	private float menuYOffset;
	private float menuXOffset;

	private Framebuffer blurOutputHorz;
	private Framebuffer blurOutputVert;
	private int lastBgBlurFactor = 6;


	public MenuScreen()
	{
		super(new LiteralText("Menu"));
	}

	@Override
	protected void init()
	{
		super.init();
		menuPage.init();
//		menuButtonList.add(new CheckBoxButton(0, 0, 10, 10, "test button", Settings.renderIcons));
//		listBox = new ListBox(menuButtonList, 0, 0);
	}

	@Override
	public void render(MatrixStack matrixStack, int mouseX, int mouseY, float delta)
	{
//		MinecraftClient.getInstance().gameRenderer.disableShader();
//		MinecraftClient.getInstance().gameRenderer.getShader().

		menuXOffset = Settings.MENU_X_OFFSET;
		menuYOffset = Settings.MENU_Y_OFFSET;
		if (dragging && keyDown)
		{
			Settings.MENU_X_OFFSET = Settings.MENU_X_OFFSET + mouseX - lastX;
			Settings.MENU_Y_OFFSET = Settings.MENU_Y_OFFSET + mouseY - lastY;
		}
		lastX = mouseX;
		lastY = mouseY;

		// own imp ->		super.render(mouseX, mouseY, delta);
		menuPage.renderSettingsMenu(matrixStack, mouseX, mouseY, delta, scale);
	}

	private void box(MatrixStack matrixStack, int x, int y, int w, int h)
	{
		GuiHelper.fill(matrixStack, GL11.GL_QUADS, menuXOffset + (x) / scale, menuYOffset + y / scale, menuXOffset + (w) / scale, menuYOffset + (h) / scale, new Color(20, 20, 20).getRGB());
		GuiHelper.fill(matrixStack, GL11.GL_QUADS, menuXOffset + (x + 1) / scale, menuYOffset + (y + 1) / scale, menuXOffset + (w - 1) / scale, menuYOffset + (h - 1) / scale, new Color(73, 73, 86).getRGB());
		GuiHelper.fill(matrixStack, GL11.GL_QUADS, menuXOffset + (x + 2) / scale, menuYOffset + (y + 2) / scale, menuXOffset + (w - 2) / scale, menuYOffset + (h - 2) / scale, new Color(33, 33, 39).getRGB());
	}

/*	private void blur(MatrixStack matrixStack)
	{
//		Matrix4f model = matrixStack.peek().getModel();
		int width = MinecraftClient.getInstance().getWindow().getFramebufferWidth();
		int height = MinecraftClient.getInstance().getWindow().getFramebufferHeight();

		if (5 <= 0)
			return;
		if (blurOutputHorz == null)
		{
			blurOutputHorz = new Framebuffer(width, height, false, false);
			blurOutputHorz.setTexFilter(GL11.GL_NEAREST);
		}
		if (blurOutputHorz.viewportWidth != width || blurOutputHorz.viewportHeight != height)
		{
			blurOutputHorz.initFbo(width, height, false);
			blurShaderHorz.setProjectionMatrix(createProjectionMatrix(width, height));
			MinecraftClient.getInstance().getFramebuffer().beginWrite(false);
		}
		if (blurShaderHorz == null)
		{
			try
			{
				blurShaderHorz = new PostProcessShader(MinecraftClient.getInstance().getResourceManager(),
						"blur", MinecraftClient.getInstance().getFramebuffer(), blurOutputHorz);
				blurShaderHorz.getProgram().getUniformByName("BlurDir").set(1, 0);
				blurShaderHorz.setProjectionMatrix(createProjectionMatrix(width, height));
			} catch (Exception e)
			{
				e.printStackTrace();
			}
		}


		if (blurOutputHorz != null)
			if (15 != lastBgBlurFactor)
			{
				blurShaderHorz.getProgram().getUniformByName("Radius").set((float) 15);
				lastBgBlurFactor = 15;
			}

		GL11.glPushMatrix();
		blurShaderHorz.render(0);
		GlStateManager.enableDepthTest();
		GL11.glPopMatrix();

		MinecraftClient.getInstance().getFramebuffer().beginWrite(false);
	}

	private Matrix4f createProjectionMatrix(int width, int height)
	{
//		Matrix4f projMatrix = new Matrix4f();
		Matrix4f matrix4f = Matrix4f.projectionMatrix(width, -height, 1, 2);
		matrix4f.loadIdentity();
//		projMatrix.m00 = 2.0F / (float)width;
//		projMatrix.m11 = 2.0F / (float)(-height);
//		projMatrix.m22 = -0.0020001999F;
//		projMatrix.m33 = 1.0F;
//		projMatrix.m03 = -1.0F;
//		projMatrix.m13 = 1.0F;
//		projMatrix.m23 = -1.0001999F;
		return matrix4f;
	}

	public void renderBlurredBackground(MatrixStack matrixStack, int width, int height, int x, int y, int blurWidth, int blurHeight)
	{
		float uMin = x / (float) width;
		float uMax = (x + blurWidth) / (float) width;
		float vMin = (height - y) / (float) height;
		float vMax = (height - y - blurHeight) / (float) height;

		blurOutputHorz.beginRead();
		GlStateManager.color4f(1f, 1f, 1f, 1f);
		GuiHelper.drawTexturedRect(x, y, blurWidth, blurHeight, uMin, uMax, vMin, vMax, GL11.GL_LINEAR);
		blurOutputHorz.endRead();
	}*/

	@Override
	public boolean mouseClicked(double mouseX, double mouseY, int button)
	{
//		menuButtonList.forEach(button1 -> button1.onClick(mouseX, mouseY));
		menuPage.onClickMenu(mouseX, mouseY);
//		listBox.onClick(mouseX, mouseY);
		if (isMouseOver(mouseX, mouseY))
			dragging = true;

		return super.mouseClicked(mouseX, mouseY, button);
	}

	@Override
	public boolean mouseReleased(double mouseX, double mouseY, int button)
	{
//		menuButtonList.forEach(menuButton -> menuButton.mouseClicked(mouseX, mouseY, button));
		dragging = false;
		return super.mouseReleased(mouseX, mouseY, button);
	}

	@Override
	public boolean keyPressed(int keyCode, int scanCode, int modifiers)
	{
		if (keyCode == GLFW.GLFW_KEY_LEFT_CONTROL)
			keyDown = true;
		return super.keyPressed(keyCode, scanCode, modifiers);
	}

	@Override
	public boolean keyReleased(int keyCode, int scanCode, int modifiers)
	{
		if (keyCode == GLFW.GLFW_KEY_LEFT_CONTROL)
			keyDown = false;
		return super.keyReleased(keyCode, scanCode, modifiers);
	}

	@Override
	public boolean isMouseOver(double mouseX, double mouseY)
	{
		return mouseX >= Settings.MENU_X_OFFSET / scale && mouseY >= Settings.MENU_Y_OFFSET / scale && mouseX < (Settings.MENU_X_OFFSET + (550) / scale) && mouseY < (Settings.MENU_Y_OFFSET + (650) / scale);
	}

	@Override
	public boolean isPauseScreen()
	{
		return false;
	}

	@Override
	public void onClose()
	{
		super.onClose();
		Settings.save();
	}

}
