package github.thesivlerecho.zeropoint.render.widget;

public class CircleComponent extends Component2d
{

	private final double topRadius, speed;
	public double progress;
	public double lastProgress;
	public boolean complete;


	public CircleComponent(Component2d component2d, double topRadius, double speed)
	{
		super(component2d);
		this.topRadius = topRadius;
		this.speed = speed;
	}

	public double getTopRadius()
	{
		return topRadius;
	}

	public double getSpeed()
	{
		return speed;
	}


}
