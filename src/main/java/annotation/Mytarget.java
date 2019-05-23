package annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @Auther: heyefu
 * @Date: 2018/8/1 14:14
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Mytarget {
    String value();
}
