package model.Course;

import model.Database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseDAOImplementation implements CourseDAO {
    static Connection connection = DatabaseConnection.getConnection();

    @Override
    public int add(Course course) throws SQLException {
        String query = "insert into courses(course_id, name, description, course_code) VALUES (?,?,?,?)";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, course.getCourseId());
        ps.setString(2, course.getCourseName());
        ps.setString(3, course.getCourseDesc());
        ps.setString(4, course.getCourseCode());
        int n = ps.executeUpdate();
        return n;
    }

    @Override
    public void delete(int id) throws SQLException {
        String query = "delete from courses where course_id = ?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, id);
        ps.executeUpdate();
    }

    @Override
    public Course getCourseById(int id) throws SQLException {
        String query = "select * from courses where course_id = ?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, id);

        ResultSet rs = ps.executeQuery();
        boolean check = false;
        Course course = null;

        while (rs.next()) {
            check = true;
            course = new Course.CourseBuilder(id)
                .setCourseName(rs.getString("name"))
                .setCourseDesc(rs.getString("description"))
                .setCourseCode(rs.getString("course_code"))
                .build();
        }

        if (check == true) {
            return course;
        }
        return null;
    }

    @Override
    public List<Course> getAllCourses() throws SQLException {
        String query = "select * from courses";
        PreparedStatement ps = connection.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        List<Course> lc = new ArrayList<>();

        while (rs.next()) {
            int courseId = rs.getInt("course_id");
            Course course = new Course.CourseBuilder(courseId)
                    .setCourseName(rs.getString("name"))
                    .setCourseDesc(rs.getString("description"))
                    .setCourseCode(rs.getString("course_code"))
                    .build();
            lc.add(course);
        }
        return lc;
    }

    @Override
    public List<Course> searchCourse(String courseColumn) throws SQLException {
        String query = "select * from courses where name like ? or id like ?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, courseColumn);
        ps.setString(2, courseColumn);

        ResultSet rs = ps.executeQuery();
        List<Course> lc = new ArrayList<>();

        while (rs.next()) {

            int courseId = rs.getInt("course_id");
            Course course = new Course.CourseBuilder(courseId)
                    .setCourseName(rs.getString("name"))
                    .setCourseDesc(rs.getString("description"))
                    .setCourseCode(rs.getString("course_code"))
                    .build();
            lc.add(course);
        }
        return lc;
    }

    @Override
    public List<Course> getUserPinnedCourses(String username) throws SQLException {
        return null;
    }

    @Override
    public void update(Course course) throws SQLException {
        String query = "update courses set name = ?, description = ?, course_code = ? where course_id = ?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, course.getCourseName());
        ps.setString(2, course.getCourseDesc());
        ps.setString(3, course.getCourseCode());
        ps.setInt(4, course.getCourseId());
        ps.executeUpdate();
    }

    public List<String> getTopicList() throws SQLException{
        String query = "select * from Topics";
        PreparedStatement ps = connection.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        List<String> topics = new ArrayList<>();

        while (rs.next()){
            topics.add(rs.getString("topic_id") + ": " + rs.getString("topic_name"));
        }
        return topics;
    }
}
