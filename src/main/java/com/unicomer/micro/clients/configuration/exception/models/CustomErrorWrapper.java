package com.unicomer.micro.clients.configuration.exception.models;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;

public class CustomErrorWrapper {

	private final List<CustomErrorModel> errors = new ArrayList<>();

	public final void addFieldError(
		final String type, 
		final String title, 
		final String source,
		final String description,
		final String severity
	) {
		CustomErrorModel error = CustomErrorModel.builder()
		.status(HttpStatus.BAD_REQUEST.value())
		.type(type)
		.title(title)
		.description(description)
		.source(source)
		.severity(severity)
		.build();
		
		this.errors.add(error);
	}
	
	public final void addApiError(final CustomErrorModel error) {
		this.errors.add(error);
	}
	
	public List<CustomErrorModel> getErrors() {
		return this.errors;
	}
	
	
}
