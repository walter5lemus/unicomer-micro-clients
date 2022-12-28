package com.unicomer.micro.clients.configuration;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
@Slf4j
public class ExceptionGlobalResponse {

    ResponseException responseException;

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ResponseException> runtimeException(RuntimeException e) {
        return getResponseExceptionResponseEntity(e);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseException> exception(Exception e) {
        return getResponseExceptionResponseEntity(e);
    }

    private ResponseEntity<ResponseException> getResponseExceptionResponseEntity(Exception e) {
        responseException = new ResponseException();
        responseException.setErrorException(e.getLocalizedMessage());
        responseException.setErrorClass(e.getClass().getName());
        responseException.setTimeStamp(LocalDateTime.now().toString());
        responseException.setErrorStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.toString());
        log.error(responseException.toString());
        return new ResponseEntity<>(responseException, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}

@Data
class ResponseException{

    private String errorClass;
    private String errorException;
    private String timeStamp;
    private String errorStatusCode;

}
