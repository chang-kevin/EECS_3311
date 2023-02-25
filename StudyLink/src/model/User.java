package model;

import java.util.UUID;

public class User {
    private String fname;
    private String emailid;
    private String password;
    private String lname;
    private DOB dob;
    private final String uniqueID;
    private final String role;

    public User(String firstName, String lastName, String emailid, String password, DOB dob, String role) {
        this.fname = firstName;
        this.emailid = emailid;
        this.password = password;
        this.lname = lastName;
        this.dob = dob;
        this.uniqueID = UUID.randomUUID().toString();
        this.role = role;
    }

    public User(String firstName, String lastName, String emailId, String password) {
        this.fname = firstName;
        this.emailid = emailId;
        this.password = password;
        this.lname = lastName;
        this.uniqueID = UUID.randomUUID().toString();
        this.role = UserRole.STUDENT;
    }

    public String getRole() {
        return role;
    }

    public String getEmail() {
        return emailid;
    }

    public String getFName() {
        return fname;
    }

    public void setFName(String name) {
        this.fname = name;
    }

    public void setEmailid(String emailid) {
        this.emailid = emailid;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public DOB getDob() {
        return dob;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getUniqueID() {
        return this.uniqueID;
    }
    public void accountinfo(){
        System.out.println("fname:" + fname + " lname:" + lname + " email: " + emailid + " dob:" + dob.toString());
    }
}
