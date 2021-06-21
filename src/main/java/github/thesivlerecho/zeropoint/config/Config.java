package github.thesivlerecho.zeropoint.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import github.thesivlerecho.zeropoint.config.selector.ConfigOption;
import github.thesivlerecho.zeropoint.util.Pair;
import net.minecraft.client.MinecraftClient;

import java.io.*;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class Config
{

	public final static Config CLIENT_INSTANCE = new Config(new File(MinecraftClient.getInstance().runDirectory, "Config" + File.separator + "Zero-point.json"));

	private final Gson gson = new GsonBuilder().setPrettyPrinting().create();
	private final File file;
	private JsonObject config = new JsonObject();
	//this array will hold all the instances of all classes that are loaded to the settings, useful to get all settings
	private static final CopyOnWriteArrayList<Pair<Field, Object>> INSTANCES = new CopyOnWriteArrayList<>();
	private static final CopyOnWriteArrayList<Object> configObjects = new CopyOnWriteArrayList<>();

	private Config(File configFile)
	{
		this.file = configFile;
		if (!configFile.exists())
		{
			this.config = new JsonObject();
			saveConfig();
		} else
			try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file)))
			{
				this.config = new JsonParser().parse(bufferedReader.lines().collect(Collectors.joining())).getAsJsonObject();
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
		configObjects.forEach(this::loadFieldToJson);
		saveConfig();
	}

	public void register(Object instance)
	{
		if (Arrays.stream(instance.getClass().getDeclaredFields()).noneMatch(f -> f.isAnnotationPresent(ConfigOption.class)))
			return;
		loadFieldToClass(instance);
		configObjects.add(instance);
	}

	private void loadFieldToClass(Object instance)
	{
		Class<?> clazz = instance.getClass();
		if (!config.has(clazz.getSimpleName()))
			config.add(clazz.getSimpleName(), new JsonObject());
		applyStream(field -> loadFieldToClass(field, instance), instance);
		applyStream(field -> INSTANCES.add(new Pair<>(field, instance)), instance);
	}

	private void loadFieldToJson(Object o)
	{

		applyStream(field -> loadFieldToJson(field, o), o);
	}

	private void loadFieldToClass(Field field, Object instance)
	{

		try
		{
			field.setAccessible(true);
			Class<?> clazz = instance.getClass();
			if (config.get(clazz.getSimpleName()).getAsJsonObject().has(field.getName()))
				field.set(instance, gson.fromJson(config.getAsJsonObject(clazz.getSimpleName()).get(field.getName()), field.getType()));
		} catch (IllegalAccessException e)
		{
			e.printStackTrace();
		}
	}

	private void loadFieldToJson(Field field, Object instance)
	{

		try
		{
			field.setAccessible(true);
			final Class<?> clazz = instance.getClass();
			JsonObject classObject = config.get(clazz.getSimpleName()).getAsJsonObject();
			classObject.add(field.getName(), gson.toJsonTree(field.get(instance), field.getType()));

			/*if (field.isAnnotationPresent(PostSaveProcess.class))
			{
				final PostSaveProcess annotation = field.getAnnotation(PostSaveProcess.class);
//				annotation.method().run();
			}*/

		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}


	private void applyStream(Consumer<Field> consumer, Object instance)
	{
		final Class<?> clazz = instance.getClass();
		Arrays.stream(clazz.getDeclaredFields())
		      .filter(field -> field.isAnnotationPresent(ConfigOption.class))
		      .filter(field -> config.has(field.getDeclaringClass().getSimpleName()))
		      .forEach(consumer);
	}

	public static CopyOnWriteArrayList<Pair<Field, Object>> getAllFields()
	{
		return INSTANCES;
	}

}
