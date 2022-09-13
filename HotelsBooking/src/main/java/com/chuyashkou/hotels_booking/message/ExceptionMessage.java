package com.chuyashkou.hotels_booking.message;


public final class ExceptionMessage {
    public static final String EXCEPTION_DURING_EXECUTE_COMMND = "Exception during execute command";
    public static final String CANT_TAKE_CONNECTION_MESSAGE = "Can't take connection from pool";
    public static final String CANT_LOAD_JDBC_DRIVER_MESSAGE = "Can't load JDBC driver";
    public static final String CANT_CREATE_CONNECTION_POOL_MESSAGE = "Can't create connection pool!";
    public static final String CONNECTION_POOL_EXCEPTION_MESSAGE = "Connection pool error";
    public static final String DAO_EXCEPTION_MESSAGE = "Database error";
    public static final String TRANSACTION_EXCEPTION_MESSAGE = "Transaction error";
    public static final String SERVICE_EXCEPTION_MESSAGE = "Dao layer error";
    public static final String COMMAND_EXCEPTION_MESSAGE = "Service layer error";

    private ExceptionMessage() {
    }
}
