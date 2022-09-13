package com.chuyashkou.hotels_booking.exception;

public class ConnectionPoolException extends Exception {

    /**
     * Constructs a new exception with the specified detail message and
     * cause.
     *
     * @param message is a message the detail message
     * @param cause   the cause
     */
    public ConnectionPoolException(String message, Throwable cause) {
        super(message, cause);
    }
}
