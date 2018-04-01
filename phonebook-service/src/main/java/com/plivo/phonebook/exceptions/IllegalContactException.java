package com.plivo.phonebook.exceptions;

public class IllegalContactException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public IllegalContactException(String message) {
        super(message);
    }

}
