package com.plivo.phonebook.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.plivo.phonebook.auth.Authentication;
import com.plivo.phonebook.entity.Contact;
import com.plivo.phonebook.exceptions.ContactNotFoundException;
import com.plivo.phonebook.services.PhoneBookService;
import com.plivo.phonebook.services.PhoneBookServiceImpl;

/**
 * Root resource (exposed at "/phonebook/contacts" path)
 */
@Path("/phonebook/contacts")
public class MyResource {
	private PhoneBookService phoneBook = new PhoneBookServiceImpl();
	private Authentication auth = new Authentication();

	/**
	 * @param authString
	 * 
	 * @return returns maximum 10 contacts from the phonebook.
	 */
	@GET
	@Path("all")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getContacts(@HeaderParam("authorization") String authString) {
		try {
			List<Contact> contact = null;
			if (auth.auth(authString))
				contact = phoneBook.getAllContact();
			else
				Response.status(Response.Status.UNAUTHORIZED).build();

			GenericEntity<List<Contact>> entity = new GenericEntity<List<Contact>>(contact) {
			};
			return Response.ok(entity).build();
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}

	/**
	 * Searches in phonebook for a contact that maches the GET reqeust's emailId
	 * and if such a contact exists, returns it.
	 *
	 * @param emailId
	 *            - the contact id to be looked up in the phone book.
	 * @return the contact that matches request's emailId, if such contact
	 *         exists.
	 * @throws ContactNotFoundException
	 *             if no contact is found with this emailId.
	 */
	@GET
	@Path("{emailId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getContact(@PathParam("emailId") String emailId, @HeaderParam("authorization") String authString)
			throws ContactNotFoundException {
		try {
			Contact contact = null;
			if (auth.auth(authString))
				contact = phoneBook.getContact(emailId);
			else
				Response.status(Response.Status.UNAUTHORIZED).build();
			return Response.ok(contact).build();
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}

	/**
	 *
	 * @param contact
	 *            - the contact to be added in the phone book.
	 * @return Response which has the same details with the contact provided,
	 *         but also the unique id that identifies it in the phone book.
	 */
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addContact(Contact contact, @HeaderParam("authorization") String authString) {
		try {
			if (auth.auth(authString))
				phoneBook.addContact(contact);
			else
				Response.status(Response.Status.UNAUTHORIZED).build();
			return Response.ok(contact).status(Response.Status.CREATED).build();
		} catch (Exception e) {
			return Response.status(Response.Status.NOT_MODIFIED).entity(e.getMessage()).build();
		}
	}

	/**
	 * Updates the contact under the specific URI.
	 *
	 * @param Contact
	 *            - the Contact object to be update.
	 */
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateContact(Contact contact, @HeaderParam("authorization") String authString) {
		try {
			Contact contactNew = null;
			if (auth.auth(authString))
				contactNew = phoneBook.updateContact(contact);
			else
				Response.status(Response.Status.UNAUTHORIZED).build();
			return Response.ok(contactNew).build();
		} catch (Exception e) {
			return Response.status(Response.Status.NOT_MODIFIED).entity(e.getMessage()).build();
		}
	}

	/**
	 * Deletes the contact under the specific URI.
	 *
	 * @param emailId
	 *            - the emailId of the contact to be deleted.
	 */
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteContact(String emailId, @HeaderParam("authorization") String authString) {
		try {
			if (auth.auth(authString))
				phoneBook.deleteContact(emailId);
			else
				Response.status(Response.Status.UNAUTHORIZED).build();
			return Response.ok().status(Response.Status.NO_CONTENT).build();
		} catch (Exception e) {
			return Response.status(Response.Status.NOT_MODIFIED).entity(e.getMessage()).build();
		}
	}
}
