package wrappertest;

import org.junit.Test;

import java.util.Scanner;
import java.util.Vector;

/**
 * @projectName: java
 * @package: wrappertest
 * @className: WrapperTest
 * @author: JKD
 * @description: 包装类
 * @date: 2020/7/6 13:39
 * @version: 1.0
 */
public class WrapperTest {
    public static void main(String[] args) {
        for (int i = 0; i < args.length; i++) {
            System.out.println("******" + args[i]);
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("数据数据：");
        System.out.println(scanner.next());
    }

    @Test
    public void test6() {
        Object o1 = true ? new Integer(1) : new Double(2.0);
        System.out.println(o1);
        Vector s = new Vector<>();
        s.addElement(1);
        for (int i = 0; i < s.size() ; i++) {
            Object obj = s.elementAt(i);
            System.out.println(obj);
        }
    }

    // String类型----> 基本数据类型、包装类: 调用包装类的parseXxx()
    @Test
    public  void test5() {
        String str1 = "123";
        int num2 = Integer.parseInt(str1);
        System.out.println(num2 + 1);
    }

    // 基本数据类型、包装类--->String 类型
    @Test
    public void test4() {
        int num1 = 10;
        // 方式1：连接运算
        String str1 = num1 + "";
        // 方式2： 调用String的valueOf(Xxx xxx)
        float f1 = 12.3f;
        String str2 = String.valueOf(f1);
    }

    @Test
    public void test3() {
        // 自动装箱
        int num2 = 10;
        Integer in1 = num2;

        // 自动拆箱
        int num3 = in1;
    }


    // 基本数据类型-----> 包装类：调用包装类的构造器
    @Test
    public  void test1() {
        int num1 = 10;
        Integer  int1 = new Integer(num1);
        System.out.println(int1.toString());
    }
    // 包装类转换为基本数据类型: 调用包装类的xxxValue()
    @Test
    public void test2() {
        Integer in1 = new Integer(12);
        int i1 = in1.intValue();
        System.out.println(i1 + 1);
    }
}
