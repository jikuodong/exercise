package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * describe 创建Statement
 *
 * @author JKD
 *  * @version 1.0.0
 * @ClassName TestJDBC2.java
 * @createTime 2019年10月21日 15:09:00
 */
public class TestJDBC2 {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/how2java?characterEncoding=UTF-8","root", "123456");
            // 注意： 使用的是 java.sql.Statement
            // 不要不小心使用到： com.mysql.jdbc.Statement;
            Statement s = c.createStatement();
            System.out.println("获取Statement对象：" + s);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
