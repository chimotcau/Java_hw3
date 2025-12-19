package org.example.exception;

public class JsonFormatException extends RuntimeException {
    public JsonFormatException(String message) {
        super("error Json format " + message);
    }
}
