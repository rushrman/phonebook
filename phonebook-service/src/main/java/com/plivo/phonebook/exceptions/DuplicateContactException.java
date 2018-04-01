package com.plivo.phonebook.exceptions;

public class DuplicateContactException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public DuplicateContactException(String contactId) {
        super("Contact with id '" + contactId + "' already exists.");
    }

}
