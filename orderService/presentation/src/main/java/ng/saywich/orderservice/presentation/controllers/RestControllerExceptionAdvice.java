package ng.saywich.orderservice.presentation.controllers;

import ng.saywich.shared.domain.exceptions.DomainValidationException;
import ng.saywich.shared.domain.exceptions.IncompatibleComponentException;
import ng.saywich.shared.domain.exceptions.IncompatibleOrderStateException;
import ng.saywich.shared.domain.exceptions.InvalidValue;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class RestControllerExceptionAdvice
    extends ResponseEntityExceptionHandler
{
    @ExceptionHandler({
        DomainValidationException.class,
        IncompatibleComponentException.class,
        IncompatibleOrderStateException.class,
        InvalidValue.class,
    })
    ResponseEntity<Object> handleDomainValidation(RuntimeException ex, WebRequest request) {
        String bodyOfResponse = ex.getMessage();
        return super.handleExceptionInternal(ex, bodyOfResponse,
            new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
}
