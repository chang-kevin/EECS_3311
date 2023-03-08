package model.User;

public class UserSettings {
    private static UserSettings instance;
    private static String currentTheme;

    private UserSettings() {}

    public static synchronized UserSettings getInstance() {
        if (instance == null) {
            instance = new UserSettings();
        }
        return instance;
    }

    public String getUserTheme() {
        return currentTheme;
    }

    public static void setUserTheme(String theme) {
        currentTheme = theme;
    }
}
