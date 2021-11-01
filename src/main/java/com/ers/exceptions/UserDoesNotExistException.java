package com.ers.exceptions;

public class UserDoesNotExistException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public UserDoesNotExistException() {
		super("No user by that username exists.");
	}

}
