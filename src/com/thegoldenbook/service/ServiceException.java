package com.thegoldenbook.service;

/**
 * Exception raíz de la capa de servicios.
 */


public class ServiceException extends Exception {
	
	public ServiceException() {
	}
	
	public ServiceException(String message) {
		super(message);
	}
	
	public ServiceException(Throwable cause) {
		super(cause);
	}
	
	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}
}
