package com.skillenza.exception;

public class MyFileNotFoundException extends Exception {
	public MyFileNotFoundException(String message) {
        super(message);
    }

    public MyFileNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
