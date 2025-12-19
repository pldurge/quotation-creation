package com.example.carquotesystem.exception;

public class UnableToRegisterUser extends RuntimeException {
	private static final long serialVersionUID = 1L;
	public UnableToRegisterUser(String msg) {
		super(msg);
	}

}
