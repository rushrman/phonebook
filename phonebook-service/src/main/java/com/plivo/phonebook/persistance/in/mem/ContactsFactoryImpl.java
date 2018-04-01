package com.plivo.phonebook.persistance.in.mem;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import com.plivo.phonebook.entity.Contact;

public class ContactsFactoryImpl implements ContactsFactory{
	static private ConcurrentMap<String,Contact> phonebook = new ConcurrentHashMap<>();
	static private int count=0;
	@Override
	public Map<String, Contact> getAllContact() {
		return phonebook;
	}

	@Override
	public Contact getContact(String emailId) {
		return phonebook.get(emailId);
	}

	@Override
	public void addContact(String emailId, Contact contact) {
		phonebook.putIfAbsent(emailId, contact);
		count++;
	}

	@Override
	public void deleteContact(String emailId) {
		phonebook.remove(emailId);
		count--;
	}

	@Override
	public int getcount() {
		return count;
	}

}
