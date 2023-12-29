package com.queempanadas.exceptions;

public class NoAuthException extends AbstractException {
    public NoAuthException() {
        super("No token provided");
    }
}
