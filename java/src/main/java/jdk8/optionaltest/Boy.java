package jdk8.optionaltest;

/**
 * @projectName: java
 * @package: jdk8.optionaltest
 * @className: Boy
 * @author: JKD
 * @description:
 * @date: 2020/5/28 14:22
 * @version: 1.0
 */
public class Boy {
    private Girl girl;

    public Boy() {
    }

    public Girl getGirl() {
        return girl;
    }

    public void setGirl(Girl girl) {
        this.girl = girl;
    }

    @Override
    public String toString() {
        return "Boy{" +
                "girl=" + girl +
                '}';
    }
}
