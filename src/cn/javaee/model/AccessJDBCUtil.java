package cn.javaee.model;

/**
 * @see    AccessJDBCUtil工具类，用于处理数据库连接
 * @author wangbin
 * @time 2017年11月15日 下午3:16:19
 */
import java.sql.*;

public class AccessJDBCUtil {
   //注册jdbc驱动
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch(ClassNotFoundException e) {
            System.err.println("JdbcOdbc Bridge Driver not found!");
            
        }
    }
    
    /**
     * 
     * @see    建立数据库连接,连接的数据库名为marhong,用户名、密码均为root
     * @author wangbin
     * @time 2017年11月15日 下午3:17:11
     * @return  
     * @throws SQLException
     */
    public static java.sql.Connection getAccessDBConnection() throws SQLException {
  
        String databaseURL = "jdbc:mysql://localhost/marhong?useUnicode=true&characterEncoding=utf-8";

        return DriverManager.getConnection(databaseURL, "root", "root");
    }  
}