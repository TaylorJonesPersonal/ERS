package com.ers.exceptions;

public class SignUpFailedException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public SignUpFailedException(){
		super("Your signup has failed. Please try again.");
	}
	
}
