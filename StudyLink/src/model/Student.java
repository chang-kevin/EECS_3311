package model;

public class Student extends User {
    private int studentId;
    private String major;

    public Student(String firstName, String lastName, String email, String password, int studentId, String major) {
        super(firstName, lastName, email, password);
        this.studentId = studentId;
        this.major = major;
    }

    public int getStudentId() {
        return studentId;
    }

    public String getMajor() {
        return major;
    }
}
