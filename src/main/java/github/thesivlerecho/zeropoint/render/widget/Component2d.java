package github.thesivlerecho.zeropoint.render.widget;

import net.minecraft.client.util.math.MatrixStack;

public abstract class Component2d implements Renderable
{
	public static int USE_PREF_SIZE = 0, USE_COMPUTED_SIZE = 1, USE_RESCALE_TEXT = 2;
	private final int sizeParadigm;
	private final PositioningComponent component;
	private int colour = 0;
	protected boolean visible = true;

	public Component2d(PositioningComponent component, int sizeParadigm)
	{
		this.component = component;
		this.sizeParadigm = sizeParadigm;
	}

	public Component2d(PositioningComponent component)
	{
		this(component, USE_PREF_SIZE);
	}


	public boolean inMouseOver(int mouseX, int mouseY)
	{
		return mouseX >= this.component.getX() && mouseX <= this.component.getW() && mouseY >= this.component.getY() && mouseY <= this.component.getH();
	}

	public int getColour()
	{
		return colour;
	}

	public PositioningComponent getComponent()
	{
		return component;
	}

	public Component2d setColour(int colour)
	{
		this.colour = colour;
		return this;
	}

	@Override
	public abstract void render(MatrixStack matrices);

	public int getSizeParadigm()
	{
		return sizeParadigm;
	}

}
