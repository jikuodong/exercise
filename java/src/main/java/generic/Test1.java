package generic;

import org.junit.Test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @projectName: java
 * @package: generic
 * @className: Test1
 * @author: JKD
 * @description: 测试类
 * @date: 2020/6/1 9:24
 * @version: 1.0
 */
public class Test1 {

    @Test
    public void test1() {
        // HashMap 为例
        Map<String, Integer> map = new HashMap<String, Integer>();

        map.put("Tom", 87);
        map.put("Tom1", 77);
        map.put("Tom2", 67);
        // 泛型的嵌套
        Set<Map.Entry<String, Integer>> entries = map.entrySet();
        Iterator<Map.Entry<String, Integer>> iterator = entries.iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Integer> next = iterator.next();
            String key = next.getKey();
            Integer value = next.getValue();
            System.out.println(key+"------" + value);

        }
    }
}
