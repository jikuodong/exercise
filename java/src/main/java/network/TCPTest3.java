package network;

import org.junit.Test;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @projectName: java
 * @package: network
 * @className: TCPTest2
 * @author: JKD
 * @description: 实现TCP的网络编程
 *  客户端发送文件给客户端，服务端将文件保存在本地,服务端反馈给客户端
 * @date: 2020/5/18 14:30
 * @version: 1.0
 */
public class TCPTest3 {

    @Test
    public void client() throws IOException {
        Socket socket = new Socket(InetAddress.getByName("127.0.0.1"), 9090);
        OutputStream os = socket.getOutputStream();
        FileInputStream fis = new FileInputStream(new File("timg.jpg"));
        byte[] buffer = new byte[1024];
        int len;
        while ((len = fis.read(buffer)) != -1) {
            os.write(buffer, 0 ,len);
        }
        // 关闭数据
        socket.shutdownOutput();
        // 接收来自于服务端的数据，并显示到控制台上
        InputStream is = socket.getInputStream();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buffer1 = new byte[20];
        int len1;
        while ((len1 = is.read(buffer1)) != -1) {
            bos.write(buffer1, 0,len1);

        }
        System.out.println(bos.toString());
        fis.close();
        os.close();
        socket.close();
        is.close();
        bos.close();
    }

    @Test
    public void server() throws IOException {
        ServerSocket ss = new ServerSocket(9090);
        Socket socket = ss.accept();
        InputStream is = socket.getInputStream();
        FileOutputStream fos = new FileOutputStream(new File("time2.jpg"));
        byte[] buffer = new byte[1024];
        int len;
        while ((len = is.read(buffer)) != -1) {
            fos.write(buffer, 0 ,len);
        }
        // 服务器端给予客户端反馈
        OutputStream os = socket.getOutputStream();
        os.write("你好,照片已收到".getBytes());
        fos.close();
        is.close();
        socket.close();
        ss.close();
        os.close();
    }
}
