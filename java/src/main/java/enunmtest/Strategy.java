package enunmtest;

/**
 * @projectName: java
 * @package: enunmtest
 * @className: Strategy
 * @author: JKD
 * @description: 优化if else
 * @date: 2020/5/28 13:50
 * @version: 1.0
 */
public enum Strategy {

    FAST{
        @Override
        String run() {
            System.out.println("FAST");
            return "FAST";
        }
    },
    NORMAL{
        @Override
        String run() {
            System.out.println("NORMAL");
            return "NORMAL";
        }
    },
    SMOOTH{
        @Override
        String run() {
            System.out.println("SMOOTH");
            return "SMOOTH";
        }
    };

    abstract String run();
}
