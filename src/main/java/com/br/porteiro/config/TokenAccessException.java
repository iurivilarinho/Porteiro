package com.br.porteiro.config;

public class TokenAccessException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TokenAccessException(String message) {
		super(message);
	}

	public TokenAccessException(String message, Throwable cause) {
		super(message, cause);
	}
}
