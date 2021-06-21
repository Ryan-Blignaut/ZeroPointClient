package github.thesivlerecho.zeropoint.util;

public class Transition
{
	public static float easeInBack(int number)
	{
		float c1 = 1.70158f;
		float c3 = c1 + 1;
		return c3 * number * number * number - c1 * number * number;
	}


	public static double easeInElastic(double x)
	{
		double c4 = (2 * Math.PI) / 3;
		return x == 0 ? 0 : x == 1 ? 1 : -Math.pow(2, 10 * x - 10) * Math.sin((x * 10 - 10.75) * c4);
	}


}
