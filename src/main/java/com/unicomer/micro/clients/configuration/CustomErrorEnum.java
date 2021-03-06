package com.unicomer.micro.clients.configuration;

import org.springframework.http.HttpStatus;

import com.unicomer.micro.clients.configuration.exception.CustomResponseException;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CustomErrorEnum {


	ERROR_NO_CLIENT(
			"",
			"fatal",
			"Error getting client",
			"ERROR_NO_CLIENT"
		),
	ERROR_UPDATE_CLIENT(
			"",
			"fatal",
			"Error updating client",
			"ERROR_UPDATE_CLIENT"
		),	
	ERROR_BIRTHDAY(
			"",
			"fatal",
			"Error birthday is not correct",
			"ERROR_BIRTHDAY"
		),	
	NONE(
		"",
		"none",
		"fatal",
		""
	);
	
	
	String type;

	String severity;

	String errorMessge;

	String code;
	
	public static CustomErrorEnum getProxyErrorEnum(String code) {
		CustomErrorEnum ret = NONE;
        for (CustomErrorEnum caseEnum : values()) {
            if (caseEnum.getCode().equals(code)) {
                ret = caseEnum;
                break;
            }
        }
        return ret;
	}
	
	public static CustomResponseException ErrorGenericException(
			CustomErrorEnum ProxyErrorEnum, 
			HttpStatus status
		) {
			return new CustomResponseException(
				ProxyErrorEnum.getSeverity(), 
				ProxyErrorEnum.getErrorMessge(),
				status
			);
	}
	
	public static CustomResponseException ErrorClient(
			CustomErrorEnum ProxyErrorEnum, 
			HttpStatus status
		) {
			return new CustomResponseException(
				ProxyErrorEnum.getSeverity(), 
				ProxyErrorEnum.getErrorMessge(),
				ProxyErrorEnum.getType(),
				status
			);
	}
	
}
