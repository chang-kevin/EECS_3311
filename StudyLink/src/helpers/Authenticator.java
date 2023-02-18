package helpers;

import model.User;
import model.UserList;

public class Authenticator {
    public static boolean authenticateUser(String email, String password) {
        User user = UserList.getInstance().getUser(email);
        if (user != null) {
            return user.getPassword().equals(password);
        }
        return false;
    }
}
