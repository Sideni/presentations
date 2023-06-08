package com.montrehack.app.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    public static final String QUOTE_S = "'";
    public static final String DOUBLE_QUOTE_S = "\"";
    public static final String BACKSLASH_S = "\\";
    public static final String PIPE_S = "|";
    public static final String COLON_S = ":";
    public static final String EMPTY_S = "";
    public static final String SPLITTER = BACKSLASH_S + PIPE_S;
    public static final String ERROR_S = "An error has occured...";
    public static final String RESTRICTED_S = "Restriction has been applied...";

    // Roles
    public static final String ADMIN = "Administrator";
    public static final String SCUMBAGS = "Pests";

    // States
    public static final String LOGGED_IN = "welcome";
    public static final String LOGGED_OUT = "GTFO!";
    public static final String STATE = "logged?";
    public static final String INVALID_LOGIN = "Invalid username or password... (GET REKT !!!)";
    public static final String INVALID_TOKEN = "Your token is invalid...";
    public static final String NOT_ADMIN = "You're not a damn admin, you plebs !";
    public static final String TOKEN = "token";

    // Creation
    public static final String EXISTS_ERROR = "This account is already taken...";
    public static final String PASSWORDS_NOT_MATCHING = "The two entered passwords do not match... You sound like a bright one...";

    // Panel
    public static final String THEME = "theme";
    public static final String STYLE = "style";

    private static final SessionFactory sessionFactory;

    static {
        try {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory(){
        return sessionFactory;
    }
}
