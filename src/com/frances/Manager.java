package com.frances;

import java.time.LocalDate;

public class Manager extends User{

	private static final long serialVersionUID = 103;

	public Manager(String username, String password, int id, String firstname, String lastname, LocalDate birthdate
			) {
		super(username, password, id, firstname, lastname, birthdate, AccessLevel.ADMIN);
	}
	
	
}
