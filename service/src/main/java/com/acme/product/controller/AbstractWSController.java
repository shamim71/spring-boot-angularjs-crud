package com.acme.product.controller;

import java.net.URI;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import com.acme.product.dto.ErrorInfo;
import com.acme.product.exception.ResourceNotFoundException;


/**
 * @author shamimz
 *
 */
public abstract class AbstractWSController {
    
    /**
     * Send a custom response when an internal server error occurs
     * 
     * @param exception NullPointerException
     * 
     * @return
     */
    @ExceptionHandler(NullPointerException.class) 
    public ResponseEntity < ? > nullPointerException(final NullPointerException exception) {
        final String message = Optional.of(exception.getMessage()).orElse(exception.getClass().getSimpleName());
        return new ResponseEntity < > (message, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @ExceptionHandler(HttpMessageNotReadableException.class) 
    public ResponseEntity < ? > httpMessageNotReadableException(final HttpServletRequest req,final HttpMessageNotReadableException exception) {
        final ErrorInfo errorInfo = new ErrorInfo(req.getRequestURI(), "Message not parsable");
        return new ResponseEntity < > (errorInfo, HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(ResourceNotFoundException.class) 
    public ResponseEntity < ? > resourceNotFoundException(final HttpServletRequest req,final ResourceNotFoundException exception) {
	final String message = Optional.of(exception.getMessage()).orElse(exception.getClass().getSimpleName());
        final ErrorInfo errorInfo = new ErrorInfo(req.getRequestURI(), message);
        return new ResponseEntity < > (errorInfo, HttpStatus.NOT_FOUND);
    }
    
    protected HttpHeaders appendLocationHeader(Long id) {
    	UriComponentsBuilder builder = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}");
		URI location = builder.buildAndExpand(id).toUri();
		
		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.LOCATION, location.getPath());
		return headers;
    }
}
