package com.chuyashkou.hotels_booking.util;

import java.util.ResourceBundle;

public final class DbPropertiesManager {

    private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("connection");

    /**
     * load properties file which contains
     * URL, login, password, pool size and JDBC driver name
     * required for create connection with database
     */
    public static String getProperty(String key) {
        return BUNDLE.getString(key);
    }
}
