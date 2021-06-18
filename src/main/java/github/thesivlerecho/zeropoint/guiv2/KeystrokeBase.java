package github.thesivlerecho.zeropoint.guiv2;

import com.mojang.blaze3d.systems.RenderSystem;
import github.thesivlerecho.zeropoint.config.Settings;
import github.thesivlerecho.zeropoint.render.DrawingUtil;
import github.thesivlerecho.zeropoint.render.widget.CircleComponent;
import github.thesivlerecho.zeropoint.render.widget.Component2d;
import github.thesivlerecho.zeropoint.util.CircleManager;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.math.MatrixStack;

import java.awt.*;

public class KeystrokeBase
{
	public static final int BASE_KEY_SIZE = 22;
	private final KeyBinding keyBinding;
	private final Component2d box;
	private final CircleManager circleManager;
	private boolean wasPressed;

	public KeystrokeBase(KeyBinding keyBinding, int x, int y)
	{
		this(keyBinding, x, y, BASE_KEY_SIZE, BASE_KEY_SIZE);
	}

	public KeystrokeBase(KeyBinding keyBinding, int x, int y, int w, int h)
	{
		this.keyBinding = keyBinding;
		this.circleManager = new CircleManager();
		this.box = new Component2d(Settings.KEY_STROKES_OFFSET.x + x, Settings.KEY_STROKES_OFFSET.y + y, w, h).setColour(new Color(55, 49, 49, 80).getRGB());
	}

	public void renderKey(MatrixStack matrixStack)
	{
		boolean isPressed = keyBinding.isPressed();
		if (isPressed != wasPressed)
		{
			wasPressed = isPressed;
			if (isPressed)
			{
				final Component2d component2d = new Component2d(this.box).resizeComponent(1);
				circleManager.addCircle(new CircleComponent(component2d, component2d.getW() - component2d.getX(), 5));
			}
		}


//		BlurPostprocessShader blurPostprocessShader = ShaderManager.getShader(BlurPostprocessShader.class, ZeroPointShader.BLUR);
//		blurPostprocessShader.draw();

//		doGlScissor(32, 32, 64, 64);
//		DrawingUtil.drawBlurWithShader(this.box, matrixStack);
		DrawingUtil.drawRectWithShader(this.box, 1, 1, matrixStack);


		circleManager.drawCircles(matrixStack);

		RenderSystem.disableBlend();
	}

	public void updateKey()
	{
		circleManager.updateCircles();
	}
}
