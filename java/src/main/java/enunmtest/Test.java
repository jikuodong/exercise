package enunmtest;

/**
 * @projectName: java
 * @package: enunmtest
 * @className: Test
 * @author: JKD
 * @description: 测试
 * @date: 2020/5/28 13:52
 * @version: 1.0
 */
public class Test {

    @org.junit.Test
    public void test1(){
        Strategy strategy = Strategy.valueOf("FAST");
        System.out.println(strategy.run());
    }
    @org.junit.Test
    public void test2(){
        EnumSingleton singleton1 = EnumSingleton.getInstance();
        EnumSingleton singleton2 = EnumSingleton.getInstance();
        System.out.println(singleton1);
        System.out.println(singleton2);
        System.out.println(singleton1==singleton2);
    }

}
