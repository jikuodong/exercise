package traversal;

import java.util.Arrays;

/**
 * @projectName: java
 * @package: traversal
 * @className: ArrayLambda
 * @author: JKD
 * @description: Array数组 遍历 四种方式
 * @date: 2020/3/11 9:41
 * @version: 1.0
 */
public class ArrayLambda {
    public static void main(String[] args) {
        Integer[] items = {1,2,3};

        // 普通for循环遍历
        System.out.println("第一种方式：普通for循环遍历Array数组");
        for (int i = 0; i < items.length; i++) {
            System.out.println(items[i]);
        }

        // 增强for循环遍历
        System.out.println("\n第二种方式：增强for循环遍历 Array 数组" );
        for ( Integer item : items) {
            System.out.println(item);
        }

        // Lambda 表达式遍历（JDK1.8）
        System.out.println("\n第三种方式：Lambda表达式遍历 Array 数组");
        Arrays.asList(items).forEach(item -> System.out.println(item));

        // Lambda 表达式遍历(JDK 1.8)
        System.out.println("\n第四种方式：Lambda 表达式遍历 Array 数组");
        Arrays.asList(items).forEach(System.out::println);
    }
}
