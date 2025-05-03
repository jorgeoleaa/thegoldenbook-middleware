package com.thegoldenbook.dao;

import com.thegoldenbook.TheGoldenBookException;

/**
 * Root exception of the data layer.
 */

public class DataException extends TheGoldenBookException {
	
	public DataException() {
	}
	
	public DataException(String message) {
		super(message);
	}
	
	public DataException(Throwable cause) {
		super(cause);
	}
	
	public DataException(String message, Throwable cause) {
		super(message, cause);
	}
}
