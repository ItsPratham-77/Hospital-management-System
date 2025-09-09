import java.sql.*;

public class DBUtil {
    private static final String URL = "root@localhost:3306";
    private static final String USER = "root";   // your username
    private static final String PASSWORD = "newunkownp@22";   // your password

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Load MySQL driver
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
