package model;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static Connection connection;

    static {
        String url = "jdbc:mysql://localhost:3306/studylink";

        Dotenv dotenv = Dotenv.load();
        String username = dotenv.get("MYSQL_USERNAME");
        String password = dotenv.get("MYSQL_PASSWORD");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return connection;
    }
}
