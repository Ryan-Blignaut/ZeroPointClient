package github.thesivlerecho.zeropoint.render.widget;

import net.minecraft.client.gui.Element;
import net.minecraft.client.util.math.MatrixStack;

public class ButtonComponent extends PositioningComponent implements Renderable, Element
{

	private PositioningComponent button;
	private final Runnable clickEvent;
	private String text;
	private boolean resizeFromText;
	private int padding = 0;

	private boolean enabled;
	private boolean visible;

	public ButtonComponent(PositioningComponent button, Runnable clickEvent)
	{
		super(button);
		this.button = button;
		this.clickEvent = clickEvent;
	}


	public ButtonComponent setPadding(int padding)
	{
		this.padding = padding;
		return this;
	}

	@Override
	public boolean mouseClicked(double mouseX, double mouseY, int button)
	{
		if (isMouseOver(mouseX, mouseY))
		{
			clickEvent.run();
			return true;
		}
		return Element.super.mouseClicked(mouseX, mouseY, button);
	}


	@Override
	public void render(MatrixStack matrices)
	{
		if (this.visible)
			this.renderButton(matrices);
	}

	private void renderButton(MatrixStack matrices)
	{
	}
}
