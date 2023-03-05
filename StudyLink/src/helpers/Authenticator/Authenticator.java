package helpers.Authenticator;

import model.User.User;
import model.User.UserList;

public class Authenticator {
    public static boolean authenticateUser(String email, String password) {
        User user = UserList.getInstance().getUser(email);
        if (user != null) {
            return user.getPassword().equals(password);
        }
        return false;
    }
}
