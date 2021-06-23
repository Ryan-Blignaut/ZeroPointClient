package github.thesivlerecho.zeropoint.event;

import com.google.common.collect.Multimap;
import com.google.common.collect.MultimapBuilder;
import github.thesivlerecho.zeropoint.util.ReflectionUtil;

import java.lang.reflect.Method;
import java.util.Comparator;

public class EventManager
{
	private static final Multimap<Class<?>, MethodData> EVENTS = MultimapBuilder.hashKeys().arrayListValues().build();

	public static void register(Object object)
	{
		for (Method method : object.getClass().getDeclaredMethods())
			if (method.isAnnotationPresent(EventListener.class))
				register(method, object);
	}

	private static void register(Method method, Object object)
	{
		Class<?> indexClass = method.getParameterTypes()[0];
		final MethodData data = new MethodData(object, method, method.getAnnotation(EventListener.class).priority());
		EVENTS.get(indexClass).add(data);
	}

	public static void call(BaseEvent event)
	{
		EVENTS.get(event.getClass()).stream().sorted(Comparator.comparingInt(MethodData::priority)).forEach(methodData -> ReflectionUtil.invokeMethod(methodData, event));
	}
}
