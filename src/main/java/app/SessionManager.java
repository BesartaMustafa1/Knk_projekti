package app;

import Model.User;

import java.util.Locale;

public class SessionManager {
    private static User currentUser;
    private static String lastAttemptedPage;
    private static Locale currentLocale = Locale.getDefault();

    public static void setUser(User user) {
        currentUser = user;
    }

    public static User getUser() {
        return currentUser;
    }

    public static boolean isLoggedIn() {
        return currentUser != null;
    }

    public static void logout() {
        currentUser = null;
        lastAttemptedPage = null;
        currentLocale = Locale.getDefault();
    }

    public static void setLastAttemptedPage(String page) {
        lastAttemptedPage = page;
        System.out.println("Set last attempted page: " + page);
    }

    public static String getLastAttemptedPage() {
        System.out.println("Getting last attempted page: " + lastAttemptedPage);
        return lastAttemptedPage;
    }
    public static void setLocale(Locale locale) {
        currentLocale = locale;
    }
    public static Locale getLocale() {
        return currentLocale;
    }
}
