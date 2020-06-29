package com.frances;

import java.time.LocalDate;
import java.util.ArrayList;

public class UserDB {
	// This has list of User and has a method that takes the username
	// and password of the User and checks if it exists
	ArrayList<User> userList;

	public UserDB() {
		userList = new ArrayList<User>();
		initialiseUsers();
	}

	private void initialiseUsers() {
		// list of students
		addStudent("emma01@gmail.com", "emma01", 1, "Emma", "Smith", LocalDate.of(1997, 12, 04), "IT-02-A");
		addStudent("jack02@gmail.com", "jack02", 2, "Jack", "Brown", LocalDate.of(1993, 8, 07), "IT-02-A");
		addStudent("michael03@hotmail.com", "lisa04", 4, "Lis", "Jones", LocalDate.of(2000, 4, 29), "IT-02-A");
		addStudent("john05@hotmail.com", "john05", 5, "John", "Miller", LocalDate.of(2001, 10, 27), "IT-02-A");
		
		//list of teachers 
		addTeacher("david01@hotmail.com", "david01", 11, "David", "Smith", LocalDate.of(1965, 6, 15), 5200.0);
		addTeacher("sophy02@gmail.com", "sophy02", 12, "Sophy", "Anderson", LocalDate.of(1987, 1, 06), 3500.0);
		addTeacher("james03@hotmail.com", "james03", 13, "James", "Jordon", LocalDate.of(1956, 3, 19), 6100.0);
		addTeacher("susan04@gmail.com", "susan04", 14, "Susan", "Jackson", LocalDate.of(1978, 12, 25), 4520.0);
		addTeacher("mary05@gmail.com", "mary05", 15, "Mary", "Lee", LocalDate.of(1971, 9, 04), 5130.0);
		
		// Some managers
		addManager("job03@hotmail.com", "job03", 100, "Job", "Lee", LocalDate.of(1970, 9, 04));
		addManager("peter05@gmail.com", "peter05", 101, "Peter", "van Dijk", LocalDate.of(1974, 9, 04));
	}

	private void addStudent(String userName, String password, int id, String firstName, String lastName,
			LocalDate birthDate, String group) {
		Student s = new Student(userName, password, id, firstName, lastName, birthDate, group);
		userList.add(s);
		setRndGrade(s, CourseEnum.CSHARP);
		setRndGrade(s, CourseEnum.JAVA);
		setRndGrade(s, CourseEnum.PYTHON);
		setRndGrade(s, CourseEnum.PHP);
	}
	
	private void addTeacher(String userName, String password, int id, String firstName, String lastName, LocalDate birthDate, double salary) {
		Teacher t = new Teacher(userName, password, id, firstName, lastName, birthDate, salary);
		userList.add(t);
	}

	private void addManager(String userName, String password, int id, String firstName, String lastName, LocalDate birthDate) {
		Manager t = new Manager(userName, password, id, firstName, lastName, birthDate);
		userList.add(t);
	}
	
	private void setRndGrade(Student s, CourseEnum c) {
		double rnd = Math.random();
		int rndGrade = (int) (rnd * 70 + 31);
		s.setGrade(c, rndGrade);
	}
	
	//this checks if the username and password is correct 
	//it takes a username and password
	//returns true if username and pwd is correct 
	public User checkUserNamePwd(String username, String password) {
		for(User u : userList) {
			if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
				return u;
			}
		}
		return null;
	}
	
	// Returns all students from the database
	public ArrayList<Student> getStudents(){
		ArrayList<Student> studentList = new ArrayList<Student>();
		for(User u : userList) {
			if (u instanceof Student) {
				Student s = (Student) u;
				studentList.add(s);
			}
		}
		return studentList;
	}
	
	// Returns all teachers from the database
	public ArrayList<Teacher> getTeachers(){
		ArrayList<Teacher> teacherList = new ArrayList<Teacher>();
		for(User u : userList) {
			if(u instanceof Teacher) {
				Teacher t = (Teacher) u;
				teacherList.add(t);
			}
		}
		return teacherList;
	}

	public User findUser(int id) {
		for (User u: userList) {
			if (u.getId() == id) {
				return u;
			}
		}
		return null;
	}

	public void updateStudent(Student s) {
		for (User u: userList) {
			if (u.getId() == s.getId()) {
				Student sdb = (Student)u;
				sdb.setGrade(CourseEnum.JAVA, s.getGrade(CourseEnum.JAVA));
				sdb.setGrade(CourseEnum.CSHARP, s.getGrade(CourseEnum.CSHARP));
				sdb.setGrade(CourseEnum.PYTHON, s.getGrade(CourseEnum.PYTHON));
				sdb.setGrade(CourseEnum.PHP, s.getGrade(CourseEnum.PHP));
				return;
			}
		}
	}

	public void insertStudent(Student s) {
		userList.add(s);
		
	}

}
