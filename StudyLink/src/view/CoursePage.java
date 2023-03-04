package view;

import model.Course;
import model.CourseDAOImplementation;

import javax.swing.*;
import java.sql.SQLException;

public class CoursePage {
    private JTextArea courseDescText;
    private JLabel courseDescTitle;
    private JLabel courseTopics;
    private JPanel courseBody;
    private JPanel courseHeader;
    private JPanel coursePanel;
    private JList CourseTopicsList;

    public CoursePage(int courseId) {
        CourseDAOImplementation courseDao = new CourseDAOImplementation();
        Course course = null;
        try {
            course = courseDao.getCourse(courseId);
            System.out.println("course" + course.getCourseDesc());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.print(course.getCourseName());
    }
}
