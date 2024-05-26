package com.anthonyo.kfc.kfc.exceptions;

public class InternalServerError extends RuntimeException{
    public InternalServerError(String message) {
        super(message);
    }
}
