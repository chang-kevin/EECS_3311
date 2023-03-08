package model.Course;

import javax.swing.*;

/**
 * Implementation of the Course class using the Builder design pattern.
 */
public class Course {
    private int courseId;
    private String courseCode;
    private String courseName;
    private String courseDesc;

    private JButton viewButton;

    private JButton bookmarkButton;

    private Course(CourseBuilder builder) {
        this.courseId = builder.courseId;
        this.courseCode = builder.courseCode;
        this.courseName = builder.courseName;
        this.courseDesc = builder.courseDesc;
        this.viewButton = builder.viewButton;
        this.bookmarkButton = builder.bookmarkButton;
    }

    public int getCourseId() {
        return courseId;
    }

    public String getCourseDesc() {
        return courseDesc;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public JButton getViewButton() { return this.viewButton; }

    public JButton getBookmarkButton() { return this.bookmarkButton; }

    public void setViewButton(JButton view) {
        this.viewButton = view;
    }

    public void setBookmarkButton (JButton bookmark) {
        this.bookmarkButton = bookmark;
    }

    public static class CourseBuilder {
        private int courseId;
        private String courseName;
        private String courseDesc;
        private String courseCode;

        private JButton viewButton;

        private JButton bookmarkButton;

        public CourseBuilder(int courseId) {
            this.courseId = courseId;
        }

        public CourseBuilder setCourseName(String courseName) {
            this.courseName = courseName;
            return this;
        }

        public CourseBuilder setCourseDesc(String courseDesc) {
            this.courseDesc = courseDesc;
            return this;
        }

        public CourseBuilder setCourseCode(String courseCode) {
            this.courseCode = courseCode;
            return this;
        }

        public Course build() {
            return new Course(this);
        }
    }
}
