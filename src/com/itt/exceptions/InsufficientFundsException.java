package com.itt.exceptions;

public class InsufficientFundsException extends Exception{
	
	public InsufficientFundsException() {
		super("You don't have sufficient funds in your account");
	}

}
