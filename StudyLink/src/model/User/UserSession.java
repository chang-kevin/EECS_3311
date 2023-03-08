package model.User;

public class UserSession {
    private static UserSession instance;
    private static User currentUser;

    private UserSession() {}

    /**
     * Synchronized ensures that only one thread can create an instance of the UserSession class
     * at a time, preventing multiple instances from being created accidentally.
     */
    public static synchronized UserSession getInstance() {
        if (instance == null) {
            instance = new UserSession();
        }
        return instance;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User user) {
        currentUser = user;
    }
}
