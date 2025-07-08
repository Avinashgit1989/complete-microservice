package com.avijava.employee.exception;

import com.avijava.employee.exception.common.ErrorCode;

public abstract class ErrorCodeProvidingRuntimeException extends RuntimeException{
    private static final long serialVersionUID = -6802941640524107214L;
    private final ErrorCode errorCode;

    public ErrorCodeProvidingRuntimeException(ErrorCode errorCode) {
        this(errorCode, (Throwable) null);
    }

    public ErrorCodeProvidingRuntimeException(ErrorCode errorCode, String message) {
        this(errorCode, message, null);
    }

    public ErrorCodeProvidingRuntimeException(ErrorCode errorCode, Throwable innerException) {
        super(errorCode.name() + " - " + errorCode.getDescription() + " - " + errorCode.getDetails(), innerException);
        this.errorCode = errorCode;
    }

    public ErrorCodeProvidingRuntimeException(ErrorCode errorCode, String message, Throwable innerException) {
        super(errorCode.name() + " - " + errorCode.getDescription() + " - " + errorCode.getDetails() + " - " + message, innerException);
        this.errorCode = errorCode;
    }


    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
