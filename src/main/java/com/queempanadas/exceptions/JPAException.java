package com.queempanadas.exceptions;

public class JPAException extends AbstractException {
    private JPAExceptionType exceptionType;

    public JPAException(Throwable cause, JPAExceptionType exceptionType) {
        super(cause);
    }

    public JPAExceptionType getExceptionType() {
        return this.exceptionType;
    }
}
