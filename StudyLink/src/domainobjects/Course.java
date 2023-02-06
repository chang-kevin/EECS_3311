package domainobjects;
public class Course {
    private String coursenumber;
    private String coursename;
    private int yearlevel;

    public Course(String coursenumber, String coursename, int yearlevel) {
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

    public void setCoursenumber(String coursenumber) {
        this.coursenumber = coursenumber;
    }

    public void setYearlevel(int yearlevel) {
        this.yearlevel = yearlevel;
    }

    public String getCoursename() {
        return coursename;
    }

    public String getCoursenumber() {
        return coursenumber;
    }
}
