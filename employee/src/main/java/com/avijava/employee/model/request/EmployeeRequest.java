package com.avijava.employee.model.request;

import com.avijava.employee.remote.request.DepartmentRequest;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Builder
@Data
@ToString
public class EmployeeRequest {
    private String firstName;
    private String lastName;
    private String primaryEmail;
    private String secondaryEmail;
    private String primaryPhone;
    private String secondaryPhone;
    private DepartmentRequest departmentRequest;


}
