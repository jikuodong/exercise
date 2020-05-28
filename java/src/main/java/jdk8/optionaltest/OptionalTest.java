package jdk8.optionaltest;

import org.junit.Test;

import java.util.Optional;

/**
 * @projectName: java
 * @package: jdk8.optionaltest
 * @className: OptionalTest
 * @author: JKD
 * @description: 测试类
 * @date: 2020/5/28 14:18
 * @version: 1.0
 */
public class OptionalTest {

    @Test
    public void test() {
        Girl girl = new Girl();
        Optional<Girl> girl1 = Optional.of(girl);
    }

}
