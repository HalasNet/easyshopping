package com.ibm.common.exception;

public class ServiceException extends RuntimeException {

	private static final long serialVersionUID = -528093800979476041L;

	public ServiceException() {
		super();
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
