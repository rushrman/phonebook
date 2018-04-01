package com.plivo.phonebook.services;

import java.util.List;

import com.plivo.phonebook.entity.Contact;
import com.plivo.phonebook.exceptions.ContactNotFoundException;
import com.plivo.phonebook.exceptions.DuplicateContactException;
import com.plivo.phonebook.exceptions.IllegalContactException;

public interface PhoneBookService {
public List<Contact> getAllContact() throws Exception;
public Contact getContact(String emailId) throws ContactNotFoundException;
public void addContact(Contact contact) throws DuplicateContactException;
public Contact updateContact(Contact contact) throws IllegalContactException;
public void deleteContact(String emailId) throws ContactNotFoundException;
}
