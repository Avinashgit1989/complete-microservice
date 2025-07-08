package com.avijava.employee.exception.common;

public enum ErrorCode {

    EMPLOYEE_NOT_FOUND("Employee not found exception"),
    INVALID_REQUEST("Invalid request" ),
    UNEXPECTED_EXCEPTION("Unexpected exception"),
    BAD_REQUEST("Bad Request Exception");

    private final String description;
    private final String details;

    ErrorCode(String description, String details) {
        this.description = description;
        this.details = details;
    }

    ErrorCode(String description) {
        this.description = description;
        this.details = "";
    }

    public String getDescription() {
        return description;
    }

    public String getDetails() {
        return details;
    }
}
