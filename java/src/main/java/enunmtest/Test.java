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

}
