package com.thegoldenbook;

/**
 * Root exception for The Golden Book application.
 */

public class TheGoldenBookException extends Exception {

	public TheGoldenBookException() {
	}
	
	public TheGoldenBookException(String message) {
		super(message);
	}
	
	public TheGoldenBookException(Throwable cause) {
		super(cause);
	}
	
	public TheGoldenBookException(String message, Throwable cause) {
		super(message, cause);
	}
}
