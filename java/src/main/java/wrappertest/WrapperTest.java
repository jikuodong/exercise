package wrappertest;

import org.junit.Test;

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
