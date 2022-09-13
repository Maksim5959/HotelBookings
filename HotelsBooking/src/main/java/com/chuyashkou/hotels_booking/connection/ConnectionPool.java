package com.chuyashkou.hotels_booking.connection;

import com.chuyashkou.hotels_booking.exception.ConnectionPoolException;
import com.chuyashkou.hotels_booking.message.ExceptionMessage;
import com.chuyashkou.hotels_booking.message.LoggerMessage;
import com.chuyashkou.hotels_booking.util.DbMappingConstant;
import com.chuyashkou.hotels_booking.util.DbPropertiesManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {

    private static final Lock LOCK = new ReentrantLock();
    private static final AtomicBoolean IS_POOL_CREATED = new AtomicBoolean(false);
    private static ConnectionPool instance;
    private final BlockingQueue<ProxyConnection> connections;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private ConnectionPool() throws ConnectionPoolException {
        loadDriver();
        int poolSize = Integer.parseInt(DbPropertiesManager.getProperty(DbMappingConstant.DB_POOL_SIZE.getKey()));
        this.connections = new LinkedBlockingDeque<>(poolSize);
        for (int i = 0; i < poolSize; i++) {
            connections.offer(ConnectionManager.getInstance().getProxyConnection());
        }
    }

    /**
     * Create thread-safety <code>ConnectionPool</code> which consist of {@code ProxyConnection}
     *
     * @return thread-safety <code>ConnectionPool</code>
     * @throws RuntimeException if cant load JDBC driver or <code>ConnectionPool</code>
     *                          properties
     */
    public static ConnectionPool getInstance() {
        if (!IS_POOL_CREATED.get()) {
            try {
                LOCK.lock();
                if (instance == null) {
                    instance = new ConnectionPool();
                    IS_POOL_CREATED.set(true);
                }
            } catch (ConnectionPoolException e) {
                throw new RuntimeException(ExceptionMessage.CANT_CREATE_CONNECTION_POOL_MESSAGE, e);
            } finally {
                LOCK.unlock();
            }
        }
        return instance;
    }

    private void loadDriver() throws ConnectionPoolException {
        try {
            Class.forName(DbPropertiesManager.getProperty(DbMappingConstant.DB_DRIVER.getKey()));
        } catch (ClassNotFoundException e) {
            throw new ConnectionPoolException(ExceptionMessage.CANT_LOAD_JDBC_DRIVER_MESSAGE, e);
        }
    }

    /**
     * Take {@code ProxyConnection} from pool
     *
     * @return <code>ProxyConnection</code> from {@code ConnectionPool}
     * @throws ConnectionPoolException if {@code ProxyConnection}
     *                                 interrupted while waiting
     */
    public ProxyConnection getConnection() throws ConnectionPoolException {
        ProxyConnection connection;
        try {
            connection = connections.take();
        } catch (InterruptedException e) {
            throw new ConnectionPoolException(ExceptionMessage.CANT_TAKE_CONNECTION_MESSAGE, e);
        }
        return connection;
    }

    /**
     * Return {@code ProxyConnection} back into pool, setAutoCommit(true)
     * and setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED)
     * before returning
     *
     * @param connection is a {@code ProxyConnection}
     */
    public void releaseConnection(ProxyConnection connection) {
        try {
            if (!connection.getAutoCommit()) {
                connection.setAutoCommit(true);
                connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            }
        } catch (SQLException e) {
            logger.error(LoggerMessage.RELEASE_CONNECTION_ERROR, e);
        }
        connections.offer(connection);
    }

    /**
     * Close all {@code ProxyConnection} into {@code ConnectionPool}
     * and deregister JDBC driver
     */
    public void closePool() {
        try {
            while (!connections.isEmpty()) {
                connections.poll().closeConnection();
            }
            Enumeration<Driver> drivers = DriverManager.getDrivers();
            while (drivers.hasMoreElements()) {
                DriverManager.deregisterDriver(drivers.nextElement());
            }
        } catch (SQLException e) {
            logger.error(LoggerMessage.CLOSE_CONNECTION_POOL_ERROR, e);
        }
    }
}
