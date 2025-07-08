package com.avijava.employee.exception.type;

import com.avijava.employee.exception.ErrorCodeProvidingRuntimeException;
import com.avijava.employee.exception.common.ErrorCode;

public class BadRequestException  extends ErrorCodeProvidingRuntimeException {

    public BadRequestException(ErrorCode errorCode) {
        super(errorCode);
    }

    public BadRequestException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }

    public BadRequestException(ErrorCode errorCode, Throwable innerException) {
        super(errorCode, innerException);
    }

    public BadRequestException(ErrorCode errorCode, String message, Throwable innerException) {
        super(errorCode, message, innerException);
    }
}
