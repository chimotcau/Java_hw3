package org.example.exception;

public class ValueTypeException extends BaseException {

    public ValueTypeException(String value_name, String value_type) {
        super("Not valid type for value"+ value_name + " " + value_type);
    }
}
