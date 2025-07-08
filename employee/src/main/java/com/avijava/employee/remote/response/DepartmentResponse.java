package com.avijava.employee.remote.response;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

    @Builder
    @Data
    @ToString
    public class DepartmentResponse {
        private Long id;
        private String deptName;
        private String deptCode;
        private String deptLocation;


    }
