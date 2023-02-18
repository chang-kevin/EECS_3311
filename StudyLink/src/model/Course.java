package model;
public class Course {
    private int coursenumber;
    private String coursename;
    private int yearlevel;

    public Course(int coursenumber, String coursename, int yearlevel) {
        this.coursenumber = coursenumber;
        this.coursename = coursename;
        this.yearlevel = yearlevel;
    }

    public void setCoursename(String coursename) {
        this.coursename = coursename;
    }

    public int getYearlevel() {
        return yearlevel;
    }

    public void setCoursenumber(int coursenumber) {
        this.coursenumber = coursenumber;
    }

    public void setYearlevel(int yearlevel) {
        this.yearlevel = yearlevel;
    }

    public String getCoursename() {
        return coursename;
    }

    public int getCoursenumber() {
        return coursenumber;
    }
    public void info(){
        System.out.println("course number:" + coursenumber + " course name:" + coursename + " year level:" + yearlevel);
    }
}
