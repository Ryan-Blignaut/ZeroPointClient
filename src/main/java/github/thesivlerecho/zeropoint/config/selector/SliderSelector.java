package github.thesivlerecho.zeropoint.config.selector;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
public @interface SliderSelector
{
	int min() default 0;

	int max() default 100;

}
