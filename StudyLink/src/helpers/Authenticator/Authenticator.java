package helpers.Authenticator;

import model.User;
import model.UserDAOImplementation;
import model.UserList;

import java.sql.SQLException;

public class Authenticator {
    public static boolean authenticateUser(String email, String password) throws SQLException {
        User user = UserDAOImplementation.getUser(email);
        if (user != null) {
            return user.getPassword().equals(password) ;
        }
        return false;
    }
}
