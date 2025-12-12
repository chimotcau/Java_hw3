package org.example.exception;

public class DocumentNotFoundException extends BaseException {
    public DocumentNotFoundException(String id) {
        super("No document with id " + id);
    }
}
