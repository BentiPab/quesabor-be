package com.queempanadas.utils;

import com.queempanadas.exceptions.AbstractException;
import com.queempanadas.exceptions.JPAException;
import com.queempanadas.exceptions.JPAExceptionType;
import com.queempanadas.exceptions.UnknownException;
import jakarta.persistence.PersistenceException;
import org.hibernate.PropertyValueException;
import org.hibernate.exception.ConstraintViolationException;

public class PersistenceUtils {
    public static void parsePersistenceException(PersistenceException e) throws AbstractException {
        if (e.getCause() instanceof ConstraintViolationException) {
            if (e.getCause()
                    .getCause()
                    .getMessage()
                    .toLowerCase()
                    .contains("unique")) {
                throw new JPAException(e, JPAExceptionType.UNIQUE);
            }
        } else if (e.getCause() instanceof PropertyValueException) {
            throw new JPAException(e, JPAExceptionType.NULL);
        } else {
            throw new UnknownException(e);
        }
    }
}
