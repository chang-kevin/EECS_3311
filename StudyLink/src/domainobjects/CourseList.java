package domainobjects;
import java.util.ArrayList;
import java.util.Iterator;
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
   public  List<Course> search(String coursenumber, String coursename, int yearlevel) {
        List<Course> matchingcourses = new ArrayList<Course>();
        for (Iterator<Course> i = courses.iterator();
             i.hasNext(); ) {
            Course course = i.next();
            if (course.getCoursenumber().matches(coursenumber) || course.getCoursename().matches(coursename))
                matchingcourses.add(course); }
        return matchingcourses;
    }
    
}
