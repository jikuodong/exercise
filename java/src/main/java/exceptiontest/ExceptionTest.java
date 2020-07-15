package exceptiontest;

import org.junit.Test;

import java.util.Date;
import java.util.Scanner;

/**
 * 一、 异常的体系结构
 * java.lang.Throwable
 *      |---------java.lang.Error: 一般不便携针对性的代码进行处理。
 *      |---------java.lang.Exception: 可以进行异常的处理
 *          |---------编译时异常（checked）
 *              |---------IOException
 *          |---------运行时异常（unchecked）
 *
 *
 *
 *
 *
 */
public class ExceptionTest {
    @Test
    public void test6() {
        // ArithmeticException 算数运算异常
        int a = 10;
        int b = 0;
        System.out.println(a/b);
    }
    /**
     * @description
     *
     * @method test5
     * @author JKD
     * @return void
     * @date 2020/7/10 9:09
     */
    @Test
    public void test5() {
        // InputMismatchException
        Scanner scanner = new Scanner(System.in);
        int score = scanner.nextInt();
        System.out.println(score);
    }

    @Test
    public void test4() {
        // NumberFormatException
        String str = "123";
        str = "abc";
        int a = Integer.parseInt(str);
        System.out.println(a);
    }

    @Test
    public void test3() {
        // ClassCastException 类型转换异常
        Object object = new Date();
        String str = (String) object;
        System.out.println(str);
    }

    @Test
    public void test2() {
        // ArrayIndexOutOfBoundsException 数组角标越界
        int[] arr= new int[10];
        System.out.println(arr[10]);
    }

    @Test
    public void test1() {
        // NullPointerException(空指针异常)
//        int[] arr = null;
//        System.out.println(arr[3]);
        String str = "abc";
        str = null;
        System.out.println(str.charAt(0));
    }
}
