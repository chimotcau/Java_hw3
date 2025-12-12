package org.example.exception;

public class MissingFieldException extends BaseException {
    public MissingFieldException(String field_name) {
        super("Missing field " + field_name);

    }
}
