package com.ebs.ngs.hsc.hsp.system.exception;

/**
 * @author peter
 *
 */
public class BizException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public BizException() {
		super();
	}

	/**
	 * @param message
	 */
	public BizException(String userMessage) {
		super(userMessage);
	}

	/**
	 * @param message
	 * @param flag
	 */
	/*
	public BizException(String message, int flag) {
		super(message, flag);
	}/
	
	/**
	 * @param message
	 * @param flag
	 * @param url
	 */
	/*
	public BizException(String message, int flag, String url) {
		super(message, flag, url);
	}
	*/

	/**
	 * @param message
	 * @param rootCause
	 */
	public BizException(String message, Throwable rootCause) {
		super(message, rootCause);
	}

	/**
	 * @param rootCause
	 */
	public BizException(Throwable rootCause) {
		super(rootCause);
	}
}