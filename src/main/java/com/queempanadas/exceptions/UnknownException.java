package com.queempanadas.exceptions;

public class UnknownException extends AbstractException {
    public UnknownException(Throwable cause) {
        super("Whoops! Something happened. Try again later.", cause);
    }
}
