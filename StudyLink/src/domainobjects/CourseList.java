package domainobjects;
import java.util.*;

public class CourseList {
    public static ArrayList<Course> courses = new ArrayList<Course>();
    public static final CourseList courselist = new CourseList();
    
    private CourseList(){}

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public void setCourses(ArrayList<Course> courses) {
        this.courses = courses;
    }
    public void addCourse(Course course){
        courses.add(course);
    }

    public static void search(int coursenumber) {

        for (Iterator<Course> i = courses.iterator();
             i.hasNext(); ) {
            Course course = i.next();
            if (course.getCoursenumber() == coursenumber ){
                System.out.print("Result of the search :" );
                course.info();
            } }

    }
    public static  void searchbyname( String coursename) {

        for (Iterator<Course> i = courses.iterator();
             i.hasNext(); ) {
            Course course = i.next();
            if (course.getCoursename().matches(coursename) ){
                System.out.print("Result of the search:" );
                course.info();
            } }

    }
    public static void list( int yearlevel) {
        ArrayList<Course> matchingcourses = new ArrayList<Course>();
        for (Iterator<Course> i = courses.iterator();
             i.hasNext(); ) {
            Course course = i.next();
            if (course.getYearlevel() == yearlevel )
                matchingcourses.add(course); }
        System.out.println("List of all courses in " + yearlevel + "year");
        for(int i=0;i<matchingcourses.size();i++){
            matchingcourses.get(i).info();
        }

    }
    public static void list(){
        System.out.println("List of  eecs courses");
        for (int j = 0;j<courses.size();j++){
            courses.get(j).info();
        }

    }


}
