package com.frances;

public enum CourseEnum {
	JAVA(1, "Java"),
	CSHARP(2, "Csharp"),
	PYTHON(3, "Phython"),
	PHP(4, "PHP");
	
	private final int code;
	private final String name;

	private CourseEnum(int code, String name) {
		this.code = code;
		this.name = name;
	}
	
	public int getCode() {
		return code;
	}
	
	public String getName() {
		return name;
	}
}
