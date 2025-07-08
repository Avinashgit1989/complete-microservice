package com.avijava.employee.remote.request;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Builder
@Data
@ToString
public class DepartmentRequest {
    private String deptName;
    private String deptCode;
    private String deptLocation;


}
