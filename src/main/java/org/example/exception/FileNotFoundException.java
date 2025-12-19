package org.example.exception;

public class FileNotFoundException extends BaseException {
    public FileNotFoundException(String fileName) {
        super("File not found: " + fileName);
    }
}