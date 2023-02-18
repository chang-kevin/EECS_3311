package model;

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

    public void addUser(User user) {
        userMap.put(user.getEmail(), user);
    }

    public User getUser(String email) {
        return userMap.get(email);
    }
}
