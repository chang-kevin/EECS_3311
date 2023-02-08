package domainobjects;
import java.util.*;

public class CourseList {
    private ArrayList<Course> courses = new ArrayList<Course>();

    public CourseList(ArrayList<Course> courses) {
        this.courses = courses;
    }

    public CourseList(){}

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public void setCourses(ArrayList<Course> courses) {
        this.courses = courses;
    }
    public void addCourse(Course course){

        courses.add(course);
    }

    public  void search(String coursenumber) {

        for (Iterator<Course> i = courses.iterator();
             i.hasNext(); ) {
            Course course = i.next();
            if (course.getCoursenumber().matches(coursenumber) ){
                System.out.println(course.toString());
            } }

    }
    public  void search( String coursename, int yearlevel) {

        for (Iterator<Course> i = courses.iterator();
             i.hasNext(); ) {
            Course course = i.next();
            if (course.getCoursenumber().matches(coursename) ){
                System.out.println(course.toString());
            } }

    }
    public void search( int yearlevel) {
        ArrayList<Course> matchingcourses = new ArrayList<Course>();
        for (Iterator<Course> i = courses.iterator();
             i.hasNext(); ) {
            Course course = i.next();
            if (course.getYearlevel() == yearlevel )
                matchingcourses.add(course); }
        for(int i=0;i<matchingcourses.size();i++){
            System.out.println(matchingcourses.get(i).toString());
        }

    }


}
