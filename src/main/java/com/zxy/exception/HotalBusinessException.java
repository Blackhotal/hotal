package com.zxy.exception;



/**
 * 统一异常
 * @author black
 */
public class HotalBusinessException extends RuntimeException{

	private String message;

	public HotalBusinessException(String message) {
		super(message);
		this.message = message;
	}
}
