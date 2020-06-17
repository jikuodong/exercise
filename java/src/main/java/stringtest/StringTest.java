package stringtest;

import org.junit.Test;

/**
 * @projectName: java
 * @package: stringtest
 * @className: StringTest
 * @author: JKD
 * @description: String的使用
 * @version: 1.0
 */
public class StringTest {


    /**
     *
     */
    @Test
    public void test1() {
        String s1 = "abc";
        String s2 = "abc";
//        s1 = "hello";
        System.out.println(s1 == s2);
        // hello
        System.out.println(s1);
        // abc
        System.out.println(s2);
    }
}
