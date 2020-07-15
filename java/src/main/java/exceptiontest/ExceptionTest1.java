package exceptiontest;

import org.junit.Test;

/**
 *  异常处理， 抓抛模型
 *  过程一: "抛"：程序在征程执行的过程中，一旦出现异常，就会在异常代码处生成一个对应异常类的对象。
 *      并将此对象抛出。
 *      一旦抛出对象以后，其后的代码就不在执行。
 *
 *  过程二： “抓”： 可以理解为异常的处理方式： ①try-catch-finally ②throws
 *
 *  二、 try-catch-finally的使用
 *
 *
 *
 *
 */
public class ExceptionTest1 {

    @Test
    public void test(){
        String  str = "123";
        str = "abc";
        try {
            int num1 = Integer.parseInt(str);
            System.out.println("hello--------------2");
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        System.out.println("hello--------------2");
    }
}
