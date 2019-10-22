package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * describe 执行SQL语句
 *
 * @author JKD
 * @version 1.0.0
 * @ClassName TestJDBC3.java
 * @createTime 2019年10月21日 15:26:00
 */
public class TestJDBC3 {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/how2java?characterEncoding=UTF-8", "root", "123456");
            Statement s = c.createStatement();
            // 准备sql语句
            // 注意：字符串要用单引号'
            String sql = "insert into hero values (null, "+"'提莫'"+","+313.0f+", "+50+")";
            s.execute(sql);
            System.out.println("执行插入语句成功");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
