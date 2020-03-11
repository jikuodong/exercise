package traversal;

import java.util.Arrays;

/**
 * @projectName: java
 * @package: traversal
 * @className: AlarmGrade
 * @author: JKD
 * @description: Enum枚举 遍历判断 四种方式
 * @date: 2020/3/11 10:01
 * @version: 1.0
 */
public enum AlarmGrade {

    ATTENTION("attention", "提示"),
    WARNING("warning", "警告"),
    SERIOUS("serious", "严重"),
    FAULT("fault", "故障"),
    UNKNOWN("unknown", "未知");

    // 键
    private String key;
    // 值
    private String name;

    /**
     *
     * @param key 键
     * @param name 值
     */
    AlarmGrade(String key, String name) {
        this.key = key;
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public String getName() {
        return name;
    }

    /**
     * 根据Key得到枚举的Value
     * 普通for循环遍历，比较判断
     *
     * @param key
     * @return
     */
    public static AlarmGrade getEnumType(String key) {
        AlarmGrade[] alarmGrades = AlarmGrade.values();
        for (int i = 0; i < alarmGrades.length; i++) {
            if (alarmGrades[i].getKey().equals(key)) {
                return alarmGrades[i];
            }
        }
        return AlarmGrade.UNKNOWN;
    }


    /**
     *
     *  根据key得到枚举的Value
     *  增强for循环遍历，比较判断
     * @param key 键
     * @return
     */
    public static AlarmGrade getEnumType1(String key){
        AlarmGrade[] alarmGrades = AlarmGrade.values();
        for (AlarmGrade alarmGrade: alarmGrades) {
            if (alarmGrade.getKey().equals(key)){
                return  alarmGrade;
            }
        }
        return AlarmGrade.UNKNOWN;
    }

    /**
     *  根据key得到枚举的Value
     *  Lambda表达式，比较判断（JDK1.8）
     * @param key
     * @return
     */
    public static AlarmGrade getEnumType2(String key) {
        AlarmGrade[] alarmGrades = AlarmGrade.values();
        AlarmGrade result = Arrays.asList(alarmGrades).stream().filter(alarmGrade -> alarmGrade.getKey().equals(key)).findFirst().orElse(AlarmGrade.UNKNOWN);
        return result;
    }

    /**
     *  根据key得到枚举的Value
     *  Lambda表达式，比较判断（JDK1.8）
     * @param key
     * @return
     */
    public static AlarmGrade getEnumType3(String key) {
        return Arrays.asList(AlarmGrade.values()).stream().filter(alarmGrade -> alarmGrade.getKey().equals(key)).findFirst().orElse(AlarmGrade.UNKNOWN);
    }

    public static void main(String[] args) {
        String grade = "attention";
        System.out.println("第一种方式：普通for循环遍历，比较判断 \n" + grade + ": " + AlarmGrade.getEnumType(grade).getName());
        System.out.println("\n第二种方式：增强for循环遍历，比较判断 \n" + grade + ": " + AlarmGrade.getEnumType1(grade).getName());
        System.out.println("\n第三种方式：Lambda表达式，比较判断 \n" + grade + ": " + AlarmGrade.getEnumType2(grade).getName());
        System.out.println("\n第四种方式：Lambda表达式，比较判断 \n" + grade + ": " + AlarmGrade.getEnumType3(grade).getName());
    }
}
