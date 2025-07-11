package com.avijava.department.model.response;

import lombok.*;

@Builder
@Data
@ToString
public class DepartmentResponse {
    private Long id;
    private String deptName;
    private String deptCode;
    private String deptLocation;


}
