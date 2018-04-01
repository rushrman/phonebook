package com.plivo.phonebook.persistance.in.mem;

import java.util.Map;

import com.plivo.phonebook.entity.Contact;

public interface ContactsFactory {
	public Map<String,Contact> getAllContact();
	public Contact getContact(String emailId);
	public void addContact(String emailId, Contact contact);
	public void deleteContact(String emailId);
	public int getcount();
}
