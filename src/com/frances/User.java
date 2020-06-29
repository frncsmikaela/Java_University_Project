package com.frances;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;


public class User implements Serializable {
	private static final long serialVersionUID = 101;
	
	private String username; 
	private String password;
	private int id;
	private String firstname; 
	private String lastname; 
	private LocalDate birthdate;
	private AccessLevel accesslvl = AccessLevel.BASIC;
	
	public User() {	
	}
	
	// In addition to the "bean" constructor without parameters, we define this one to more easily initialize a new instance.
	public User(String username, String password, int id, String firstname, String lastname, LocalDate birthdate, AccessLevel accesslvl) {
		this.setUsername(username);
		this.setPassword(password);
		this.setId(id);
		this.setFirstname(firstname); 
		this.setLastname(lastname);
		this.setBirthdate(birthdate);
		this.setAccesslvl(accesslvl);
		}
	
	public int getAge() {
		int age = 0;
		LocalDate now = LocalDate.now();
		Period diff = Period.between(birthdate, now);
		age = diff.getYears();
		return age;
		
	}


	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public LocalDate getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(LocalDate birthdate) {
		this.birthdate = birthdate;
	}

	public AccessLevel getAccesslvl() {
		return accesslvl;
	}

	public void setAccesslvl(AccessLevel accesslvl) {
		this.accesslvl = accesslvl;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
}
