package me.thesilverecho.zeropoint;

import java.util.ArrayList;

public class User
{

	private String level;
	private String equippedCape;
	private boolean effect;
	private ArrayList<String> unlocked;

	public User(String level, String equippedCape, boolean effect, ArrayList<String> unlocked)
	{
		this.level = level;
		this.equippedCape = equippedCape;
		this.effect = effect;
		this.unlocked = unlocked;
	}

	public String getEquippedCape()
	{
		return equippedCape;
	}

	@Override
	public String toString()
	{
		return "User{" +
				"level='" + level + '\'' +
				", equippedCape='" + equippedCape + '\'' +
				", unlocked=" + unlocked +
				'}';
	}

	public boolean isEffect()
	{
		return effect;
	}
}
