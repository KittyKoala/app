package com.kangyonggan.app.exception;

/**
 * @author kangyonggan
 * @since 8/13/18
 */
public class ValidException extends RuntimeException {

    public ValidException() {
    }

    public ValidException(String message) {
        super(message);
    }

    public ValidException(String message, Throwable cause) {
        super(message, cause);
    }

    public ValidException(Throwable cause) {
        super(cause);
    }

    public ValidException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
