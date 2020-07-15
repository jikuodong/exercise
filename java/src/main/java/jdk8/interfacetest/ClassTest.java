package jdk8.interfacetest;

/**
 * @projectName: java
 * @package: jdk8.interfacetest
 * @className: ClassTest
 * @author: JKD
 * @description:
 * @date: 2020/7/7 16:25
 * @version: 1.0
 */
public class ClassTest {

    public static void main(String[] args) {
        SubClass subClass = new SubClass();
        InterFaceTest.method1();
    }
}
class SubClass implements InterFaceTest{

}
