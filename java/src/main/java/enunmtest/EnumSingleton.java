package enunmtest;

/**
 * @projectName: java
 * @package: enunmtest
 * @className: EnumSingleton
 * @author: JKD
 * @description:
 * @date: 2020/6/2 17:06
 * @version: 1.0
 */
public class EnumSingleton {

    public static EnumSingleton getInstance() {
        return Enum.INSTANCE.getSingleton();
    }

    private enum Enum {
        /**
         * 实例
         */
        INSTANCE;
        private EnumSingleton singleton;

        /**
         * 关键， 使用枚举类的构造方法创建对象实例
         */
        Enum() {
            singleton = new EnumSingleton();
        }

        private EnumSingleton getSingleton() {
            return singleton;
        }
    }


}
