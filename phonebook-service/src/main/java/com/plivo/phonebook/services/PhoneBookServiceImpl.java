package com.plivo.phonebook.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.plivo.phonebook.entity.Contact;
import com.plivo.phonebook.exceptions.ContactNotFoundException;
import com.plivo.phonebook.exceptions.DuplicateContactException;
import com.plivo.phonebook.exceptions.IllegalContactException;
import com.plivo.phonebook.persistance.in.mem.ContactsFactory;
import com.plivo.phonebook.persistance.in.mem.ContactsFactoryImpl;

public class PhoneBookServiceImpl implements PhoneBookService {
	private ContactsFactory contactsFactory = new ContactsFactoryImpl();

	@Override
	public List<Contact> getAllContact() throws Exception {
		List<Contact> list = null;
		int i = 10;
		int count = contactsFactory.getcount();
		if (count > 0) {
			list = new ArrayList<>();

			if (count < i)
				i = count;
			Map<String, Contact> map = contactsFactory.getAllContact();
			for (String emailId : map.keySet()) {
				list.add(map.get(emailId));
			}
		} else
			throw new Exception("Empty Phone Book !!");
		Collections.sort(list);
		return list.subList(0, i);
	}

	@Override
	public Contact getContact(String emailId) {
		if (contactsFactory.getAllContact().containsKey(emailId))
			return contactsFactory.getContact(emailId);
		else
			throw new ContactNotFoundException(emailId);
	}

	@Override
	public void addContact(Contact contact) {
		if (contactsFactory.getContact(contact.getEmailId()) != null)
			throw new DuplicateContactException(contact.getEmailId());
		else
			contactsFactory.addContact(contact.getEmailId(), contact);

	}

	@Override
	public Contact updateContact(Contact contactNew) {

		if (contactNew == null)
			throw new IllegalContactException("Empty Input!!");
		Contact contactOld = contactsFactory.getContact(contactNew.getEmailId());

		if (contactOld == null)
			throw new ContactNotFoundException(contactNew.getEmailId());
		else {
			if (contactNew.getName() != null)
				contactOld.setName(contactNew.getName());
			if (contactNew.getPhone() != null)
				contactOld.setPhone(contactNew.getPhone());
			if (contactNew.getSurname() != null)
				contactOld.setSurname(contactNew.getSurname());
			contactsFactory.addContact(contactNew.getEmailId(), contactOld);
		}
		return contactOld;
	}

	@Override
	public void deleteContact(String emailId) {
		if (emailId == null)
			throw new IllegalContactException("Empty Input!!");
		Contact contactOld = contactsFactory.getContact(emailId);
		if (contactOld == null)
			throw new ContactNotFoundException(emailId);

	}

}
