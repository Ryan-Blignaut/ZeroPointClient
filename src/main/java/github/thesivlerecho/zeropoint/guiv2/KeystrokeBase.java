package github.thesivlerecho.zeropoint.guiv2;

import com.mojang.blaze3d.systems.RenderSystem;
import github.thesivlerecho.zeropoint.module.impl.render.KeyStrokes;
import github.thesivlerecho.zeropoint.render.DrawingUtil;
import github.thesivlerecho.zeropoint.render.shader.ZeroPointShader;
import github.thesivlerecho.zeropoint.render.shader.programs.RoundedRectangleShader;
import github.thesivlerecho.zeropoint.render.widget.Component2d;
import github.thesivlerecho.zeropoint.render.widget.PositioningComponent;
import github.thesivlerecho.zeropoint.util.CircleManager;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.math.MatrixStack;

import java.awt.*;

public class KeystrokeBase extends Component2d
{
	private final KeyBinding keyBinding;
	private final CircleManager circleManager;
	private boolean wasPressed;

	//	public KeystrokeBase(KeyBinding keyBinding, int x, int y)
//	{
//		this(keyBinding, );
//	}
	public KeystrokeBase(KeyBinding keyBinding, float x, float y)
	{
		this(keyBinding, x, y, KeyStrokes.DIMENSIONS, KeyStrokes.DIMENSIONS);
	}

	public KeystrokeBase(KeyBinding keyBinding, float x, float y, float w, float h)
	{
		super(new PositioningComponent(x, y, w, h));
		this.keyBinding = keyBinding;
		this.circleManager = new CircleManager();
	}

//	public void renderKey(MatrixStack matrixStack)
//	{
//		final boolean isPressed = keyBinding.isPressed();
//		final PositioningComponent f = new PositioningComponent(this.box);
//		f.addToX(KeyStrokes.offset.x);
//		f.addToY(KeyStrokes.offset.y);
//
//		if (isPressed != wasPressed)
//		{
//			wasPressed = isPressed;
//			if (isPressed)
//			{
//				final PositioningComponent positioningComponent = new PositioningComponent(f).resizeComponent(1);
//				circleManager.addCircle(new CircleComponent(positioningComponent, positioningComponent.getW() - positioningComponent.getX(), 10));
//			}
//		}
//
//
////		BlurPostprocessShader blurPostprocessShader = ShaderManager.getShader(BlurPostprocessShader.class, ZeroPointShader.BLUR);
////		blurPostprocessShader.draw();
//
////		doGlScissor(32, 32, 64, 64);
////		DrawingUtil.drawBlurWithShader(this.box, matrixStack);
//		BlurPostprocessShader.addBlurArea(f);
//		DrawingUtil.drawRectWithShader(f, 1, 1, matrixStack);
//
//
//		circleManager.drawCircles(matrixStack);
//
//		RenderSystem.disableBlend();
//	}

	public void updateKey()
	{
		circleManager.updateCircles();
	}

	@Override
	public void render(MatrixStack matrices)
	{
		final boolean isPressed = keyBinding.isPressed();
//		final PositioningComponent f = new PositioningComponent(this.box);
//		f.addToX(KeyStrokes.offset.x);
//		f.addToY(KeyStrokes.offset.y);

		if (isPressed != wasPressed)
		{
			wasPressed = isPressed;
			if (isPressed)
			{
//				final PositioningComponent positioningComponent = new PositioningComponent(f).resizeComponent(1);
//				circleManager.addCircle(new CircleComponent(positioningComponent, positioningComponent.getW() - positioningComponent.getX(), 10));
			}
		}


//		BlurPostprocessShader.addBlurArea(f);
//		DrawingUtil.drawRectWithShader(f, 1, 1, matrices);


		DrawingUtil.drawRectWithShader(this.getComponent(), 3, matrices);

		DrawingUtil.drawShader(matrices, this.getComponent(), ZeroPointShader.ROUND_RECT, shader ->
		{
			final float radius = 5f;
			DrawingUtil.setColor(Color.ORANGE.getRGB());
			RoundedRectangleShader.setRectangle(this.getComponent().getX() + radius, this.getComponent().getY() + radius, this.getComponent().getW() - radius, this.getComponent().getH() - radius);
			RoundedRectangleShader.setThickness(radius, 1f);
		});

		circleManager.drawCircles(matrices);

		RenderSystem.disableBlend();
	}
}
