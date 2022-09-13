package com.chuyashkou.hotels_booking.exception;

public class RepositoryException extends Exception {

    /**
     * Constructs a new exception with the specified detail message.
     *
     * @param message the detail message.
     */
    public RepositoryException(String message) {
        super(message);
    }

    /**
     * Constructs a new exception with {@code null} as its detail message.
     * The cause is not initialized
     */
    public RepositoryException() {
        super();
    }

    /**
     * Constructs a new exception with cause.
     *
     * @param cause the cause
     */
    public RepositoryException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructs a new exception with the specified detail message and
     * cause.
     *
     * @param message is a message the detail message
     * @param cause   the cause
     */
    public RepositoryException(String message, Throwable cause) {
        super(message, cause);
    }
}
