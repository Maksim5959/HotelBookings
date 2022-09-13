package com.chuyashkou.hotels_booking.connection;

import com.chuyashkou.hotels_booking.exception.ConnectionPoolException;
import com.chuyashkou.hotels_booking.util.DbMappingConstant;
import com.chuyashkou.hotels_booking.util.DbPropertiesManager;

import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

    private static final ConnectionManager INSTANCE = new ConnectionManager();

    /**
     * @return {@code ConnectionManager} instance
     */
    public static ConnectionManager getInstance() {
        return INSTANCE;
    }

    /**
     * @return {@code ProxyConnection} is a connection (session) with a database.
     * @throws ConnectionPoolException if a database access error occurs
     *                                 or this method is called on a closed connection
     */
    public ProxyConnection getProxyConnection() throws ConnectionPoolException {
        try {
            return new ProxyConnection(DriverManager.getConnection(DbPropertiesManager.getProperty(DbMappingConstant.DB_URL.getKey()),
                    DbPropertiesManager.getProperty(DbMappingConstant.DB_USER_NAME.getKey()),
                    DbPropertiesManager.getProperty(DbMappingConstant.DB_USER_PASSWORD.getKey())));
        } catch (SQLException e) {
            throw new ConnectionPoolException("Database access error", e);
        }
    }
}
