package org.krijs.exceptions;

@SuppressWarnings("serial")
public class OutOfRangeException extends Exception {
	public OutOfRangeException(String message) {
        super(message);
    }

    public OutOfRangeException(String message, Throwable throwable) {
        super(message, throwable);
    }

	public OutOfRangeException() {
		// TODO Auto-generated constructor stub
	}
}
