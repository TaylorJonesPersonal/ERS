package com.ers.exceptions;

public class WrongInviteCodeException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public WrongInviteCodeException() {
		super("Wrong invite code received.");
	}

}
