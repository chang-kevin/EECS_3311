package helpers.Authenticator;

import model.User.User;
import model.User.UserDAO;


import java.sql.SQLException;

public class authusername {
    public static boolean authuser(String username) throws SQLException {
        User user = UserDAO.getUser(username);
        if (user != null) {
            return user.getUsername().equals(username);
        }
        return false;
    }
}
