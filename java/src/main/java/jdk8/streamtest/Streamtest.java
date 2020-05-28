package jdk8.streamtest;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @projectName: java
 * @package: jdk8.streamtest
 * @className: Streamtest
 * @author: JKD
 * @description: stream API的测试
 * @date: 2020/5/27 15:03
 * @version: 1.0
 */
public class Streamtest {

    @Test
    public void test1() {
        List<String> list = Arrays.asList("aa","bb","cc","dd");
        list.stream().map(s -> s.toUpperCase()).forEach(str1 ->System.out.println(str1));
    }

}
