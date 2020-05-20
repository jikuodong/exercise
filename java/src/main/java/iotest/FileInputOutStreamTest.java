package iotest;

import org.junit.Test;

import java.io.*;

/**
 * @projectName: java
 * @package: iotest
 * @className: FileInputOutStreamTest
 * @author: JKD
 * @description: 测试FileInputStream和FileOutputStream的使用
 * @date: 2020/5/20 15:04
 * @version: 1.0
 */
public class FileInputOutStreamTest {

    /**
     *
     */
    @Test
    public void testFileInputStream(){
        FileInputStream fileInputStream = null;
        try {
            // 1.造文件
            File file = new File("hello.txt");

            // 2. 造流
            fileInputStream = new FileInputStream(file);
            // 3. 读数据
            byte[] buffer = new byte[5];
            int len;
            while ((len = fileInputStream.read()) != -1) {
                String str = new String(buffer, 0 ,len);
                System.out.print(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null){
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
         }


    }

    /**
     * 实现对图片的复制操作
     */
    @Test
    public void testFileInputOutputStream() {
        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;
        try {
            File srcfile = new File("壁纸.png");
            File destfile = new File("壁纸1.png");

            fileInputStream = new FileInputStream(srcfile);
            fileOutputStream = new FileOutputStream(destfile);

            byte[] buffer = new byte[5];
            int len;
            while ((len = fileInputStream.read(buffer)) != -1) {
                fileOutputStream.write(buffer, 0 ,len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
