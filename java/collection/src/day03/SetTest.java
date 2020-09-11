package day03;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * Set： 存储无序的、不可重复的数据。
 * 1、无序性：不等于随机性Set。
 * 以HashSet为例说明：存储的数据在底层数组中并非按照数组索引的顺序添加的，而是根据数据的哈希值决定的
 *
 * 2、不可重复性：
 * 保证添加的元素按照equals()判断时，不能返回true,即：相同的元素只能添加一个。
 *
 *
 */
public class SetTest {

    @Test
    public void test1() {
        Set set = new HashSet();
    }
}
