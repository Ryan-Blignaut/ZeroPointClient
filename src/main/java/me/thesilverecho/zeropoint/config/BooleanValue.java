package me.thesilverecho.zeropoint.config;

public class BooleanValue
{
	private boolean on;
	private String name;

	public BooleanValue(boolean b)
	{
		on = b;
	}

	public BooleanValue()
	{
		on = false;
	}

	public String getName()
	{
		return name;
	}

	public BooleanValue setName(String name)
	{
		this.name = name;
		return this;
	}

	public boolean isOn()
	{
		return on;
	}

	public BooleanValue setOn(boolean on)
	{
		this.on = on;
		return this;
	}

	public void toggle()
	{
		this.on = !this.on;
		Config2.updateConfig();
	}

}
