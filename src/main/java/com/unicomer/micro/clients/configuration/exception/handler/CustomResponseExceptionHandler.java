package com.unicomer.micro.clients.configuration.exception.handler;

import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.unicomer.micro.clients.configuration.CustomErrorEnum;
import com.unicomer.micro.clients.configuration.exception.CustomResponseException;
import com.unicomer.micro.clients.configuration.exception.models.CustomErrorModel;
import com.unicomer.micro.clients.configuration.exception.models.CustomErrorWrapper;

public abstract class CustomResponseExceptionHandler extends ResponseEntityExceptionHandler {


	@ExceptionHandler({CustomResponseException.class})
	protected ResponseEntity<Object> handleRestResponseApp(
		final CustomResponseException ex,
		final WebRequest request
	) {
		CustomErrorWrapper apiErrorWrapper = this.message(ex);
		return this.handleExceptionInternal(ex, apiErrorWrapper, new HttpHeaders(), ex.getHttpStatus(), request);
	}

	
	@Override
	protected final ResponseEntity<Object> handleMethodArgumentNotValid(
		final MethodArgumentNotValidException ex,
		final HttpHeaders headers, 
		final HttpStatus status, 
		final WebRequest request
	) {
		CustomErrorWrapper apiErrorWrapper = this.processErrors(ex.getBindingResult().getAllErrors());
		return this.handleExceptionInternal(ex, apiErrorWrapper, headers, HttpStatus.BAD_REQUEST, request);
	}
	

	protected ResponseEntity<Object> handleExceptionInternal(
			Exception ex, 
			HttpHeaders headers, 
			HttpStatus status,
			WebRequest request
	) {
		return this.handleExceptionInternal(ex, null, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleExceptionInternal(
			Exception ex, 
			@Nullable Object body, 
			HttpHeaders headers,
			HttpStatus status, 
			WebRequest request
	) {
		this.logger.error(ex.getMessage(), ex);
		
		if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
			request.setAttribute("javax.servlet.error.exception", ex, 0);
		}

		if (Objects.isNull(body)) {
			CustomErrorWrapper apiErrors = this.message(status, ex);
			return new ResponseEntity<>(apiErrors, headers, status);
		} else {
			return new ResponseEntity<>(body, headers, status);
		}
	}

	protected CustomErrorWrapper message(final CustomResponseException ex) {
		return this.message(this.buildProxyErrorModel(ex));
	}

	protected CustomErrorWrapper message(
		final HttpStatus httpStatus, 
		final Exception ex
	) {
		return this.message(this.buildProxyErrorModel(httpStatus, ex));
	}

	protected CustomErrorWrapper message(final CustomErrorModel error) {
		CustomErrorWrapper errors = new CustomErrorWrapper();
		errors.addApiError(error);
		return errors;
	}

	protected CustomErrorWrapper processErrors(final List<ObjectError> errors) {
		CustomErrorWrapper dto = new CustomErrorWrapper();
		errors.forEach(objError -> {
			if (isFieldError(objError)) {
				FieldError fieldError = (FieldError) objError;
				String localizedErrorMessage = fieldError.getDefaultMessage();
				dto.addFieldError(
					fieldError.getClass().getSimpleName(), 
					"Invalid Attribute", 
					fieldError.getField(),
					localizedErrorMessage,
					""
				);
			} else {
				String localizedErrorMessage = objError.getDefaultMessage();
				dto.addFieldError(
					objError.getClass().getSimpleName(), 
					"Invalid Attribute", 
					"base",
					localizedErrorMessage,""
				);
			}
		});
		return dto;
	}

	private CustomErrorModel buildProxyErrorModel(final CustomResponseException ex) {
        String title="";
		
		if(ex.getTitle().equals("")) {
			title=ex.getHttpStatus().getReasonPhrase();
		}else {
			title=ex.getTitle();
		}
		
		
		return CustomErrorModel.builder()
		.type((ex.getType()!=null)?ex.getType():ex.getClass().getSimpleName())
		.title(title)
		.source("base")
		.description(ex.getMessage())
		.severity(ex.getSeverity())
		.status(ex.getHttpStatus().value())
		.build();
	}

	private CustomErrorModel buildProxyErrorModel(
		final HttpStatus httpStatus, 
		final Exception ex
	) {
		String typeException = ex.getClass().getSimpleName();
		CustomErrorEnum errorEnum = CustomErrorEnum.getProxyErrorEnum(typeException);
		String description = StringUtils.defaultIfBlank(ex.getMessage(), ex.getClass().getSimpleName());
		String source = "base";
		if (this.isMissingRequestParameterException(ex)) {
			MissingServletRequestParameterException missingParamEx = (MissingServletRequestParameterException) ex;
			source = missingParamEx.getParameterName();
		} else if (this.isMissingPathVariableException(ex)) {
			MissingPathVariableException missingPathEx = (MissingPathVariableException) ex;
			source = missingPathEx.getVariableName();
		}

		return CustomErrorModel.builder()
		.status(httpStatus.value())
		.type(typeException)
		.title(httpStatus.getReasonPhrase())
		.description(description)
		.source(source)
		.severity(errorEnum.getSeverity())
		.build();
	}

	private boolean isMissingPathVariableException(final Exception ex) {
		return ex instanceof MissingPathVariableException;
	}

	private boolean isMissingRequestParameterException(final Exception ex) {
		return ex instanceof MissingServletRequestParameterException;
	}

	private boolean isFieldError(ObjectError objError) {
		return objError instanceof FieldError;
	}

}
