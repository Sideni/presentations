package com.csgames.exodus.util;

import java.util.InvalidPropertiesFormatException;

import static com.csgames.exodus.model.User.*;
import static com.csgames.exodus.util.HibernateUtil.*;

public class LoggedInUserUtil {
    private LoggedInUserUtil(){}

    public static String getUsername(String token) throws InvalidPropertiesFormatException {
        if (! isTokenValid(token))
            throw new InvalidPropertiesFormatException("The token is not valid...");

        String[] parts = token.split(SPLITTER);
        return parts[TOK_USERNAME];
    }

    public static String getRole(String token) throws InvalidPropertiesFormatException {
        if (! isTokenValid(token))
            throw new InvalidPropertiesFormatException("The token is not valid...");

        String[] parts = token.split(SPLITTER);
        return parts[TOK_ROLE];
    }

    public static boolean isAdmin(String token) throws InvalidPropertiesFormatException {
        if (! isTokenValid(token))
            throw new InvalidPropertiesFormatException("The token is not valid...");

        String role = getRole(token);
        return role.equals(ADMIN);
    }
}
