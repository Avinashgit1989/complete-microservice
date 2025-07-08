package com.avijava.employee.exception.type;

import com.avijava.employee.exception.ErrorCodeProvidingRuntimeException;
import com.avijava.employee.exception.common.ErrorCode;

public class RemoteServiceException  extends ErrorCodeProvidingRuntimeException {

    public RemoteServiceException(ErrorCode errorCode) {
        super(errorCode);
    }

    public RemoteServiceException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }

    public RemoteServiceException(ErrorCode errorCode, Throwable innerException) {
        super(errorCode, innerException);
    }

    public RemoteServiceException(ErrorCode errorCode, String message, Throwable innerException) {
        super(errorCode, message, innerException);
    }
}
