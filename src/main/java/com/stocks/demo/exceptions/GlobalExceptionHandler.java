package com.stocks.demo.exceptions;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.stocks.demo.model.ApiError;
import com.stocks.demo.utils.ResponseEntityBuilder;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {

		List<String> details = new ArrayList<String>();
		details.add(ex.getMessage());

		ApiError err = new ApiError(LocalDateTime.now(), HttpStatus.BAD_REQUEST, "Malformed request", details);

		return ResponseEntityBuilder.build(err);
	}

	@ExceptionHandler({ Exception.class })
	public ResponseEntity<Object> handleAll(Exception ex, WebRequest request) {

		List<String> details = new ArrayList<String>();
		details.add(ex.getLocalizedMessage());

		ApiError err = new ApiError(LocalDateTime.now(), HttpStatus.BAD_REQUEST, "Error occurred", details);

		return ResponseEntityBuilder.build(err);

	}
}
