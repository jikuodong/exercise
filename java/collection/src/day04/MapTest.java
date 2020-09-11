package day04;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

/**
 *
 *
 */
public class MapTest {


    @Test
    public void test5() throws IOException {
        Properties properties = new Properties();
        FileInputStream fileInputStream = new FileInputStream("E:\\gitHub\\exercise\\java\\jdbc.properties");
        properties.load(fileInputStream); // 加载流对应的文件
        String name = properties.getProperty("name");
        String password = properties.getProperty("password");
        System.out.println(name);
        System.out.println(password);
    }

    public static void main(String[] args) throws IOException {
        Properties properties = new Properties();
        FileInputStream fileInputStream = new FileInputStream("jdbc.properties");
        properties.load(fileInputStream); // 加载流对应的文件
        String name = properties.getProperty("name");
        String password = properties.getProperty("password");
        System.out.println(name);
        System.out.println(password);
    }
    @Test
    public void test4() {
        // 遍历
        Map map = new HashMap();
        map.put("AA", 123);
        map.put(45, 1234);
        map.put("BB", 56);
        // 遍历所有的key集
        Set set = map.keySet();
        Iterator iterator = set.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }

        System.out.println();
        // 遍历所有的value: values()
        Collection values = map.values();
        for (Object obj: values) {
            System.out.println(obj);
        }

        // 遍历所有的key-value
        Set entrySet = map.entrySet();
        Iterator iterator1 = entrySet.iterator();
        while (iterator1.hasNext()) {
            Object obj = iterator1.next();

            Map.Entry entry = (Map.Entry) obj;
            System.out.println(entry.getKey() + "---------" + entry.getValue());
        }

    }

    @Test
    public void test3() {
        Map map = new HashMap();
        map.put("AA", 123);
        map.put(45, 123);
        map.put("BB", 56);

        System.out.println(map.get("AA"));
    }

    @Test
    public void test2() {
        Map map = new HashMap();
        // 新增
        map.put("AA", 123);
        map.put(45, 123);
        map.put("BB", 56);
        // 修改
        map.put("AA", 87);
        System.out.println(map);

        Map map1 = new HashMap();
        map1.put("CC",123);
        map1.put("DD",123);
        map.putAll(map1);
        System.out.println(map);

        // remove(Object key)
        Object value = map.remove("CC");
        System.out.println(value);
        System.out.println(map);

        // clear()
        map.clear();
        System.out.println(map);
    }

    @Test
    public void test1(){
        Map map = new HashMap();
//        map = new LinkedHashMap();
        map.put(123, "AA");
        map.put(456, "BB");
        map.put(789, "CC");
        System.out.println(map);

    }
}
