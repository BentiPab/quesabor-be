package com.queempanadas.exceptions;

public class ProductsNotFoundException extends AbstractException {

    public ProductsNotFoundException() {
        super("No products found with given ids");
    }

}
