package com.unicomer.micro.clients.configuration.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomResponseException extends Exception{
	
	private static final long serialVersionUID = 1L;
	
	private String severity;
	private String userMessage;
	private String type = null;
	private HttpStatus httpStatus;
	private String title="";
	private String errorMessage;
	private String userTitle;
	private String userCode;
	
	public CustomResponseException(
		String severity, 
		String userMessage,
		HttpStatus httpStatus
	) {
		super(userMessage);
		this.severity = severity;
		this.userMessage = userMessage;
		this.httpStatus = httpStatus;
	}
	
	public CustomResponseException(
		String severity, 
		String errorMessage,
		String userMessage,
		HttpStatus httpStatus
	) {
		super(errorMessage);
		this.severity = severity;
		this.userMessage = userMessage;
		this.httpStatus = httpStatus;
	}
	 
	 
 
         
	 
	

}
