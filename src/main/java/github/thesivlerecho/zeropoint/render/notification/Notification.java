package github.thesivlerecho.zeropoint.render.notification;

import github.thesivlerecho.zeropoint.render.DrawingUtil;
import github.thesivlerecho.zeropoint.render.widget.PositioningComponent;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.util.Window;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Util;

import java.awt.*;

public class Notification
{
	private final String title, info;
	private final long startTime;
	private final int displayTime;
	private long displayingTime = 0;
	private boolean Disposing;
	private float posX, posY;

	private final PositioningComponent box;
	private static final TextRenderer TEXT_RENDERER = MinecraftClient.getInstance().textRenderer;


	public Notification(String title, String info, int displayTime, float x, float y)
	{
		this.title = title;
		this.info = info;
		this.displayTime = displayTime;
		this.startTime = Util.getMeasuringTimeMs();
		this.posX = x;
		this.posY = y;

		float baseWidth = 180;
		final int graphicSize = 45;
		if (TEXT_RENDERER.getWidth(title) * 1.1 + graphicSize >= baseWidth)
			baseWidth = (float) (TEXT_RENDERER.getWidth(title) * 1.1 + graphicSize);
		if (TEXT_RENDERER.getWidth(info) * 0.9 + graphicSize >= baseWidth)
			baseWidth = (float) (TEXT_RENDERER.getWidth(info) * 0.9 + graphicSize);
		box = new PositioningComponent(0, 0, baseWidth, graphicSize);
	}

	public Notification(String title, String info, int x, int y)
	{
		this(title, info, 10, x, y);
	}


	public void onRender(MatrixStack matrixStack)
	{
		final Window window = MinecraftClient.getInstance().getWindow();
		if (startTime - displayTime > displayingTime)
		{
			Disposing = startTime - displayTime > displayingTime;
			posY = window.getScaledHeight() + 10;
		}

		box.setX(posX);
		box.setY(posY);

		DrawingUtil.drawBoxWithShader(matrixStack, box);

		matrixStack.push();
		matrixStack.scale(1.1f, 1.1f, 1.1f);
		TEXT_RENDERER.drawWithShadow(matrixStack, title, (box.getX() + 45) / 1.1f, (box.getY() + 15 - TEXT_RENDERER.fontHeight / 2f) / 1.1f, Color.WHITE.getRGB());
		matrixStack.pop();

		matrixStack.push();
		matrixStack.scale(0.9f, 0.9f, 0.9f);
		TEXT_RENDERER.drawWithShadow(matrixStack, title, (box.getX() + 45) / 0.9f, (box.getY() + 17 - TEXT_RENDERER.fontHeight / 2f) / 0.9f, Color.WHITE.getRGB());
		matrixStack.pop();

//		if (!Disposing && )
//		{
//		}


	}

	public void setPosX(float posX)
	{
		this.posX = posX;
	}

	public void setPosY(float posY)
	{
		this.posY = posY;
	}
}
