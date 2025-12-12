package org.example.exception;

public abstract class BaseException extends RuntimeException {

    public BaseException(String notify) {
        super(notify);
    }

    public BaseException(String notify, Throwable cause) {
        super(notify, cause);
    }
}