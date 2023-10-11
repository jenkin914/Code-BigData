package client;

import java.sql.*;

/**
 * @author bigData-zj
 * @version 1.0
 * @date 2023/10/9 11:35
 * @description mysql 客户端
 */
public class MysqlSyncClient {
    private static transient Connection connection;
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/test";
    private static final String USER = "root";
    private static final String PASSWORD = "jenkin";

    static {
        init();
    }

    private static void init() {
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println("Driver not found!" + e.getMessage());
        }
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("init connection failed!" + e.getMessage());
        }
    }

    public void close() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println("close connection failed!" + e.getMessage());
        }
    }

    public <OUT> OUT query(OUT out) {
        try {
            String sql = "SELECT * FROM ods_day_1009";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            if (rs != null && rs.next()) {
                System.out.println(rs);
                // out = (OUT) rs;
            }
        } catch (SQLException e) {
            System.out.println("query failed!" + e.getMessage());
        }
        return out;
    }
}
