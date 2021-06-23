package github.thesivlerecho.zeropoint.util;

import github.thesivlerecho.zeropoint.event.BaseEvent;
import github.thesivlerecho.zeropoint.event.MethodData;

import java.lang.reflect.InvocationTargetException;

public class ReflectionUtil
{
	public static void invokeMethod(MethodData method, BaseEvent baseEvent)
	{
		try
		{
			method.target().invoke(method.source(), baseEvent);
		} catch (IllegalAccessException | InvocationTargetException e)
		{
			e.printStackTrace();
		}
	}


}
