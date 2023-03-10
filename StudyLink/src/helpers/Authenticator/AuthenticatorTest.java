package helpers.Authenticator;
import model.User.User;
import model.User.UserDAO;
import helpers.UserRole;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AuthenticatorTest {
    @Test
    void authenticateUser() {
        assertAll(() -> {
            String email1 = "johndoe@my.yorku.ca";
            String email2 = "foobar@my.yorku.ca";
            String password1 = "12345";
            String password2 = "67890";

            User userJohn = new User.UserBuilder(email1, password1)
                    .setFirstName("John")
                    .setLastName("Doe")
                    .setRole(UserRole.STUDENT)
                    .build();

            UserDAO.add(userJohn);
            assertTrue(Authenticator.authenticateUser(email1, password1));
            assertFalse(Authenticator.authenticateUser(email1, password2));
            assertFalse(Authenticator.authenticateUser(email2, password1));
            UserDAO.delete(email1);
            assertFalse(Authenticator.authenticateUser(email1, password1));
        });
    }

    @Test
    void hasUser() {
        assertAll(() -> {
            String email1 = "johndoe@my.yorku.ca";
            String password1 = "12345";

            // delete user if it exists
            UserDAO.delete(email1);

            assertFalse(Authenticator.authenticateUser(email1, password1));

            User userJohn = new User.UserBuilder(email1, password1)
                    .setFirstName("John")
                    .setLastName("Doe")
                    .setRole(UserRole.STUDENT)
                    .build();

            UserDAO.add(userJohn);
            assertTrue(Authenticator.authenticateUser(email1, password1));

            // reset DB
            UserDAO.delete(email1);
            assertFalse(Authenticator.authenticateUser(email1, password1));
        });
    }
}
