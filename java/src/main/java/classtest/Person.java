package classtest;

/**
 * @projectName: java
 * @package: classtest
 * @className: Person
 * @author: JKD
 * @description:
 * @date: 2020/5/22 10:19
 * @version: 1.0
 */
public class Person {
    private String name;
    private int age;

    public Person() {
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
