/*
 * Copyright 2021 Ryan Blignaut
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package github.thesivlerecho.zeropoint.module;


import github.thesivlerecho.zeropoint.config.Config;
import github.thesivlerecho.zeropoint.event.EventManager;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;

public class ModuleManager
{

	private static final CopyOnWriteArrayList<BaseModule> MODULES = new CopyOnWriteArrayList<>();

	public static void registerModules(String classPath, String directory)
	{
		getClasses(classPath, directory);

	}


	private static void getClasses(String packageName, String directory)
	{
		try
		{
			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			assert classLoader != null;
			final String realPath = packageName + "." + directory;
			String path = realPath.replace('.', '/');
			final Enumeration<URL> resources = classLoader.getResources(path);
			List<File> dirs = new ArrayList<>();
			resources.asIterator().forEachRemaining(url -> dirs.add(new File(url.getFile())));
			filterFiles(packageName, classLoader, dirs);
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	private static final ArrayList<String> FILES = new ArrayList<>();

	private static void filterFiles(String packageName, ClassLoader classLoader, List<File> dirs)
	{
		dirs.forEach(file -> recursiveRetrieveFiles(packageName, file));
		FILES.forEach(file -> register(classLoader, file));
	}

	private static void register(ClassLoader classLoader, String file)
	{
		try
		{
			final Class<?> clazz = Class.forName(file, true, classLoader);
			if (clazz.getSuperclass() == (BaseModule.class))
			{
				Constructor<? extends BaseModule> constructor = clazz.asSubclass(BaseModule.class).getConstructor();
				final BaseModule instance = constructor.newInstance();
				MODULES.add(instance);
				EventManager.register(instance);
				//get all fields in class if class contains an @SettingAnnotation then we can register it and then save
				Config.CLIENT_INSTANCE.register(instance);
			}


		} catch (ClassNotFoundException | InvocationTargetException | NoSuchMethodException | InstantiationException | IllegalAccessException e)
		{
			e.printStackTrace();
		}
	}

	private static void recursiveRetrieveFiles(String packageName, File file)
	{
		if (file.isDirectory())
			for (File listFile : Objects.requireNonNull(file.listFiles()))
				recursiveRetrieveFiles(packageName + "." + file.getName(), listFile);
		else
		{

			final String fileNoClassExt = file.getName().substring(0, file.getName().length() - 6);
			FILES.add(packageName + "." + fileNoClassExt);
		}
	}


}
