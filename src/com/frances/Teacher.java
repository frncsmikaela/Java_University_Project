package com.frances;

import java.time.LocalDate;

public class Teacher extends User{

	private static final long serialVersionUID = 103;
	private double salary;
	public Teacher(String userName, String password, int id, String firstName, String lastName, LocalDate birthDate,
			double salary) {
		super (userName, password, id, firstName, lastName, birthDate, AccessLevel.EDITOR);
		this.setSalary(salary);
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}

}