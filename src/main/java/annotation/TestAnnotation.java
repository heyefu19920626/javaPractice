package annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 测试注解并解析.
 *
 * @author heyefu
 * @date 2018/8/1 14:15
 */
@JDBCConfig
public class TestAnnotation {

    @Mytarget("9")
    private
    String ip;

    @Mytarget(value = "mytarget:dosomething")
    private void doSomething() {

        System.out.println("I am doing something.");
    }

    public static void main(String[] args) throws NoSuchFieldException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        Field ip = TestAnnotation.class.getDeclaredField("ip");
        ip.setAccessible(true);
        if (ip.getAnnotation(Mytarget.class) != null) {

            System.out.println(ip + "使用了Mytarget注解!");
            System.out.println(ip.getAnnotation(Mytarget.class).value());
        }

        Method dosomething = TestAnnotation.class.getDeclaredMethod("doSomething");

        if (dosomething.getAnnotation(Mytarget.class) != null) {
            System.out.println(dosomething.getAnnotation(Mytarget.class).value());
        }

        Annotation[] anns = dosomething.getAnnotations();
        if (anns != null) {
            for (Annotation ann : anns) {
                System.out.println(ann.annotationType().getSimpleName());
            }
        }

        Method method = TestAnnotation.class.getDeclaredMethod("doSomething");

        if (method.isAnnotationPresent(Mytarget.class)) {
            System.out.println(method.getAnnotation(Mytarget.class));
            method.invoke(new TestAnnotation());
        }

    }
}
