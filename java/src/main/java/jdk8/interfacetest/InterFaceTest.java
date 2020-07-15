package jdk8.interfacetest;

/**
 * @projectName: java
 * @package: jdk8.interfacetest
 * @className: InterFaceTest
 * @author: JKD
 * @description: java8接口中可以定义静态方法和默认方法
 * @date: 2020/7/7 16:21
 * @version: 1.0
 */
public interface InterFaceTest {

    // 静态方法
    public static void method1() {
        System.out.println("北京");
    }
    // 默认方法
    public default void method2() {
        System.out.println("上海");
    }
    default void method3() {
        System.out.println("深圳");
    }
}
