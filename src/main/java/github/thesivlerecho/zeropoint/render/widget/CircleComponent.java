package github.thesivlerecho.zeropoint.render.widget;

public class CircleComponent extends Component2d
{

	private final double topRadius, speed;
	public double progress;
	public double lastProgress;
	public boolean complete;


	public CircleComponent(float x, float y, float width, float height, double topRadius, double speed)
	{
		super(x, y, width, height);
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

	public void render()
	{
	}

}
