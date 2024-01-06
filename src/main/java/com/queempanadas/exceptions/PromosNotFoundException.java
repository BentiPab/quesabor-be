package com.queempanadas.exceptions;

public class PromosNotFoundException extends AbstractException{
    public PromosNotFoundException() {
        super("No products found with given ids");
    }
}
