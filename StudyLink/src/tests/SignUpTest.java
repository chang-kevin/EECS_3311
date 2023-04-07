package tests;
import controller.SignUp;
import helpers.Authenticator.Authenticator;
import helpers.UserRole;
import model.User.User;
import model.User.UserDAO;
import org.junit.jupiter.api.*;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class SignUpTest {
    private SignUp signUp;
    String username = "test@my.yorku.ca";
    String firstName = "Test";
    String lastName = "User";
    String password = "password";
    String securityQuestion = "xyz";

    @BeforeEach
    public void setUp() throws SQLException {
        UserDAO.delete(username);
        signUp = new SignUp();
    }

    @AfterEach
    public void tearDown() throws SQLException {
        UserDAO.delete(username);
        signUp.dispose();
    }

    @Test
    @Order(1)
    public void testEmptyFields() throws SQLException {
        signUp.getEmailField().setText("");
        signUp.getFirstNameField().setText("");
        signUp.getLastNameField().setText("");
        signUp.getPasswordField().setText("");
        signUp.getSecurityQuestionField().setText("");
        signUp.getSignUpBtn().doClick();
        assertFalse(Authenticator.authenticateUser("", ""));
        assertTrue( signUp.isVisible());
    }

    @Test
    @Order(2)
    public void testSignUpSuccessful() throws SQLException {
        signUp.getEmailField().setText(username);
        signUp.getFirstNameField().setText(firstName);
        signUp.getLastNameField().setText(lastName);
        signUp.getPasswordField().setText(password);
        signUp.getSecurityQuestionField().setText(securityQuestion);
        signUp.getSignUpBtn().doClick();
        User user = UserDAO.getUser(username);
        assertNotNull(user);
        assertEquals(firstName, user.getFirstName());
        assertEquals(lastName, user.getLastName());
        assertEquals(password, user.getPassword());
        assertTrue(Authenticator.authenticateUser(username, password));
    }
    @Test
    @Order(3)
    public void testSignUpThrowsException() throws SQLException {
        User demo = new User.UserBuilder(username,password)
                .setFirstName(firstName)
                .setLastName(lastName)
                .setRole(UserRole.STUDENT)
                .build();
        UserDAO.add(demo);
        signUp.getEmailField().setText(username);
        signUp.getFirstNameField().setText(firstName);
        signUp.getLastNameField().setText(lastName);
        signUp.getPasswordField().setText(password);
        signUp.getSecurityQuestionField().setText(securityQuestion);
        signUp.getSignUpBtn().doClick();
        String message = "Duplicate entry 'test@my.yorku.ca' for key 'users.PRIMARY'";
        String e = "";
        try {
            UserDAO.add(demo);
        } catch (Exception ex) {
            e = ex.getMessage();
            assertTrue(message.equals(e));
        }
    }
}