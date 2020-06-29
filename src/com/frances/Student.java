package com.frances;

import java.time.LocalDate;
import java.util.Hashtable;

public class Student extends User {

	private static final long serialVersionUID = 102;
	private String group;
	//ArrayList<Course> courseList;
	private Hashtable<CourseEnum, Integer> gradeMap;
	
	public Student(String userName, String password, int id, String firstName, String lastName, LocalDate birthDate,
			String group) {
		super(userName, password, id, firstName, lastName, birthDate, AccessLevel.BASIC);
		this.setGroup(group);
		//this.courseList = new ArrayList<Course>();
		this.gradeMap = new Hashtable<CourseEnum, Integer>();
	}

	public void setGrade(CourseEnum courseEnum, int grade) {
		//This will set the grade for every courses of the student 
		gradeMap.put(courseEnum, grade);
	}
	
	//this will get a grade from a specific course
	public int getGrade(CourseEnum specificCourse) {
		Integer v = gradeMap.get(specificCourse);
		if (v == null) {
			System.err.println("error! the student has not been graded for this course!");
			v = 0;
		}
		return v;
	}
	
	public int getNumberOfRetakes() {
		int retakes = 4;
		if (isPassed(CourseEnum.JAVA))
			retakes--;
		if (isPassed(CourseEnum.CSHARP))
			retakes--;
		if (isPassed(CourseEnum.PYTHON))
			retakes--;
		if (isPassed(CourseEnum.PHP))
			retakes--;
		return retakes;
	}
	
	public boolean isPassed(CourseEnum courseEnum) {
		return getGrade(courseEnum) >= 55;
	}
	
	// Returns true if the student is passed for all subjects, false otherwise
	public boolean isPassed() {
		// Passed if there is no retake
		return getNumberOfRetakes() == 0;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}
}