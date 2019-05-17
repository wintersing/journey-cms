package com.lt.journey_cms.exception;

public class MessageException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String msg;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public MessageException(String msg) {
		this.msg = msg;
	}
	
}
