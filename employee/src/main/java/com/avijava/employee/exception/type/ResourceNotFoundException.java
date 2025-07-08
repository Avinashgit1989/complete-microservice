package com.avijava.employee.exception.type;

import com.avijava.employee.exception.ErrorCodeProvidingRuntimeException;
import com.avijava.employee.exception.common.ErrorCode;

public class ResourceNotFoundException extends ErrorCodeProvidingRuntimeException {
    private static final long serialVersionUID = 1L;


    public ResourceNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }

    public ResourceNotFoundException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }

    public ResourceNotFoundException(ErrorCode errorCode, Throwable innerException) {
        super(errorCode, innerException);
    }

    public ResourceNotFoundException(ErrorCode errorCode, String message, Throwable innerException) {
        super(errorCode, message, innerException);
    }
}
