package traversal;

import java.sql.SQLOutput;
import java.util.HashMap;
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
            System.out.println("Key:" + key + "");
        }
    }
}
