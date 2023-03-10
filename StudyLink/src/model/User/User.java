 
       package model.User;
import java.util.UUID;
/**
 * Implementation of the User class using the Builder design pattern.
 */
public class User {
    private String username;
    private String password;
    private String firstName;
    private String lastName;

    private final String uniqueId;
    private final String role;

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
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
        this.uniqueId = UUID.randomUUID().toString();
        this.role = builder.role;
    }

    public static class UserBuilder {
        private String username;
        private String password;
        private String firstName;
        private String lastName;
        private String uniqueId;
        private String role;

        public UserBuilder(String username, String password) {
            this.username = username;
            this.password = password;
            this.uniqueId = UUID.randomUUID().toString();
        }
        public UserBuilder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public UserBuilder setLastName(String lastName) {
            this.lastName = lastName;
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
