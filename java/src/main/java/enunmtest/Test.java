package enunmtest;

import java.util.List;

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
    //泛型方法
    //举例：获取表中一共有多少条记录？获取最大的员工入职时间？
    public <E> E getValue(E e){

        return null;
    }
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

    @org.junit.Test
    public void test3() {
        Test test = new Test();
        Integer value = test.getValue(1);
    }
}
