package com.stacksimplify.restservices.exceptions;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//@RestControllerAdvice
public class GlobalRestControllerExceptionHandler {
	
	@ExceptionHandler(UsernameNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public CustomErrorDetails usernameNotFound(UsernameNotFoundException ex) {
		return new CustomErrorDetails(new Date(), "From @RestController NOT FPUND", ex.getMessage());
	}

}
