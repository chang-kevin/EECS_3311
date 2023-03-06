package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImplementation  {
    static Connection connection = DatabaseConnection.getConnection();
public static int adduser(User user) throws SQLException{
    String query = "insert into Users  values (?,?,?,?,?,?)";
    PreparedStatement ps = connection.prepareStatement(query);
    ps.setInt(1,user.getUser_id());
    ps.setString(2,user.getusername());
    ps.setString(3,user.getPassword());
    ps.setString(4,user.getRole());
    ps.setString(5,user.getFirstName());
    ps.setString(6,user.getLastName());
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
            user = new User.UserBuilder(username,rs.getString("user_password"))
                    .setFirstName(rs.getString("first_name"))
                    .setLastName(rs.getString("last_name"))
                    .setuser_id(rs.getInt("user_id"))
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
                    .setuser_id(rs.getInt("user_id"))
                    .build();
            lu.add(user);
        }
        return lu;
    }
    public static List<Course> userCourses(String username) throws SQLException {
    String query = "Select courses.name, courses.description,courses.course_id,courses.course_code \n" +
            "from Users\n" +
            "JOIN user_courses ON user_courses.user_id = users.user_id\n" +
            "JOIN courses ON user_courses.course_id = courses.course_id\n" +
            "WHERE Users.username = ?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1,username);
        ResultSet rs = ps.executeQuery();
        List<Course> lc = new ArrayList<>();
        boolean check = false;
        Course course = null;
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
        String query = "update Users set user_password = ?, first_name = ? ,last_name = ?,birthdate = ? where username = ?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, user.getPassword());
        ps.setString(2,user.getFirstName());
        ps.setString(3, user.getLastName());
        ps.setString(5,user.getusername());
        ps.executeUpdate();
    }
    public static int countuser() throws SQLException{
    int count = 0;
        String query = "select count(*) from Users";
        PreparedStatement ps = connection.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
             count = rs.getInt(1);
        }
        return count;
    }

}
