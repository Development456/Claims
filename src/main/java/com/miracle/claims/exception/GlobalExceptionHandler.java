package com.miracle.claims.exception;

import java.lang.reflect.Method;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler implements AsyncUncaughtExceptionHandler {

	/**
	 * Handle all exceptions.
	 *
	 * @param ex      the ex
	 * @param request the request
	 * @return the response entity
	 */
	@ExceptionHandler({ Exception.class, RuntimeException.class, Throwable.class })
	public ResponseEntity<ErrorDetails> handleAllExceptions(Exception ex, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(HttpStatus.INTERNAL_SERVER_ERROR.value(),
				"Error occured processing the request");
		HttpStatus responseCode = HttpStatus.INTERNAL_SERVER_ERROR;
		log.error("Response Code: {}, Message: {}, {}", responseCode.value(), ex.getMessage(),
				request.getDescription(false), ex);
		return new ResponseEntity<>(errorDetails, responseCode);
	}

	/**
	 * Handle http request method not supported.
	 *
	 * @param ex      the ex
	 * @param headers the headers
	 * @param status  the status
	 * @param request the request
	 * @return the response entity
	 */
	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails();
		errorDetails.setStatus(status.value());
		errorDetails.setMessage(ex.getMessage());
		return new ResponseEntity<>(errorDetails, status);
	}

	/**
	 * Handle bind exception.
	 *
	 * @param ex      the ex
	 * @param headers the headers
	 * @param status  the status
	 * @param request the request
	 * @return the response entity
	 */
	@Override
	protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status,
			WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails();
		errorDetails.setStatus(status.value());
		errorDetails.setMessage(ex.getMessage());
		return new ResponseEntity<>(errorDetails, status);
	}

	/**
	 * Handle http message not readable.
	 *
	 * @param ex      the ex
	 * @param headers the headers
	 * @param status  the status
	 * @param request the request
	 * @return the response entity
	 */
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails();
		String errorDesc = ex.getRootCause().getMessage();
		errorDetails.setStatus(status.value());
		errorDetails.setMessage(errorDesc);
		return new ResponseEntity<>(errorDetails, status);
	}

	/**
	 * Handle method argument not valid.
	 *
	 * @param ex      the ex
	 * @param headers the headers
	 * @param status  the status
	 * @param request the request
	 * @return the response entity
	 */
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails();
		for (ObjectError error : ex.getBindingResult().getAllErrors()) {
			String message = error.getDefaultMessage();
			errorDetails.setStatus(status.value());
			errorDetails.setMessage(message);
			break;
		}
		return new ResponseEntity<>(errorDetails, status);
	}

	/**
	 * Handle uncaught exception.
	 *
	 * @param ex     the ex
	 * @param method the method
	 * @param params the params
	 */
	@Override
	public void handleUncaughtException(Throwable ex, Method method, Object... params) {
		log.error("Exception occured " + ex.getMessage());

	}
}
