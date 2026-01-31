package sms;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    public static Connection getConnection() {
        try {
            // Load MySQL JDBC Driver (IMPORTANT)
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/student_db?useSSL=false&serverTimezone=UTC",
                "root",
                "yogam#11@2004"
            );

            return con;

        } catch (Exception e) {
            e.printStackTrace();   // SHOW REAL ERROR
            return null;
        }
    }
}
