package com.ctbe.product_service.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(Long id) {
        super("Product " + id + " not found");
    }
}
