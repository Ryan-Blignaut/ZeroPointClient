package me.thesilverecho.zeropoint.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Config
{
	private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
	private static JsonObject config = new JsonObject();
	private File file;
	private final ArrayList<Object> configObjects = new ArrayList<>();

	public void loadToJson(Object o)
	{
		Class<?> clazz = o.getClass();
		Arrays.stream(clazz.getDeclaredFields()).filter(field -> field.isAnnotationPresent(ConfigOption.class) && config.has(clazz.getSimpleName())).forEach(field ->
		{
			field.setAccessible(true);
			JsonObject classObject = config.get(clazz.getSimpleName()).getAsJsonObject();
			try
			{
				classObject.add(field.getName(), gson.toJsonTree(field.get(o), field.getType()));
			} catch (IllegalAccessException e)
			{
				e.printStackTrace();
			}
		});
	}

	public Config(File file)
	{
		this.file = file;
		if (!file.exists())
		{
			config = new JsonObject();
			saveConfig();
		} else
			try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file)))
			{
				config = new JsonParser().parse(bufferedReader.lines().collect(Collectors.joining())).getAsJsonObject();
			} catch (IOException e)
			{
				e.printStackTrace();
			}
	}

	private void saveConfig()
	{
		try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file)))
		{
			file.createNewFile();
			bufferedWriter.write(gson.toJson(config));
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public void save()
	{
		configObjects.forEach(this::loadToJson);
		saveConfig();
	}

	public void register(Object o)
	{
		if (Arrays.stream(o.getClass().getDeclaredFields()).noneMatch(field -> field.isAnnotationPresent(ConfigOption.class)))
			return;
		loadToClass(o);
		configObjects.add(o);
	}

	public void loadToClass(Object o)
	{
		Class<?> clazz = o.getClass();
		if (!config.has(clazz.getSimpleName()))
			config.add(clazz.getSimpleName(), new JsonObject());
		Arrays.stream(clazz.getDeclaredFields()).filter(field -> field.isAnnotationPresent(ConfigOption.class) && config.has(clazz.getSimpleName())).forEach(field ->
		{
			try
			{
				field.setAccessible(true);
				field.set(o, gson.fromJson(config.getAsJsonObject(clazz.getSimpleName()).get(field.getName()), field.getType()));
			} catch (IllegalAccessException e)
			{
				e.printStackTrace();
			}
		});
	}
}
