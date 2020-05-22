package iotest;

import org.junit.Test;

import java.io.*;

/**
 * @projectName: java
 * @package: iotest
 * @className: BufferedTest
 * @author: JKD
 * @description: 缓冲流的使用
 *
 * 作用： 提高流的读取、写入的速度
 *
 * 提高读写速度的原因： 内部提供了一个缓冲区
 *
 * @date: 2020/5/20 15:51
 * @version: 1.0
 */
public class BufferedTest {

    /**
     * 实现非文本文件的复制
     */
    @Test
    public void BufferedDreamTest(){
        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            // 造文件
            File srcFile = new File("壁纸.png");
            File destFile = new File("壁纸2.png");
            // 造流
            fileInputStream = new FileInputStream(srcFile);
            fileOutputStream = new FileOutputStream(destFile);
            // 造缓冲流
            bis = new BufferedInputStream(fileInputStream);
            bos = new BufferedOutputStream(fileOutputStream);

            // 2.复制的细节，读取、写入
            byte[] buffer = new byte[1024];
            int len;
            while ((len = bis.read(buffer))!=-1) {
                bos.write(buffer, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 4.资源的关闭
            // 要求：先关闭外层的流，在关闭内层的流
            try {
                if (bos != null){
                    bos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (bis != null) {
                    bis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            // 说明：关闭外层流的同时，内层流也会自动的进行关闭。关于内层的流的关闭，我们可以省略。
//            fileOutputStream.close();
//            fileInputStream.close();
        }

    }

}
