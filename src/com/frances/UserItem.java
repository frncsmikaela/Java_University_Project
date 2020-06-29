package com.frances;

public class UserItem{
	private String id;
	private String firstName;
	private String lastName;
	private String birthDate;
	private String age;
	private String groupOrSalary;
	
	UserItem(String id, String firstName, String lastName, String birthDate, String age, String groupOrSalary) {
		this.setId(id);
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setBirthDate(birthDate);
		this.setAge(age);
		this.setGroupOrSalary(groupOrSalary);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getGroupOrSalary() {
		return groupOrSalary;
	}

	public void setGroupOrSalary(String groupOrSalary) {
		this.groupOrSalary = groupOrSalary;
	}
}
