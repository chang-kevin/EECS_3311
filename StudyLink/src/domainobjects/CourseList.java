package domainobjects;

import java.util.List;

public class CourseList {
    private List<Course> courses;

    public CourseList(List<Course> courses) {
        this.courses = courses;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
    public void addCourse(Course course){
        courses.add(course);
    }

    public Course search(){
        return null;
    }

}
