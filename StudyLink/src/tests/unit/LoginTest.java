package tests.unit;

import controller.Login;




import helpers.Authenticator.Authenticator;
import helpers.UserRole;
import model.User.User;
import model.User.UserDAO;
import model.User.UserSession;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


import java.sql.SQLException;

public class LoginTest {
    private Login login;


    User user = new User.UserBuilder("ymann@my.yorku.ca", "yuvtesh").setFirstName("yuvtesh").setLastName("mann").setRole(UserRole.ADMIN).build();



    @BeforeEach
    public void setUp() {
        login = new Login();
        UserSession.setCurrentUser(null);
    }
    @AfterEach
    public void tearDown(){
        login.dispose();
    }
    @Test
    @Order(1)
    public void testEmptyFields(){
        login.getUsername().setText("");
        login.getPasswordField().setText("");
        // Trigger the login button click
        login.getLoginBtn().doClick();
        assertNull(UserSession.getInstance().getCurrentUser());
        assertTrue(login.isVisible());
    }


    @Test
    @Order(2)
    public void testInvalidLogin() throws SQLException {
        // Set up mock behavior for user authentication

        // Set the username and password fields
        login.getUsername().setText("testuser");
        login.getPasswordField().setText("testpass");

        // Trigger the login button click
        login.getLoginBtn().doClick();

        // Verify that the user session was not set
        assertNull(UserSession.getInstance().getCurrentUser());
        assertTrue(login.isVisible());
    }

    @Test
    @Order(3)
    public  void testValidLogin() throws SQLException {
        login.getUsername().setText("ymann@my.yorku.ca");
        login.getPasswordField().setText("yuvtesh");
        login.getLoginBtn().doClick();
        assertEquals(user.getFirstName(), UserSession.getInstance().getCurrentUser().getFirstName());
        assertFalse(login.isVisible());
    }
}
