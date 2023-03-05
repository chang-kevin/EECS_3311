package helpers.Authenticator;

import model.User.User;
import model.User.UserList;

/**
 * Authenticates the user by checking if the user input password matches the known user
 * password in the database.
 */
public class Authenticator {
    public static boolean authenticateUser(String email, String password) {
        User user = UserList.getInstance().getUser(email);
        if (user != null) {
            return user.getPassword().equals(password);
        }
        return false;
    }
}
