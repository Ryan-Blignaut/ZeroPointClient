/*
package github.thesivlerecho.zeropoint.gui.old.menu;

import com.mojang.datafixers.kinds.ListBox;
import github.thesivlerecho.zeropoint.config.Settings;
import github.thesivlerecho.zeropoint.gui.old.GuiHelper;
import net.minecraft.client.util.math.MatrixStack;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class MenuPage
{
	//string being setting name , SettingCategory being the setting category
//	HashMap<SettingCategory, ListBox> map1 = new HashMap<>();


	ArrayList<ListBox> listBoxes = new ArrayList<>();

	public void renderSettingsMenu(MatrixStack matrixStack, int mouseX, int mouseY, float delta, float scale)
	{


//		GuiHelper.fill(matrixStack,GL11.GL_QUADS,20,20,45,45,new Color(246, 11, 11,255).getRGB());

		Float menuXOffset = Settings.MENU_X_OFFSET;
		Float menuYOffset = Settings.MENU_Y_OFFSET;

		GL11.glLineWidth(2);
		GuiHelper.fill(matrixStack, GL11.GL_QUADS, menuXOffset, menuYOffset, menuXOffset + (550) / scale, menuYOffset + (650) / scale, new Color(32, 32, 38).getRGB());

		GuiHelper.fill(matrixStack, GL11.GL_QUADS, menuXOffset, menuYOffset, menuXOffset + (550) / scale, menuYOffset + (650) / scale, new Color(32, 32, 38).getRGB());
		GuiHelper.fill(matrixStack, GL11.GL_QUADS, menuXOffset + 10 / scale, menuYOffset + 45 / scale, menuXOffset + (550 - 12) / scale, menuYOffset + (650 - 12) / scale, new Color(22, 22, 28).getRGB());
		GuiHelper.fill(matrixStack, GL11.GL_QUADS, menuXOffset, menuYOffset + 30 / scale, menuXOffset + (549) / scale, menuYOffset + (6 + 30) / scale, */
/*new Color(73, 73, 82).getRGB()+*//*
 Color.CYAN.getRGB());
		GuiHelper.fill(matrixStack, GL11.GL_LINE_LOOP, menuXOffset, menuYOffset, menuXOffset + (550) / scale, menuYOffset + (650) / scale, new Color(27, 27, 33).getRGB());
		GuiHelper.fill(matrixStack, GL11.GL_LINE_LOOP, menuXOffset + 10 / scale, menuYOffset + 45 / scale, menuXOffset + (550 - 12) / scale, menuYOffset + (650 - 12) / scale, new Color(36, 36, 41).getRGB());

		GL11.glLineWidth(1);
		listBoxes.forEach(l -> l.setMenuXOffset(menuXOffset + 24).setMenuYOffset(menuYOffset + 24).render(matrixStack, mouseX, mouseY, delta));
	}


	public void onClickMenu(double mouseX, double mouseY)
	{
		listBoxes.forEach(listBox -> listBox.onClick(mouseX, mouseY));

	}

	public void init()
	{
		AtomicInteger x = new AtomicInteger(0);
		AtomicInteger y = new AtomicInteger(0);
		AtomicInteger counter = new AtomicInteger();

//		Arrays.stream(Settings.class.getDeclaredFields()).filter(field -> field.isAnnotationPresent(BoolConfigOption.class)).forEach(field ->
//				m.put(field.getName(), new ListBox(x.getAndAdd(24), y.getAndAdd(24))));

//		ListBox value1 = new ListBox(25, 25, SettingCategory.MISC);
//		value1.addCheckbox(new CheckBoxButton(5, 5, 5, 5, "test", Settings.FLIGHT));
//		listBoxes.add(value1);

		*/
/*Arrays.stream(Settings.class.getDeclaredFields()).filter(field -> field.isAnnotationPresent(BoolConfigOption.class)).forEach(field ->
		{
		*//*
*/
/*	SettingCategory category = field.getAnnotation(BoolConfigOption.class).category();
			String name = field.getAnnotation(BoolConfigOption.class).name();
			if (!map1.containsKey(category))
				map1.put(category, new ListBox(x.getAndAdd(24), 0, category));
			else
			{
				try
				{
					if (field.get(AtomicBoolean.class) instanceof AtomicBoolean)
						map1.get(category).addCheckbox(new CheckBoxButton(3, 3, 12, 12, name, (AtomicBoolean) field.get(AtomicBoolean.class)));
				} catch (IllegalAccessException e)
				{
					e.printStackTrace();
				}
			}*//*
*/
/*


		});*//*



//		map.forEach((s, settingCategory) ->
////		if (s == null)
////		{
////
////		}
//				listBoxes.add(new ListBox(x.getAndAdd(counter.get() == 2 ? -24 : 24), y.getAndAdd(counter.getAndSet(counter.get() == 2 ? counter.get() : 1) == 2 ? -24 : 24)));
//		);

//				m.put(field.getName(), new ListBox(x.getAndAdd(24), y.getAndAdd(24))));

	}
}
*/
