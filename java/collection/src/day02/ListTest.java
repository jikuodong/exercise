package day02;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 面试题：ArrayList、LinkedList、Vector三者的异同？
 *
 * 同： 三个类都是实现了List接口，存储数据的特点相同：存储有序的、可重复的数据
 * 不同：
 *
 */
public class ListTest {

    // List接口中的常用方法
    @Test
    public void test1() {
        ArrayList list = new ArrayList();
        list.add(123);
        list.add(456);
        list.add("AA");
        list.add(new Person("Tom", 12));
        list.add(456);
        System.out.println(list);
        // 在index位置插入ele位置
        list.add(1, "BB");
        System.out.println(list);

        // addAll(int index, Collection eles): 从index位置开始将eles中的所有元素添加到集合中
        List<Integer> list1 = Arrays.asList(1, 2, 3);
        list.addAll(list1);
        System.out.println(list.size());

    }


}
