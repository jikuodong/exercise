package network;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @projectName: java
 * @package: network
 * @className: InetAddressTest
 * @author: JKD
 * @description: InetAddress对象的测试
 * @date: 2020/5/15 16:30
 * @version: 1.0
 */
public class InetAddressTest {

    public static void main(String[] args) {
        try {
            InetAddress inetAddress = InetAddress.getByName("192.168.6.7");
            System.out.println(inetAddress);
            // 获取本地地址
            InetAddress inetAddress1 = InetAddress.getLocalHost();
            System.out.println(inetAddress1);
            // getHostName
            System.out.println(inetAddress.getHostName());
            System.out.println(inetAddress.getHostAddress());

        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
