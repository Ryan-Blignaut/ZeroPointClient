package github.thesivlerecho.zeropoint.render.widget;

public class CircleComponent extends PositioningComponent
{

	private final double topRadius, speed;
	public double progress;
	public double lastProgress;
	public boolean complete;


	public CircleComponent(PositioningComponent positioningComponent, double topRadius, double speed)
	{
		super(positioningComponent);
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
