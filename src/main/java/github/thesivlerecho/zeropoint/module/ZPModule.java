package github.thesivlerecho.zeropoint.module;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ZPModule
{
	String name();

	ModCategory category();

	boolean shouldDraw() default true;

	boolean showToggleMsg() default false;

	boolean active() default false;
	//	int keyBinding = 0;


}
