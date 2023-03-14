package model.Course;

import model.Topic.Topic;

import java.sql.SQLException;
import java.util.List;

public interface CourseDAO {
    public int add(Course course) throws SQLException;

    public void delete(int id) throws SQLException;

    public Course getCourseById(int id) throws SQLException;

    public List<Course> getAllCourses() throws SQLException;

    public List<Course> searchCourse(String courseColumn) throws SQLException;

    public void update(Course course) throws SQLException;

    public List<Topic> getCourseTopics(String courseId) throws SQLException;

    public void setCourseRating(String username, String courseId, String rating) throws SQLException;
}
