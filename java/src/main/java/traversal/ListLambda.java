package traversal;

import java.util.ArrayList;
import java.util.List;

/**
 * @projectName: java
 * @package: traversal
 * @className: ListLambda
 * @author: JKD
 * @description: List 集合遍历 四种方法
 * @date: 2020/3/10 16:18
 * @version: 1.0
 */
public class ListLambda {
    public static void main(String[] args) {
        List<String> items = new ArrayList<>();
        items.add("A");
        items.add("B");
        items.add("C");

        // 普通for循环遍历
        System.out.println("第一种遍历方式： 普通for循环遍历List 集合");
        for (int i = 0; i < items.size(); i++) {
            System.out.println(items.get(i));
        }

        // 增强for循环遍历
        System.out.println("\n第二种遍历方式： 普通for循环遍历List 集合");
        for (String item : items) {
            System.out.println(item);
        }

        // Lambda表达式遍历（JDK 1.8）
        System.out.println("\n第三种遍历方式： Lambda表达式遍历List 集合");
        items.forEach(item -> {
            System.out.println(item);
        });

        // Lambda 表示式遍历（JDK 1.8）
        System.out.println("\n 第四种遍历方式： Lambda表达式遍历List 集合");
        items.forEach(System.out::println);

        // 普通for循环遍历，判断List集合中是否包含字符串"C"
        System.out.println("\n第一种判断方式：普通for循环遍历，判断List集合中是否包含字符串C");
        for (int i = 0; i < items.size() ; i++) {
            if ("C".equals(items.get(i))) {
                System.out.println(items.get(i));
            }
        }

        // 增强for循环遍历，判断List集合中是否包含字符串"C"
        System.out.println("\n第二种判断方式：增强for循环遍历，判断List集合中是否包含字符串C");
        for ( String item: items) {
            if ("C".equals(item)) {
                System.out.println(item);
            }
        }

        // Lambda 表达式 判断List集合中是否包含字符串"C"(JDK 1.8)
        System.out.println("\n第三种判断方式：Lambda 表达式，判断List集合中是否包含字符串C");
        items.forEach(item -> {
            if ("C".equals(item)) {
                System.out.println(item);
            }
        });

        // Lambda 表达式 判断List集合中是否包含字符串"C"(JDK 1.8)
        System.out.println("\n第四种判断方式：Lambda 表示式，判断List集合中是否包含字符串C");
        items.stream().filter(s-> s.equals("C")).forEach(System.out:: println);
    }
}
