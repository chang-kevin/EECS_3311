package model;

import java.util.UUID;

public class User {
    private String fname;
    private String emailid;
    private String password;
    private String lname;
    private DOB dob;
    private String uniqueID ;

    public User(String firstName, String lastName, String emailid, String password, DOB dob) {
        this.fname = firstName;
        this.emailid = emailid;
        this.password = password;
        this.lname = lastName;
        this.dob = dob;
        this.uniqueID = UUID.randomUUID().toString();
    }

    public User(String name, String lname, String emailId, String password){
        this.fname = name;
        this.emailid = emailId;
        this.password = password;
        this.lname = lname;
        this.uniqueID = UUID.randomUUID().toString();
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
