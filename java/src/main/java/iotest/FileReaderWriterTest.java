package iotest;

import org.junit.Test;

import java.io.*;

/**
 * @projectName: java
 * @package: iotest
 * @className: FileReaderWriterTest
 * @author: JKD
 * @description: IO 节点流（文件流）的测试
 * @date: 2020/5/20 10:32
 * @version: 1.0
 */
public class FileReaderWriterTest {

    /**
     *  将hello.txt文件内容读入程序中，并输出到控制台
     */
    @Test
    public void testFileReader() {
        FileReader fileReader = null;
        try {
            // 1. 实例化File类的对象，指明要操作的文件
            // 相较于当前Module
            File file = new File("hello.txt");

            // 2. 提供具体的流
            fileReader = new FileReader(file);
            // 3. 数据的读入
            // read(): 返回读入的一个字符。如果达到文件末尾，返回-1
            // 方式一
//        int data = fileReader.read();
//        while (data != -1) {
//            System.out.print((char) data);
//            data = fileReader.read();
//        }
            // 方式二： 语法上针对于方式一的修改
            int data;
            while ((data = fileReader.read()) != -1) {
                System.out.print((char) data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 4. 流的关闭操作
            try {
                if (fileReader != null) {
                    fileReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 对read()操作升级： 使用read的重载方法
     */
    @Test
    public void testFileReader1(){
        FileReader fr = null;
        try {
            // 1.File类的实例化
            File file = new File("hello.txt");
            // 2. FileReader流的实例化
            fr = new FileReader(file);
            // 3. 读入的操作
            // read(char[] cbuf): 返回每次读入cbuf数组中的字符的个数。如果达到文件末尾，返回-1.
            char[] cbuf = new char[5];
            int len;
            while ((len = fr.read(cbuf)) != -1) {
                // 方式一：
                // 错误的写法
//                for (int i = 0; i < cbuf.length; i++) {
//                    System.out.print(cbuf[i]);
//                }
                // 正确写法
//                for (int i = 0; i < len; i++) {
//                    System.out.print(cbuf[i]);
//                }
                // 方式二：
                // 错误的写法，对应着方式一的错误写法
//                String str= new String(cbuf);
//                System.out.print(str);
                // 正确写法
                String str = new String (cbuf, 0, len);
                System.out.print(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 4. 资源的关闭
            try {
                if (fr != null) {
                    fr.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 从内存中写出数据到硬盘的文件里。
     * 说明：
     * ① 输出操作，对应的File可以不存在的。并不会报异常。
     * ②
     *    File对应的硬盘中的文件如果不存在，在输出的过程中,会自动创建此文件。
     *    File对应的硬盘中的文件如果存在：
     *      如果流使用的构造器是： FileWriter(file, false)/FileWriter(file):对原有文件的覆盖
     *      如果流使用的构造器是： FileWriter(file, true)/FileWriter(file): 不会对原有文件覆盖，而是在原有文件后面添加。
     */
    @Test
    public void testFileWriter() {
        FileWriter fileWriter = null;
        try {
            // 1. 提供file类的对象，指明写出到的文件
            File file = new File("hello1.txt");
            // 2.提供FileWriter的对象，用于数据的写出
            fileWriter = new FileWriter(file);
            // 3.写出的操作
            fileWriter.write("I hava a dream!\n");
            fileWriter.write("you nedd to hava a dream!");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileWriter != null) {
                // 4.流资源的关闭
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    /**
     * 文件复制操作
     */
    @Test
    public void testFileReaderFileWriter(){
        FileReader fileReader = null;
        FileWriter fileWriter = null;
        try {
            // 1.创建FIle类的对象，指明读入和写入的文件
            File srcFile = new File("hello.txt") ;
            File destFile = new File("hello2.txt") ;
            // 2.创建输入流和输出流的对象
            fileReader = new FileReader(srcFile);
            fileWriter = new FileWriter(destFile);
            // 3. 数据的读入和写出操作
            char[] cbuf = new char[5];
            // 记录每次读入到cbuf数组中的字符的个数
            int len;
            while ((len = fileReader.read(cbuf)) != -1) {
                fileWriter.write(cbuf, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭流资源
            try {
                if (fileReader!=null){
                    fileWriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (fileWriter!= null) {
                    fileReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
