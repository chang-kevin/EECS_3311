package helpers.Authenticator;
import model.User.User;
import model.User.UserRole;
import model.User.Userdao;
import static org.junit.jupiter.api.Assertions.*;
class AuthenticatorTest {

    @org.junit.jupiter.api.Test
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

            Userdao.adduser(userJohn);


            assertTrue(Authenticator.authenticateUser(email1, password1));
            assertFalse(Authenticator.authenticateUser(email1, password2));
            assertFalse(Authenticator.authenticateUser(email2, password1));
            Userdao.delete(email1);
            assertFalse(Authenticator.authenticateUser(email1, password1));
        });
    }
