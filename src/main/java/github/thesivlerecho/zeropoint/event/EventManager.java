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

package github.thesivlerecho.zeropoint.event;

import com.google.common.collect.Multimap;
import com.google.common.collect.MultimapBuilder;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class EventManager
{
	private static final Multimap<Class<?>, MethodData> EVENTS = MultimapBuilder.hashKeys().arrayListValues().build();

	public static void register(Object object)
	{
		for (Method method : object.getClass().getDeclaredMethods())
		{
			if (method.isAnnotationPresent(TargetEvent.class))
				register(method, object);
		}
	}

	private static void register(Method method, Object object)
	{
		Class<?> indexClass = method.getParameterTypes()[0];
		final MethodData data = new MethodData(object, method);
		EVENTS.get(indexClass).add(data);
	}

	public static void call(BaseEvent event)
	{
		EVENTS.get(event.getClass()).forEach(methodData ->
		{
			try
			{
				methodData.getTarget().invoke(methodData.getSource(), event);
			} catch (IllegalAccessException | InvocationTargetException e)
			{
				e.printStackTrace();
			}
		});
	}
}
