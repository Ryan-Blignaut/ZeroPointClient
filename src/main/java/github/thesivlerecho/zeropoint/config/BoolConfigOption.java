package github.thesivlerecho.zeropoint.config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static github.thesivlerecho.zeropoint.config.SettingCategory.MISC;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface BoolConfigOption
{
	String name() default "";

	SettingCategory category() default MISC;
}