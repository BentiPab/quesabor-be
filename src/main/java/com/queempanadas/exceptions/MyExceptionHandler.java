package com.queempanadas.exceptions;

import com.queempanadas.model.response.ErrorResponse;
import jakarta.servlet.ServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;

@ControllerAdvice
public class MyExceptionHandler {


    @ExceptionHandler(HttpMessageNotReadableException.class)
    protected ResponseEntity<Object> handleConflict(
            HttpMessageNotReadableException ex, ServletRequest request) throws IOException {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value())
                .body(errorResponse);
    }

    @ExceptionHandler(ProductsNotFoundException.class)
    protected ResponseEntity<Object> handleConflict(
            ProductsNotFoundException ex, ServletRequest request) throws IOException {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value())
                .body(errorResponse);
    }

    @ExceptionHandler(PromosNotFoundException.class)
    protected ResponseEntity<Object> handleConflict(
            PromosNotFoundException ex, ServletRequest request) throws IOException {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value())
                .body(errorResponse);
    }



}
