package day01;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 一、 集合框架的概述
 * 1、集合、数组都是对多个数据进行存储操作的结构，简称Java容器。
 * 说明：此时的存储，主要指的是内存层面的存储，不涉及到持久化的存储（.txt,.jpg,.avi,数据库中）
 *
 * 2.1、数组在存储多个数据的特点：
 *      > 一旦初始化以后，其长度就确定了。
 *      > 数组一旦定义好，其元素的类型也就确定了。我们也就只能操作指定类型的数据了。
 * 2.2、 数组在存储多尴尬个数据方面的缺点：
 *          >  一旦初始化以后，其长度就不可修改了。
 *          >  数组中提供的方法非常有限，对于添加、删除、插入数据等操作，非常不便，同时效率也不高。
 *          >  获取数组中实际元素的个数的需求，数组没有现成的属性或方法可用。
 *          >  数组存储数据的特点： 有序、可重复。对于无序、不可重复的需求，不能满足。
 *
 *
 */
public class CollectionTest {

    @Test
    public void test1() {
        Collection collection = new ArrayList();
        collection.add("AA");
    }
}
