package com.example.yousavebackend.exceptions;

public class PostAlreadyExistsException extends RuntimeException {
    public PostAlreadyExistsException(String message) {
        super(message);
    }
}
