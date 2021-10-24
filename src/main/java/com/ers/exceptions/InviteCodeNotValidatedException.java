package com.ers.exceptions;

public class InviteCodeNotValidatedException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InviteCodeNotValidatedException() {
		super("User has not validated their invite code.");
	}

}
