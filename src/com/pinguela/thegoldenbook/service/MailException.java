package com.pinguela.thegoldenbook.service;

public class MailException extends ServiceException{
	
	public MailException(String message) {
		super(message);
	}
	
	public MailException(Throwable cause) {
		super(cause);
	}
	
	public MailException(String message, Throwable cause) {
		super(message, cause);
	}
}
