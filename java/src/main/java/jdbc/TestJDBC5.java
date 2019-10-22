package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * describe 使用try-with-resource的方式自动关闭连接
 *
 * @author JKD
 * @version 1.0.0
 * @ClassName TestJDBC5.java
 * @createTime 2019年10月21日 15:58:00
 */
public class TestJDBC5 {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try (
                Connection c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/how2java?characterEncoding=UTF-8","root","123456");
                Statement s = c.createStatement();
        ) {
            String sql = "insert into hero values (null, "+"'提莫'"+ ","+312.0f+", "+50+")";
            s.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
