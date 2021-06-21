package github.thesivlerecho.zeropoint.render.widget;

import net.minecraft.client.util.math.MatrixStack;

import java.util.ArrayList;

public class ContainerComponent<T extends Component2d> extends Component2d
{
	private final ArrayList<T> components = new ArrayList<>();
	private int padding;
	private int vGap;
	private boolean resize;

	@SafeVarargs
	public ContainerComponent(PositioningComponent component2d, T... components)
	{
		this(component2d);
		addChildren(components);
		padding = 3;
		vGap = 5;
	}

	public ContainerComponent()
	{
		this(new PositioningComponent(0, 0, 32, 32));
		resize = true;
	}

	public ContainerComponent(PositioningComponent component2d)
	{
		super(component2d);
	}

	@Override
	public void render(MatrixStack matrices)
	{
		components.forEach(component ->
		{
			component.getComponent().setX(this.getComponent().getX() + component.getComponent().getX());
			component.getComponent().setY(this.getComponent().getY() + component.getComponent().getY());

			if (component.getSizeParadigm() == Component2d.USE_COMPUTED_SIZE)
			{
				component.getComponent().setX(this.getComponent().getX() + padding);
				component.getComponent().setW(this.getComponent().getW() - padding);
				component.getComponent().setY(this.getComponent().getX() + vGap);
			}
			component.render(matrices);
		});
	}

	public ArrayList<T> getChildren()
	{
		return components;
	}

	@SafeVarargs
	public final void addChildren(T... components)
	{
		for (T component : components)
			addChild(component);
	}

	public void addChild(T component)
	{
		if (resize)
		{
			final float otherWidth = component.getComponent().getActualWidth();
			if (otherWidth > this.getComponent().getActualWidth())
				this.getComponent().setW(otherWidth);
			final float otherHeight = component.getComponent().getActualHeight();
			if (otherHeight > this.getComponent().getActualHeight())
				this.getComponent().setH(otherHeight);
		}
		components.add(component);
	}
}
