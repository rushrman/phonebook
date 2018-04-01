package com.plivo.phonebook.auth;

import java.util.StringTokenizer;

public class Authentication {
	
	public boolean auth(String authcred) {
		return authenticate(authcred);
	}

	private boolean authenticate(String authCredentials) {

		if (null == authCredentials)
			return false;
		
		final StringTokenizer tokenizer = new StringTokenizer(authCredentials, ":");
		final String username = tokenizer.nextToken();
		final String password = tokenizer.nextToken();

		// we have fixed the userid and password as admin
		// call some UserService/LDAP here
		boolean authenticationStatus = "admin".equals(username) && "admin".equals(password);
		return authenticationStatus;
	}
}
