package com.queempanadas.exceptions;

public class FieldValidationException extends AbstractException {
    public FieldValidationException(String field, String reason) {
        super("Field " + field + " failed to be validated. " + reason);
    }
}
