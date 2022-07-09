package com.unicomer.micro.clients.configuration;

import org.springframework.web.bind.annotation.ControllerAdvice;

import com.unicomer.micro.clients.configuration.exception.handler.CustomResponseExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends CustomResponseExceptionHandler {

}
