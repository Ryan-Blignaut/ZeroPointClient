package github.thesivlerecho.zeropoint.config;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.internal.Streams;
import com.google.gson.stream.JsonWriter;
import net.minecraft.client.MinecraftClient;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.lang.reflect.Field;

public class Config2
{
	private static final File configFile;

	public static BooleanValue harvestSugarCane, goToItem, sidebarNumbers, sidebarEnabled, wingChroma, brightnessEnabled, customCrosshairEnabled, perspective, nikHider, hideSkins, useRealSkinForSelf;

	/*private static boolean enabled;
	private static int num;
	private static boolean harvestSugarCane;
	private static boolean goToItem;
	private static int harvestSugarCaneRange;
	private static boolean tracers;
	@IntConfigValue private static int glintRed, glintGreen, glintBlue;
	@IntConfigValue private static int sidebarRed, sidebarGreen, sidebarBlue, sidebarAlpha;
	@IntConfigValue(minValue = -500, maxValue = 200, defaultValue = 0) private static int sidebarXOffset, sidebarYOffset;
	@FloatConfigValue(minValue = 0.5F, maxValue = 3F, defaultValue = 0.8F) private static float sidebarSize;
	@BooleanConfigValue private static boolean sidebarNumbers, sidebarEnabled;
	@BooleanConfigValue private static boolean wingChroma;
	@IntConfigValue private static int wingRed, wingBlue, wingGreen;
	@BooleanConfigValue private static boolean potsGlow;
	public static BooleanValue booleanValue = new BooleanValue("test");

	@BooleanConfigValue private static boolean brightnessEnabled;
	@FloatConfigValue private static float previousBrightness;

	@BooleanConfigValue public static boolean customCrosshairEnabled;

	@IntConfigValue(minValue = -500, maxValue = 200, defaultValue = 0) private static int menuXOffset, menuYOffset;
*/
	static
	{
		configFile = new File(MinecraftClient.getInstance().runDirectory, "Config" + File.separator + "Tweaks2.json");
	}

	public static void loadConfig()
	{
		try
		{
			configFile.getParentFile().mkdirs();
			String content = configFile.exists() ? FileUtils.readFileToString(configFile, "utf-8") : "{}";
			JsonElement jsonElement = (new JsonParser().parse(content));
			JsonObject jsonObject = jsonElement.getAsJsonObject();

			for (Field declaredField : Config2.class.getDeclaredFields())
			{
				if (declaredField.getType() == BooleanValue.class)
				{
					boolean x = declaredField.get(BooleanValue.class) != null;

					String name = parseName(declaredField.getName());
					if (x)
					{
						boolean on = ((BooleanValue) declaredField.get(BooleanValue.class)).isOn();
						declaredField.set(declaredField,
								new BooleanValue(jsonObject.has(name) ? jsonObject.get(name).getAsBoolean() : on).setName(name));
					} else
						declaredField.set(declaredField, new BooleanValue(jsonObject.has(name) && jsonObject.get(name).getAsBoolean()).setName(name));
				}
			}

		} catch (IllegalAccessException | IOException e)
		{
			e.printStackTrace();
		}
		updateConfig();

	}

	public static void updateConfig()
	{
		JsonObject jsonObject = new JsonObject();

		for (Field declaredField : Config2.class.getDeclaredFields())
			if (declaredField.getType() == BooleanValue.class)
			{
				try
				{
					BooleanValue booleanValue = (BooleanValue) declaredField.get(BooleanValue.class);
					jsonObject.addProperty(parseName(booleanValue.getName()), booleanValue.isOn());
				} catch (IllegalAccessException e)
				{
					e.printStackTrace();
				}
			}

		if (configFile.exists())
		{
			configFile.delete();
		}

		PrintWriter writer;
		try
		{
			writer = new PrintWriter(configFile);
			writer.print(objectToString(jsonObject));
			writer.close();
		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}

	}

	private static String objectToString(JsonObject object)
	{
		try
		{
			StringWriter stringWriter = new StringWriter();
			JsonWriter jsonWriter = new JsonWriter(stringWriter);
			jsonWriter.setLenient(true);
			jsonWriter.setIndent("\t");
			Streams.write(object, jsonWriter);
			return stringWriter.toString();
		} catch (IOException var3)
		{
			throw new AssertionError(var3);
		}
	}

	private static String parseName(String s)
	{
		return Character.toUpperCase(s.charAt(0)) + s.substring(1).replaceAll("(?<!_)(?=[A-Z])", " ");
	}

}


