package enunmtest;

/**
 * @projectName: java
 * @package: enunmtest
 * @className: SeasonTest1
 * @author: JKD
 * @description: 使用enum关键字定义枚举类
 * @date: 2020/5/19 10:49
 * @version: 1.0
 */
public class SeasonTest1 {
    public static void main(String[] args) {
        Season1 summer = Season1.SUMMER;
        // toString
        System.out.println(summer.toString());
        System.out.println();
        // values
        Season1[] values = Season1.values();
        for (int i = 0; i < values.length; i++) {
            System.out.println(values[i]);
        }
        System.out.println();
        // valueOf(String objName): 返回枚举类中对象名是objName 的对象。
        Season1 season1 = Season1.valueOf("SPRING");
        System.out.println(season1);
        season1.show();

    }

}

interface  Info{
    void show();
}

enum Season1 implements Info{
    // 1.提供当前枚举类的对象，多个对象之间用“,”隔开，末尾对象";"结束
    SPRING("春天","春暖花开"){
        // 方式二： 让每一个枚举类对象实现接口的方法
        @Override
        public void show() {

        }
    },
    SUMMER("夏天","夏日炎炎"){
        @Override
        public void show() {

        }
    },
    AUTUMN("秋天","秋高气爽"){
        @Override
        public void show() {

        }
    },
    WINTER("冬天天","冰天雪地"){
        @Override
        public void show() {

        }
    };

    private final  String seasonName;
    private final  String seasonDesc;

    // 私有化构造器
   Season1(String seasonName, String seasonDesc) {
        this.seasonName = seasonName;
        this.seasonDesc = seasonDesc;
    }

    public String getSeasonName() {
        return seasonName;
    }

    public String getSeasonDesc() {
        return seasonDesc;
    }

//   方式1
//    @Override
//    public void show() {
//        System.out.println("这是一个季节");
//    }
}
