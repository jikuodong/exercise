package jdk8.optionaltest;

/**
 * @projectName: java
 * @package: jdk8.optionaltest
 * @className: Girl
 * @author: JKD
 * @description:
 * @date: 2020/5/28 14:23
 * @version: 1.0
 */
public class Girl {
     private String name;

    public Girl() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Girl{" +
                "name='" + name + '\'' +
                '}';
    }
}
