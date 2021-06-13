package github.thesivlerecho.zeropoint.render.particle;

import github.thesivlerecho.zeropoint.render.DrawingUtil;
import github.thesivlerecho.zeropoint.render.widget.Component2d;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.MatrixStack;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SnowParticle
{
	private final float size;
	private final int color;
	private float positionX, positionY, velocityX, velocityY;
	private static final List<SnowParticle> customParticles = new ArrayList<>();

	public static void init()
	{
		for (int i = 0; i < 100; i++)
		{
			float size = (float) ((Math.random() * 3) + 1);
			float x = (float) (Math.random() * ((MinecraftClient.getInstance().getWindow().getScaledWidth() - size * 2) - (size * 2)) + size * 2);
			float y = 0; /*(Math.random() * ((canvas.getHeight() - size * 2) - (size * 2)) + size * 2);*/
			float dirX = (float) (((Math.random() * 5) - 2.5) / 2);
			float dirY = (float) (((Math.random() * 5)) / 2);

			customParticles.add(new SnowParticle(size, new Color(0.9607843f, 0.9607843f, 0.9607843f, 0.8f).getRGB(), x, y, dirX, dirY));
		}
	}

	public static void render(MatrixStack matrixStack)
	{
		customParticles.forEach(snowParticle -> snowParticle.update(matrixStack));
	}

	private void update(MatrixStack matrixStack)
	{
		final int width = MinecraftClient.getInstance().getWindow().getScaledWidth();
		final int height = MinecraftClient.getInstance().getWindow().getScaledHeight();

		if ((this.positionX > width))
			this.positionX = (float) Math.random();
		else if (this.positionX < 0)
			this.positionX = (float) (width - Math.random());
		if ((this.positionY > height) || this.positionY < 0)
			this.positionY = 0;

		this.positionX += this.velocityX;
		this.positionY += this.velocityY;
		draw(matrixStack);
	}

	private void draw(MatrixStack matrixStack)
	{
		DrawingUtil.drawCircleShader(new Component2d(positionX, positionY, size, size).setColour(color), size / 2, 3, component2d -> DrawingUtil.drawBoxWithShader(matrixStack, component2d));
	}

	public SnowParticle(float size, int color, float positionX, float positionY, float velocityX, float velocityY)
	{
		this.size = size;
		this.positionX = positionX;
		this.positionY = positionY;
		this.velocityX = velocityX;
		this.velocityY = velocityY;
		this.color = color;
	}
}
