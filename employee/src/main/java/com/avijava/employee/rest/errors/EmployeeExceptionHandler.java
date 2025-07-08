package com.avijava.employee.rest.errors;

import com.avijava.employee.exception.ErrorCodeProvidingRuntimeException;
import com.avijava.employee.exception.common.ErrorCode;
import com.avijava.employee.exception.common.ErrorResponse;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.util.WebUtils;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@ControllerAdvice
public class EmployeeExceptionHandler  extends ResponseEntityExceptionHandler {
    private static  final Logger LOGGER = LoggerFactory.getLogger(EmployeeExceptionHandler.class);

    @ExceptionHandler(value = { ErrorCodeProvidingRuntimeException.class })
    @ResponseBody
    protected ResponseEntity<ErrorResponse> handleErrorCodeRuntimeException(ErrorCodeProvidingRuntimeException exception) {
        String errorId = UUID.randomUUID().toString();
        LOGGER.error("Error Id : {} Error response created : {}", errorId, exception);
        ErrorResponse errorResponse = new ErrorResponse(exception.getErrorCode(), ExceptionUtils.getRootCauseMessage(exception) + " (Error " + errorId + ")");

        ResponseStatus annotation = AnnotationUtils.findAnnotation(exception.getClass(), ResponseStatus.class);
        if (annotation != null) {
            return new ResponseEntity<>(errorResponse, annotation.value());
        }
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(value = { ConstraintViolationException.class })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    protected ErrorResponse handleConstraintViolation(ConstraintViolationException exception) {
        List<String> messages = exception.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.toList());
        return new ErrorResponse(ErrorCode.INVALID_REQUEST, String.join(",", messages));
    }


    @ExceptionHandler(value = { Exception.class })
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    protected ErrorResponse handleUnexpectedException(Exception exception) {
        String errorId = UUID.randomUUID().toString();
        LOGGER.error("Error Id : {} Error response created : {}", errorId, exception);
        return new ErrorResponse(ErrorCode.UNEXPECTED_EXCEPTION, ExceptionUtils.getRootCauseMessage(exception) + " (Error " + errorId + ")");
    }


    protected ResponseEntity<Object> handleExceptionInternal(Exception exception, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String errorId = UUID.randomUUID().toString();
        LOGGER.error("Error Id : {} Error response created : {}", errorId, exception);
        if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
            request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, exception, WebRequest.SCOPE_REQUEST);
        }
        return new ResponseEntity<>(new ErrorResponse(ErrorCode.UNEXPECTED_EXCEPTION, ExceptionUtils.getRootCauseMessage(exception) + " (Error " + errorId + ")"), headers, status);
    }
}
