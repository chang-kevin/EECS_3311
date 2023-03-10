package tests.unit;

import helpers.Authenticator.Authenticator;
import helpers.UserRole;
import model.User.User;
import model.User.UserDAO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class userCRUDTest {
    String email1 = "johndoe@my.yorku.ca";
    String email2 = "foobar@my.yorku.ca";
    String password1 = "12345";
    String password2 = "67890";

    User john = new User.UserBuilder(email1, password1)
            .setFirstName("John")
            .setLastName("Doe")
            .setRole(UserRole.STUDENT)
            .build();
    User sean = new User.UserBuilder(email1, password2)
            .setFirstName("Sean")
            .setLastName("Harper")
            .setRole(UserRole.STUDENT)
            .build();

    @AfterEach
    public void tearDown() throws SQLException {
        UserDAO.delete(john.getUsername());
        UserDAO.delete(sean.getUsername());
    }

    @Test
    public void addUser() throws SQLException {
        assertFalse(Authenticator.hasUser(email1));
        UserDAO.add(john);
        assertTrue(Authenticator.hasUser(email1));
    }

    @Test
    public void checkForDuplicateUsers() throws SQLException {
        String message = "Duplicate entry 'johndoe@my.yorku.ca' for key 'users.PRIMARY'";
        String e = "";
        try {
            UserDAO.add(john);
            UserDAO.add(john);
        } catch (Exception ex) {
            e = ex.getMessage();
        }
        assertTrue(message.equals(e));
    }

    @Test
    public void updateUser() throws SQLException {
        // we first add the user
        assertFalse(Authenticator.hasUser(email1));
        UserDAO.add(john);
        assertTrue(Authenticator.hasUser(email1));

        // then we check the current password
        assertTrue(john.getPassword().equals(password1));
        john.setPassword(password2);

        // then we check if the password was updated
        UserDAO.update(john);
        User user = UserDAO.getUser(email1);
        assertTrue(user.getPassword().equals(password2));
    }

    @Test
    public void deleteUser() throws SQLException{
        assertFalse(Authenticator.hasUser(email2));
        UserDAO.add(sean);
        assertTrue(Authenticator.hasUser(email2));

        UserDAO.delete(email2);
        assertFalse(Authenticator.hasUser(email2));
    }
}
