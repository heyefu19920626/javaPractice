package disorder;

/**
 * Description:
 * 代码块相关.
 * 执行顺序：静态代码块 > 构造代码块 > 构造函数 > 代码块
 *
 * @author heyefu
 * Create in: 2019-05-23
 * Time: 16:15
 **/
public class CodeBlock {

    private String name;

    private int age;

    //    静态代码块
    static {
        System.out.println("Static block init...");
    }

    //    构造代码块
    {
        System.out.println("Structure block init...");
        name = "CodeBlock";
    }

    private CodeBlock(int age) {
        this.age = age;
    }

    private void say() {
        System.out.println("Name: " + name + ", Age: " + age);
        //        普通代码块,顺序执行，感觉没必要
        {
            System.out.println("Block init...");
        }
    }


    public static void main(String[] args) {
        System.out.println("New object...");
        CodeBlock test = new CodeBlock(15);
        System.out.println("Execute function say...");
        test.say();
        test = new CodeBlock(19);
        System.out.println("New object...");
        System.out.println("Execute function say...");
        test.say();

    }
}
