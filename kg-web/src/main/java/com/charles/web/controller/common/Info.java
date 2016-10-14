package com.charles.web.controller.common;

public class Info {
	
	public final static Integer OK = 1;
	
	public final static Integer FAILURE = 0;
	
	private boolean ok;
	
	private String message;
	
	public Info(boolean isOK) {
		this.ok = isOK;
	}
	
	public Info(boolean isOK, String message) {
		this.ok = isOK;
		this.message = message;
	}
	public boolean isOk() {
		return ok;
	}

	public void setOk(boolean ok) {
		this.ok = ok;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
