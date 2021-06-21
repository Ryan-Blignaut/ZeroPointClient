package github.thesivlerecho.zeropoint.util;

import github.thesivlerecho.zeropoint.render.DrawingUtil;
import github.thesivlerecho.zeropoint.render.widget.CircleComponent;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.MatrixStack;

import java.util.ArrayList;
import java.util.List;

public class CircleManager
{
	public final List<CircleComponent> circles = new ArrayList<>();

	public void addCircle(CircleComponent component)
	{
		circles.add(component);
	}

	public void updateCircles()
	{
		List<CircleComponent> completes = new ArrayList<>();
		for (CircleComponent c : circles)
		{
			if (!c.complete)
			{
				runCircle(c);
			} else
			{
				completes.add(c);
			}
		}
		synchronized (circles)
		{
			circles.removeAll(completes);
		}
	}

	private void runCircle(CircleComponent c)
	{
		c.lastProgress = c.progress;

		c.progress += (c.getTopRadius() - c.progress) / (c.getSpeed()) + 0.01;
		if (c.progress >= c.getTopRadius())
			c.complete = true;
	}

	public void drawCircles(MatrixStack matrixStack)
	{
		circles.stream().filter(c -> !c.complete).forEach(c -> drawCircle(c, matrixStack));
	}

	public void drawCircle(CircleComponent c, MatrixStack matrixStack)
	{
		final float progress = (float) (c.progress * MinecraftClient.getInstance().getTickDelta() + (c.lastProgress * (1.0f - MinecraftClient.getInstance().getTickDelta())));
//		c.setColour(new Color(1f, 1f, 1f, (1 - Math.min(1f, Math.max(0f, (float) (progress / c.getTopRadius())))) / 2).getRGB());
		if (!c.complete)
			DrawingUtil.drawCircleShader(c, progress, 1, component2d -> DrawingUtil.drawBoxWithShader(matrixStack, component2d));
	}
}
