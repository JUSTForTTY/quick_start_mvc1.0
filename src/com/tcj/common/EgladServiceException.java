package com.tcj.common;

/**
 * Service异常 这个是抛到前台显示的，不用记录日志
 * @author 潘平
 */
public class EgladServiceException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EgladServiceException() {
		super();
		
	}

	public EgladServiceException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public EgladServiceException(String message) {
		super(message);
		
	}

	public EgladServiceException(Throwable cause) {
		super(cause);
		
	}

	
	
}
