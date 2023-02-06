package domainobjects;

import java.util.UUID;

public class User {
    private String fname;
    private String emailid;
    private String password;
    private String lname;
    private DOB dob;
    private String uniqueID ;


    public User(String name, String emailid, String password, String lname, DOB dob) {
        this.fname = name;
        this.emailid = emailid;
        this.password = password;
        this.lname = lname;
        this.dob = dob;
        String uniqueID = UUID.randomUUID().toString();
    }

    public String getEmailid() {
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

    private String getPassword() {
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
        return uniqueID;
    }
}
