package tests.unit;

import java.sql.*;

public class DBTestCourses {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Statement statement;

        String url = "jdbc:mysql://localhost:3306/studylink";
        String user = "root";
        String password = "your password";

        String query = "select * from Courses";
        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection con = DriverManager.getConnection(url, user, password);
        statement = con.createStatement();
        ResultSet res = statement.executeQuery(query);

        while (res.next()) {
            String course_id = res.getString(1);
            String course_name = res.getString(2);
            String description = res.getString(3);
            String course_code = res.getString(4);
            String username = res.getString(5);
            System.out.println(course_id + " " + course_name + " " + description + " "
                    + course_code + " " + username);
        }
        con.close();
    }
}
