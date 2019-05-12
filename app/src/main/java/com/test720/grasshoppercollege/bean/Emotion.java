package com.test720.grasshoppercollege.bean;

public class Emotion {

	private String code = null;
	
	private String name = null;
	
	public Emotion() {}

	public Emotion(String code, String name) {
		this.code = code;
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
