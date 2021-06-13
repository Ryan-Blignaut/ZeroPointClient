package github.thesivlerecho.zeropoint.render.widget;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class Container2d extends Component2d
{
	private final ArrayList<Component2d> components = new ArrayList<>();

	public Container2d(int x, int y, int width, int height, Component2d... components)
	{
		super(x, y, width, height);
		this.addComponents(Arrays.asList(components));
	}

	public void addComponent(Component2d component)
	{
		components.add(component);
	}

	public void addComponents(Collection<Component2d> component)
	{
		components.addAll(component);
	}

}
