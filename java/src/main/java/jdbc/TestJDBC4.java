package jdbc;

import java.sql.*;

/**
 * describe 关闭连接
 * 数据库的连接是有限资源，相关操作结束后，养成关闭数据库的好习惯
 * 先关闭Statement
 * 后关闭Connection
 *
 * @author JKD
 * @version 1.0.0
 * @ClassName TestJDBC4.java
 * @createTime 2019年10月21日 15:45:00
 */
public class TestJDBC4 {
    public static void main(String[] args) {
        Connection c = null;
        Statement s = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/how2java?characterEncoding=UTF-8", "root", "123456");
            s = c.createStatement();
            String sql = "insert into hero values (null, "+ "'提莫'" +" ,"+ 313.0f+","+50+")";
            s.execute(sql);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 数据库的连接是有限资源，相关操作结束后，养成关闭数据库的好习惯。
            // 先关闭Statement
            if (s!=null) {
                try {
                    s.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            // 后关闭Connection
            if (c != null) {
                try {
                    c.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
