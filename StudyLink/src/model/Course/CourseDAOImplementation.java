package model.Course;

import model.Database.DatabaseConnection;
import model.Topic.Topic;

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

    public Course getCourseByNameOrId(String searchTerm) throws SQLException {
        String query = "select * from courses where name like lower(?) or (lower(course_code) like lower(?) or replace(lower(course_code), 'eecs ', '') = lower(?))";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, searchTerm);
        ps.setString(2, searchTerm);
        ps.setString(3, searchTerm);

        ResultSet rs = ps.executeQuery();
        boolean check = false;
        Course course = null;

        while (rs.next()) {
            check = true;
            course = new Course.CourseBuilder(rs.getInt("course_id"))
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
        String query = "select courses.course_id, courses.name, courses.description, courses.course_code, AVG(course_ratings.rating) AS average_rating from courses left join course_ratings ON courses.course_id = course_ratings.course_id group by courses.course_id order by courses.course_id";
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
    public void update(Course course) throws SQLException {
        String query = "update courses set name = ?, description = ?, course_code = ? where course_id = ?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, course.getCourseName());
        ps.setString(2, course.getCourseDesc());
        ps.setString(3, course.getCourseCode());
        ps.setInt(4, course.getCourseId());
        ps.executeUpdate();
    }

    @Override
    public List<Topic> getCourseTopics(String courseId) throws SQLException {
        String query = "SELECT course_topics.course_id, course_topics.topic_id, topics.topic_name FROM course_topics JOIN topics ON course_topics.topic_id = topics.topic_id WHERE course_topics.topic_id = ?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, courseId);
        ResultSet rs = ps.executeQuery();
        List<Topic> topics = new ArrayList<>();
        while (rs.next()) {
            topics.add(new Topic(rs.getString("topic_id"), rs.getString("topic_name"), rs.getString("course_id")));
        }
        return topics;
    }

    @Override
    public void setCourseRating(String username, String courseId, String rating) throws SQLException {
        String query = "insert into course_ratings (course_id, username, rating) values (?, ?, ?) ON DUPLICATE KEY UPDATE rating = ?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, courseId);
        ps.setString(2, username);
        ps.setString(3, rating);
        ps.setString(4, rating);
        ps.executeUpdate();
    }
}
