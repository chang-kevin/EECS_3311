package tests;

import controller.Login;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class LoginTest {
    static Login login;

    @Test
    public void testLoginFields() {
        login = new Login();
        assertNotNull(login);

        JTextField username = (JTextField) TestUtils.getChildNamed(login, "username");
        assertNotNull(username);
    }
}
