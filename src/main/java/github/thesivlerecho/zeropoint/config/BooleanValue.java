package github.thesivlerecho.zeropoint.config;

public class BooleanValue
{
	private boolean on;
	private String name;
	private SettingCategory Category;

	public BooleanValue(String name, SettingCategory category)
	{
		this.on = false;
		this.name = name;
		Category = category;
	}

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
//		Config2.updateConfig();
	}

	public SettingCategory getCategory()
	{
		return Category;
	}

}
