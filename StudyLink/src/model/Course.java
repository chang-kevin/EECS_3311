package model;

/**
 * Implementation of the Course class using the Builder design pattern.
 */
public class Course {
    private int courseCode;
    private String courseName;
    private int yearlevel;

    private Course(CourseBuilder builder) {
        this.courseCode = builder.courseCode;
        this.courseName = builder.courseName;
        this.yearlevel = builder.yearLevel;
    }

    public int getYearlevel() {
        return yearlevel;
    }

    public String getCourseName() {
        return courseName;
    }

    public int getCourseCode() {
        return courseCode;
    }

    public void info() {
        System.out.println("course number:" + courseCode + " course name:" + courseName + " year level:" + yearlevel);
    }

    public static class CourseBuilder {
        private int courseCode;
        private String courseName;
        private int yearLevel;

        public CourseBuilder(String courseName, int courseNumber) {
            this.courseName = courseName;
            this.courseCode = courseNumber;
        }

        public CourseBuilder setYearLevel(int yearLevel) {
            this.yearLevel = yearLevel;
            return this;
        }
    }
}
