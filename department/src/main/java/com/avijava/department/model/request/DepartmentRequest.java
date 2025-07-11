package com.avijava.department.model.request;

import lombok.*;

@Builder
@Data
@ToString
public class DepartmentRequest {
    private String deptName;
    private String deptCode;
    private String deptLocation;


}
