package com.frances;

public enum AccessLevel {
	BASIC(1, "Basic"),
	EDITOR(2, "Editor"),
	ADMIN(3, "Admin");

	private final int code;
	private final String name;

	private AccessLevel(int code, String name) {
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

