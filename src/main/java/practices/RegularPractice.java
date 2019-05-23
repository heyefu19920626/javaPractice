package practices;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式练习.
 * <p>
 * 正则表达式的分组与断言
 *
 * @Auther: heyefu
 * @Date: 2018/7/31 16:38
 */
public class RegularPractice implements Serializable {

    private static final long serialVersionUID = -487997139393301189L;

    public static void main(String[] args) {

        RegularPractice regularPractice = new RegularPractice();

        String content = "src: local('Open Sans Light'), local('OpenSans-Light'), url(http://fonts.gstatic.com/s/opensans/v13/DXI1ORHCpsQm3Vp6mXoaTa-j2U0lmluP9RWlSytm3ho.woff2) format('woff2')";
//        (?<=\\().*?(?=\\))
/*      为什么需要两个转义
        第一个转义是编译器的,为了显示\
        第二个转义是正则表达式的，为了显示(
        */
        Pattern pattern = Pattern.compile("(?<=\\()[^\\)]+");
/*      分组和后向引用
        分组用()表示
        作用有2:
            1. 将某些规律看成是一组，然后进行组级别的重复
            2. 分组之后，可以通过后向引用简化表达式
        整个正则为第0组
        从左至右括号内的分别为第1,2,3...组
        后向引用引用的仅仅是文本内容，而不是正则表达式（引用的是匹配成功后的内容，是结果，而不是表达式）
        \\1表示引用的local*/
        Pattern pattern1 = Pattern.compile("(local)(?<result>.*)(?=\\1)");
/*      零宽度断言
        断言:
            就是指明某个字符串前边或者后边，将会出现满足某种规律的字符串
        (?=exp) 零宽度正先行断言 表示匹配表达式exp前面的位置 即仅当子表达式exp在此位置的右侧匹配时才继续匹配
        (?<=exp) 零宽度正后发断言 表示匹配表达式exp后面的位置
        (?!exp) 零宽度负先行断言 仅当子表达式exp不在此位置的右侧匹配时才继续匹配
        (?<!exp) 零宽度负后发断言
        问号就是在说这是一个非捕获组，这个组没有编号，不能用来后向引用，只能当做断言
        */

        regularPractice.printResult(pattern, content);
        regularPractice.printResult(pattern1, content);

        Matcher matcher = pattern1.matcher(content);
        if (matcher.find()) {
            System.out.println(matcher.group("result"));
        }

    }

    /**
     * 用给定的正则表达式匹配字符串，打印出符合的字符串.
     *
     * @param pattern 正则表达式
     * @param content 需要匹配的字符串
     * @return void
     **/
    public void printResult(Pattern pattern, String content) {
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            System.out.println(matcher.group());
        }
    }


}
