package model.User;

import model.Course.Course;
import model.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    static Connection connection = DatabaseConnection.getConnection();

    public static int add(User user) throws SQLException{
        String query = "insert into users(username, user_password, user_role, first_name, last_name) values (?,?,?,?,?)";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, user.getUsername());
        ps.setString(2, user.getPassword());
        ps.setString(3, user.getRole());
        ps.setString(4, user.getFirstName());
        ps.setString(5, user.getLastName());
        int n = ps.executeUpdate();
        return n;
    }

    public static void delete(String username) throws SQLException {
        String query = "delete from Users where username = ?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1,username);
        ps.executeUpdate();
    }

    public static User getUser(String username) throws SQLException {
        String query = "select * from Users where username = ?";

        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, username);

        ResultSet rs = ps.executeQuery();
        boolean check = false;
        User user = null;

        while (rs.next()) {
            check = true;
            user = new User.UserBuilder(username, rs.getString("user_password"))
                    .setRole(rs.getString("user_role"))
                    .setFirstName(rs.getString("first_name"))
                    .setLastName(rs.getString("last_name"))
                    .build();
        }

        if (check == true) {
            return user;
        }

        return null;
    }

    public static List<User> getAllUsers() throws SQLException {
        String query = "select * from Users";
        PreparedStatement ps = connection.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        List<User> lu = new ArrayList<>();

        while (rs.next()) {
            String username = rs.getString("username");
            String password = rs.getString("password");
            User user  = new User.UserBuilder(username,password)
                    .setFirstName(rs.getString("first_name"))
                    .setLastName(rs.getString("last_name"))

                    .build();
            lu.add(user);
        }

        return lu;
    }

    public static List<Course> getUserCourses(String username) throws SQLException {
        String query = "Select courses.name, courses.description,courses.course_id,courses.course_code \n" +
                "from Users\n" +
                "JOIN user_courses ON user_courses.user_id = users.user_id\n" +
                "JOIN courses ON user_courses.course_id = courses.course_id\n" +
                "WHERE Users.username = ?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, username);
        ResultSet rs = ps.executeQuery();
        List<Course> lc = new ArrayList<>();
        boolean check = false;
        Course course;

        while (rs.next()) {
            check = true;
            int courseId = rs.getInt("course_id");
            course = new Course.CourseBuilder(courseId)
                    .setCourseName(rs.getString("name"))
                    .setCourseDesc(rs.getString("description"))
                    .setCourseCode(rs.getString("course_code"))
                    .build();
            lc.add(course);
        }

        if (check == true) {
            return lc;
        }

        return null;
    }

    public static void update(User user) throws SQLException {
        String query = "update Users set user_password = ?, first_name = ? ,last_name = ? where username = ?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, user.getPassword());
        ps.setString(2,user.getFirstName());
        ps.setString(3, user.getLastName());
        ps.setString(4,user.getUsername());
        ps.executeUpdate();
    }
    public static int countUsers() throws SQLException {
        int count = 0;
        String query = "select count(*) from Users";
        PreparedStatement ps = connection.prepareStatement(query);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            count = rs.getInt(1);
        }

        return count;
    }

    public static UserSettings getUserSettings(String username) throws SQLException {
        String query = "select theme from user_settings where user_settings.username = ?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, username);
        ResultSet rs = ps.executeQuery();
        UserSettings settings = UserSettings.getInstance();

        while (rs.next()) {
            settings.setUserTheme(rs.getString("theme"));
        }

        return settings;
    }
}
