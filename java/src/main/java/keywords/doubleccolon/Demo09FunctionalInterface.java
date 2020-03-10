package keywords.doubleccolon;

import java.util.ArrayList;
import java.util.List;

/**
 * @projectName: java
 * @package: keywords
 * @className: Demo09FunctionalInterface
 * @author: JKD
 * @description:
 * @date: 2020/3/10 15:44
 * @version: 1.0
 */
public class Demo09FunctionalInterface {
    // 使用自定义的函数式接口作为方法参数
    private static void doSomething(MyFunctionalInterface inter) {
        inter.myMethod(); // 调用自定义的函数式接口方法
    }

    private static void test(MyFunctionalInterface inter) {
        inter.myMethod();
    }

    public static void main(String[] args) {
        // 调用使用函数式接口的方法
        doSomething(() -> System.out.println("Lambda执行啦"));
        test(() -> System.out.println("执行test"));
        MyFunctionalInterface inter = () -> System.out.println("测试");
        inter.myMethod();
        List<String> items = new ArrayList<>();
        items.add("A");
        items.add("B");
        items.add("C");
        items.forEach(System.out::println);
    };
}
