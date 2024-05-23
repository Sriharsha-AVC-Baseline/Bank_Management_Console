package com.itt.exceptions;

public class InvalidAmountException extends Exception {
	
	public InvalidAmountException() {
		super("Invalid deposit amount");
	}

}
