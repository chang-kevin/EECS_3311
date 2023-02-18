package view;

import model.*;

import java.util.HashMap;
import java.util.Map;

public class UserList {
    private static UserList userList;
    private static Map<String, User> userMap = new HashMap<>();

    private  UserList() {
        // generate admins (this should be in a helper class)

    };

    public static synchronized UserList getInstance() {
        if (userList == null) {
            userList = new UserList();
        }
        return userList;
    }

    public void addUser(User user) {
        userMap.put(user.getEmail(), user);
    }

    // this should be in a different class
    public boolean authenticateUser(String email, String password) {
        User user = userMap.get(email);
        if (user != null) {
            return user.getPassword().equals(password);
        }
        return false;
    }

    public User search(String email) {
        User user = userMap.get(email);
        return user;
    }
}
