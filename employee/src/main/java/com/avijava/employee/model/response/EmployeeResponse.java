package com.avijava.employee.model.response;

import com.avijava.employee.remote.response.DepartmentResponse;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Builder
@Data
@ToString
public class EmployeeResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String primaryEmail;
    private String secondaryEmail;
    private String primaryPhone;
    private String secondaryPhone;
    private DepartmentResponse departmentResponse;


}
