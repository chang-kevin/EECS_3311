package helpers.Authenticator;

import model.User.User;
import model.User.UserDAO;

import java.sql.SQLException;

/**
 * Authenticates the user by checking if the user input password matches the known user
 * password in the database.
 */
public class Authenticator {
    public static boolean authenticateUser(String username, String password) throws SQLException {
        User user = UserDAO.getUser(username);
        if (user != null) {
            return user.getPassword().equals(password);
        }
        return false;
    }
}
