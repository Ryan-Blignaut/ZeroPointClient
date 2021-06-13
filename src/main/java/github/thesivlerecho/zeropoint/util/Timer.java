package github.thesivlerecho.zeropoint.util;

public class Timer
{
	private long time;

	public Timer()
	{
		time = System.currentTimeMillis();
	}

	public boolean passed(double ms)
	{
		return System.currentTimeMillis() - this.time >= ms;
	}

	public void reset()
	{
		this.time = System.currentTimeMillis();
	}

	public void resetTimeSkipTo(long MS)
	{
		this.time = System.currentTimeMillis() + MS;
	}

	public long getTime()
	{
		return time;
	}

	public void setTime(long time)
	{
		this.time = time;
	}

}
