package com.plivo.phonebook.exceptions;

public class ContactNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ContactNotFoundException(String contactId) {
        super("Could not find contact '" + contactId + "'.");
    }

}
