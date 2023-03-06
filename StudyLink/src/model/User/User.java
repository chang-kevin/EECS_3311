package model;

import java.util.UUID;

/**
 * Implementation of the User class using the Builder design pattern.
 */
public class User {
    private int user_id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private DOB dateOfBirth;

    public int getUser_id() {
        return user_id;
    }
    public String getusername() {
        return username;
    }

    public DOB getDateOfBirth() {
        return dateOfBirth;
    }


    private final String uniqueId;
    private final String role;


    public String getPassword() {
        return password;
    }





    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public String getRole() {
        return role;
    }

    private User(UserBuilder builder) {
        this.username = builder.username;
        this.password = builder.password;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.dateOfBirth = builder.dateOfBirth;

        this.uniqueId = UUID.randomUUID().toString();
        this.role = builder.role;
    }

    public static class UserBuilder {
        private int user_id;
        private String username;
        private String password;
        private String firstName;
        private String lastName;

        private DOB dateOfBirth;
        private String role;

        public UserBuilder(String username, String password) {
            this.username = username;
            this.password = password;
        }

        public UserBuilder setUsername(String username) {
            this.username = username;
            return this;
        }

        public UserBuilder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public UserBuilder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }
        public UserBuilder setuser_id(int user_id){
            this.user_id = user_id;
            return  this;
        }

        public UserBuilder setDOB(DOB dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
            return this;
        }


        public UserBuilder setRole(String role) {
            this.role = role;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}
