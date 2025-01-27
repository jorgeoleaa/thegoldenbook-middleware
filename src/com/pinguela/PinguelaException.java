package com.pinguela;

/**
 * Exception raíz de nuestra compañía, IES A Pinguela
 */

public class PinguelaException extends Exception {

	public PinguelaException() {
	}
	
	public PinguelaException(String message) {
		super(message);
	}
	
	public PinguelaException(Throwable cause) {
		super(cause);
	}
	
	public PinguelaException(String message, Throwable cause) {
		super(message, cause);
	}
}
