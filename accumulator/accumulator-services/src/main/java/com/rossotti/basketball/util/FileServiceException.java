package com.rossotti.basketball.util;

/**
 * Exception thrown when unable to handle file.
 */
public class FileServiceException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public FileServiceException(String exception) {
		super("File Exception " + exception);
	}
}
