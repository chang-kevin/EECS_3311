package tests;

import controller.Login;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class LoginTest {
    static Login login;

    @BeforeAll
    static void setUp() {
        login = new Login();
    }

    @AfterAll
    static void tearDown() {
        login = null;
    }

    @Test
    public void testLoginHeader() {
        JLabel loginHeader = (JLabel) TestUtils.getChildNamed(login, "loginHeader");
        assertNotNull(loginHeader);
    }

    @Test
    public void testLoginFields() {
        assertNotNull(login);

        JTextField usernameField = (JTextField) TestUtils.getChildNamed(login, "usernameField");
        assertNotNull(usernameField);

        JLabel usernameLabel = (JLabel) TestUtils.getChildNamed(login, "usernameLabel");
        assertNotNull(usernameLabel);

        JTextField passwordField = (JTextField) TestUtils.getChildNamed(login, "passwordField");
        assertNotNull(passwordField);

        JLabel passwordLabel = (JLabel) TestUtils.getChildNamed(login, "passwordLabel");
        assertNotNull(passwordLabel);
    }

    @Test
    public void testLoginButtons() {
        JButton loginBtn = (JButton) TestUtils.getChildNamed(login, "loginBtn");
        assertNotNull(loginBtn);

        JButton signUpBtn = (JButton) TestUtils.getChildNamed(login, "signUpBtn");
        assertNotNull(signUpBtn);

        JButton forgotPasswordBtn = (JButton) TestUtils.getChildNamed(login, "forgotPasswordBtn");
        assertNotNull(forgotPasswordBtn);
    }
}
