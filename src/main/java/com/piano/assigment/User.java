package com.piano.assigment;

public class User {

	private String uid;
	private String email;
	private String firstName;
	private String lastName;
	
	public String getUid() {
		return uid;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	
}

/*
 * {'code': 0, 'ts': 1668104556, 'limit': 1, 'offset': 0, 'total': 1, 'count':
 * 1, 'users': [{'first_name': None, 'last_name': None, 'personal_name': None,
 * 'email': 'user_0@example.com', 'uid': 'PNISGXVjCpnhexk', 'image1': None,
 * 'create_date': 1551098072, 'reset_password_email_sent': True,
 * 'custom_fields': []}]}
 */