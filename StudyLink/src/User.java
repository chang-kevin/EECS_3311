/**
 * User class containing all the user attributes.
 */
public class User {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private int userId;

    private int dateOfBirth;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(String username, String password, String firstName, String lastName, String email, int dateOfBirth, int userId)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.userId = userId;
    }
}
