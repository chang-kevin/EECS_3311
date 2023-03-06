package model.User;

import java.util.HashMap;
import java.util.Map;

public class UserList {
    private static UserList userList;
    private static Map<String, User> userMap = new HashMap<>();

    private  UserList() {};

    public static synchronized UserList getInstance() {
        if (userList == null) {
            userList = new UserList();
        }
        return userList;
    }

    public void addUser(User user) throws Exception {
        String username = user.getUsername();
        if (!doesUserExist(username)) {
            userMap.put(username, user);
        } else {
            throw new Exception("User exists already!");
        }
    }

    private boolean doesUserExist(String email) {
        return getUser(email) != null;
    }

    public User getUser(String email) {
        return userMap.get(email);
    }
}
