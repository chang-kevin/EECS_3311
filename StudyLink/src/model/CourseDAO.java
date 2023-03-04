package model;

import java.sql.SQLException;
import java.util.List;

public interface CourseDAO {
    public int add(Course course) throws SQLException;

    public void delete(int id) throws SQLException;

    public Course getCourse(int id) throws SQLException;

    public List<Course> getCourses() throws SQLException;

    public void update(Course course) throws SQLException;
}
