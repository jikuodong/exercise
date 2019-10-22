package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * describe 建立与数据库的连接
 *
 * @author JKD
 * @version 1.0.0
 * @ClassName TestJDBC1.java
 * @createTime 2019年10月21日 14:47:00
 */
public class TestJDBC1 {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        // 建立与数据库的Connection连接
        // 这里需要提供：
        // 数据库所处于的ip:127.0.0.1(本机)
        // 数据库的端口号： 3306  （mysql 专用端口号）
        // 数据库名称 how2java
        // 编码方式 UTF-8
        // 账号 root
        // 密码 123456

            Connection c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/how2java?characterEncoding=UTF-8", "root", "123456");
            System.out.println("连接成功，获取连接对象：" + c);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
