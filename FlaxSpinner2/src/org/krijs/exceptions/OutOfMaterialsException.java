package org.krijs.exceptions;

@SuppressWarnings("serial")
public class OutOfMaterialsException extends Exception {
	public OutOfMaterialsException(String message) {
	    super(message);
	}

	public OutOfMaterialsException(String message, Throwable throwable) {
	    super(message, throwable);
	}

	public OutOfMaterialsException() {
		// TODO Auto-generated constructor stub
	}
}
