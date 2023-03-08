package model.Course;

import java.sql.SQLException;
import java.util.List;

public interface CourseDAO {
    public int add(Course course) throws SQLException;

    public void delete(int id) throws SQLException;

    public Course getCourseById(int id) throws SQLException;

    public List<Course> getAllCourses() throws SQLException;

    public List<Course> searchCourse(String courseColumn) throws SQLException;

    public List<Course> getUserPinnedCourses(String username) throws SQLException;

    public void update(Course course) throws SQLException;
}
