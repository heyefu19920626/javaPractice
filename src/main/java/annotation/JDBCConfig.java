package annotation;

import java.lang.annotation.*;

/**
 * java自定义注解的使用.
 *
 * @Auther: heyefu
 * @Date: 2018/8/1 11:16
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface JDBCConfig {
    String ip() default "127.0.0.1";
}
