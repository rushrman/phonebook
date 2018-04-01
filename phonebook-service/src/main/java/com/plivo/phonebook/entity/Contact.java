package com.plivo.phonebook.entity;

/**
 * A class containing all the useful information of a contact in the phone book.
 * <p/>
 * When a phone book user wants to insert a new contact in the book, he will only be
 * concerned about the information included in this class.
 *
 * @author <a href="mailto:travelling.with.code@gmail.com">Alex</a>
 */
public class Contact implements Comparable{

    private String name;
    private String surname;
    private String phone;
    private String emailId;

    public Contact(String name, String surname, String phone,String emailId) {
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.emailId=emailId;
    }

    public Contact(Contact contact) {
        this.name = contact.getName();
        this.surname = contact.getSurname();
        this.phone = contact.getPhone();
        this.emailId=contact.getEmailId();
    }

    public Contact() {
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	@Override
	public int compareTo(Object o) {
		Contact old = (Contact) o;
		return this.getName().compareTo(old.getName());
	}

}
