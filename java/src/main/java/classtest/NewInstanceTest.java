package classtest;

import org.junit.Test;

import java.lang.reflect.Field;

/**
 * @projectName: java
 * @package: classtest
 * @className: NewInstanceTest
 * @author: JKD
 * @description: 通过发射创建对应的运行时类的对象
 * @date: 2020/5/22 10:17
 * @version: 1.0
 */
public class NewInstanceTest {

    @Test
    public void test1() throws IllegalAccessException, InstantiationException {
        Class<Person> clazz = Person.class;
        Field[] fields = clazz.getDeclaredFields();
        for (Field f: fields) {
            System.out.println(f);
        }
        /*
         * newInstance(): 调用此方法，创建对应的运行时类的对象。内部调用了运行时类的空参构造器
         */
        Person obj = clazz.newInstance();
        System.out.println(obj);
    }
}
