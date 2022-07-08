package com.unicomer.micro.clients.configuration.exception.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class CustomErrorModel {

	private Integer status;
	private String type;
	private String title;
	private String source;
	private String description;
	private String severity;
	
	
	public static CustomErrorModelBuilder builder() {
		return new CustomErrorModelBuilder();
	}
	
}
