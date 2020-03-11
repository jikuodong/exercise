package traversal;

import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @projectName: java
 * @package: traversal
 * @className: TraverseMap
 * @author: JKD
 * @description: Map 集合 遍历 五种方式（包含 Lambda 表达式遍历）
 * @date: 2020/3/10 17:06
 * @version: 1.0
 */
public class TraverseMap {

    public static void main(String[] args) {
        Map<String,String> map = new HashMap<>();
        map.put("key1", "value1");
        map.put("key2", "value2");
        map.put("key3", "value3");

        // 第一种遍历方式
        System.out.println("第一种遍历方式：通过遍历Map的 keySet，遍历Key 和 Value");
        for (String key: map.keySet()) {
            System.out.println("Key:" + key + "，Value:" + map.get(key));
        }

        // 第二种遍历方式(如果在遍历过程中，有删除某些Key-Value的需求，可以使用这种遍历方式)
        System.out.println("\n第二种遍历方式：通过Iterator 迭代器遍历 Key 和 Value");
        /*
        获得map的迭代器，用作遍历map中的每一个键值对
        Iterator是迭代器，map之前应该定义过，姑且认为是HashMap。
        <Entry<String,String>>表示map中的键值对都是String类型的。
        map.entrySet()是把HashMap类型的数据转换成集合类型
        map.entrySet().iterator()是去获得这个集合的迭代器，保存在iterator里面。。
        */
        Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> entry = iterator.next();
            System.out.println("Key:" + entry.getKey() +  ",Value:" + entry.getValue());
        }

        // 第三种遍历方式（推荐,尤其是容量大的）
        System.out.println("\n第三种遍历方式：通过遍历Map的entrySet,遍历Key的Value");
        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println("Key:" + entry.getValue() + ",Value:"+ entry.getValue());
        }

        // 第四种遍历方式
        System.out.println("\n第四种遍历方式： 通过遍历Map的values, 只能遍历Values, 获取不到对应的Key");
        for (String value: map.values()) {
            System.out.println("Value:" + value);
        }

        // 第五种遍历方式(JDK1.8支持的Lambda表达式，强烈推荐)

        System.out.println("\n第五种遍历方式：通过Lambda表示式，遍历Key 和 Value");
        map.forEach((key, value) -> {
            System.out.println("Key:" + key + ", Value:" + value);
        });
    }
}
