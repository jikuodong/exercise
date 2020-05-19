package filetest;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * @projectName: java
 * @package: filetest
 * @className: FileTest
 * @author: JKD
 * @description: File类的使用
 * @date: 2020/5/19 16:10
 * @version: 1.0
 */
public class FileTest {

    @Test
    public void test2() {
        File file1 = new File("hello.txt");
        File file2 = new File("d:\\io\\hi.txt");
        // 获取绝对路径
        System.out.println(file1.getAbsolutePath());
        // 获取路径
        System.out.println(file1.getPath());
        // 获取名称
        System.out.println(file1.getName());
        // 获取上层文件目录路径。若无，返回null
        System.out.println(file1.getParent());
        // 获取文件长度（即：字节数）。不能获取目录的长度。
        System.out.println(file1.length());
        // 获取最后一次的修改时间，毫秒值。
        System.out.println(file1.lastModified());

        System.out.println();

        System.out.println(file2.getAbsolutePath());
        System.out.println(file2.getPath());
        System.out.println(file2.getName());
        System.out.println(file2.getParent());
        System.out.println(file2.length());
        System.out.println(file2.lastModified());
    }
    @Test
    public void test3() {
        File file = new File("E:\\gitHub\\exercise\\java\\src\\main\\java");
        // 获取指定目录下的所有文件或者文件目录的名称数组
        String[] list = file.list();
        for (String s: list) {
            System.out.println(s);
        }
        // 获取指定目录下的所有文件或者文件目录的File数组
        File[] files = file.listFiles();
        for (File f: files) {
            System.out.println(f);
        }
    }

    @Test
    public void test4() {
        /* public boolean renameTo(File dest): 把文件重命名为指定的文件路径
            比如： file1.renameTo(file2)为例.
            要想保证返回true,需要file1在硬盘中是存在的，且file2不能在硬盘中存在。
        */
        File file1 = new File("hello.txt");
        File file2 = new File("D:\\io\\hi.txt");
        boolean renameTo = file2.renameTo(file1); // hello.txt从用盘中删除，hi.txt中有了hello.txt 中 的内容
        System.out.println(renameTo);
    }

    @Test
    public void test5() {
        File file = new File("hello.txt");
        // 判断是否是文件目录
        System.out.println(file.isDirectory());
        // 判断是否是文件
        System.out.println(file.isFile());
        // 判断是否存在
        System.out.println(file.exists());
        // 判断是否可读
        System.out.println(file.canRead());
        // 判断是否可写
        System.out.println(file.canWrite());
        // 判断是否隐藏
        System.out.println(file.isHidden());

        System.out.println();
        File file1 = new File("D:\\io");
        // 判断是否是文件目录
        System.out.println(file1.isDirectory());
        // 判断是否是文件
        System.out.println(file1.isFile());
        // 判断是否存在
        System.out.println(file1.exists());
        // 判断是否可读
        System.out.println(file1.canRead());
        // 判断是否可写
        System.out.println(file1.canWrite());
        // 判断是否隐藏
        System.out.println(file1.isHidden());
    }


    /*
        创建硬盘中对应的文件或文件夹
     */
    @Test
    public void test6() throws IOException {
        File file = new File("hi.txt");
        // 创建文件。若文件存在，则不创建，返回false
        if (!file.exists()){
            // 文件创建
            file.createNewFile();
            System.out.println("创建成功");
        } else {
            // 文件删除
            file.delete();
            System.out.println("删除成功");
        }
    }
    @Test
    public void test7() throws IOException {
        // 文件目录的创建
        File file = new File("D:\\io\\io1");
        // 创建文件目录。如果此文件目录存在，就不创建了。如果此文件目录的上层目录不存在，也不创建。
        boolean mkdir = file.mkdir();
        if (mkdir) {
            System.out.println("创建成功1");
        }
        File file1 = new File("D:\\io\\io2");
        //  创建文件目录。如果此文件上层目录不存在，一并创建。
        boolean mkdir1 = file1.mkdirs();
        if (mkdir1) {
            System.out.println("创建成功2");
        }
    }


}
