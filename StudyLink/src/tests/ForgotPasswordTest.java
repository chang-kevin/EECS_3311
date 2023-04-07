package tests;

import controller.ForgotPassword;
import controller.SignUp;
import helpers.Authenticator.Authenticator;
import helpers.UserRole;
import model.User.User;
import model.User.UserDAO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ForgotPasswordTest {
    private ForgotPassword forgotPassword;
    private SignUp signUp;
    String username = "test@my.yorku.ca";
    String firstName = "Test";
    String lastName = "User";
    String password = "password";
    String securityQuestion = "xyz";
    @BeforeEach
    public void setUp() throws SQLException {

        forgotPassword = new ForgotPassword();
        signUp = new SignUp();
        User demo = new User.UserBuilder(username,password)
                .setFirstName(firstName)
                .setLastName(lastName)
                .setRole(UserRole.STUDENT)
                .build();
        signUp.getEmailField().setText(username);
        signUp.getFirstNameField().setText(firstName);
        signUp.getLastNameField().setText(lastName);
        signUp.getPasswordField().setText(password);
        signUp.getSecurityQuestionField().setText(securityQuestion);
        signUp.getSignUpBtn().doClick();

    }
    @AfterEach
    public void tearDown() throws SQLException {
        UserDAO.delete(username);
        forgotPassword.dispose();
    }
    @Test
    public void verifyUserName() throws SQLException {

        assertTrue(forgotPassword.getUsernameBtn().isVisible());
        forgotPassword.getUsernameField().setText(username);
        forgotPassword.getUsernameBtn().doClick();
        assertFalse(forgotPassword.getUsernameBtn().isVisible());
        assertTrue(forgotPassword.getSecurityQuestionBtn().isVisible());
        assertTrue(forgotPassword.getAnswer().isVisible());
        assertTrue(forgotPassword.getSecurityQuestionLabel().getText().equals("What is the name of your first pet?"));
        forgotPassword.getAnswer().setText(securityQuestion);
        assertTrue(forgotPassword.getAnswer().getText().equals(securityQuestion));
        assertFalse(Authenticator.authenticateUser(username,"new"));
        forgotPassword.getPasswordField1().setText("new");
        forgotPassword.getResetPasswordBtn().doClick();
        assertTrue(Authenticator.authenticateUser(username,"new"));
    }

}