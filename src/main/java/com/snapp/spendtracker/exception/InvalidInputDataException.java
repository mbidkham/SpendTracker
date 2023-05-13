package com.snapp.spendtracker.exception;

public class InvalidInputDataException extends RuntimeException{
    public InvalidInputDataException() {
    }

    public InvalidInputDataException(String message) {
        super(message);
    }
}
