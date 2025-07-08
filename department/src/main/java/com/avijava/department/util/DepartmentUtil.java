package com.avijava.department.util;

import com.avijava.department.entity.DepartmentEntity;
import com.avijava.department.model.request.DepartmentRequest;
import com.avijava.department.model.response.DepartmentResponse;

import java.util.ArrayList;
import java.util.List;

public class DepartmentUtil {

    public static DepartmentEntity transformDepartmentRequestToDepartmentEntity(DepartmentRequest departmentRequest) {
        if (departmentRequest == null) {
            return null;
        }
        return DepartmentEntity.builder()
                .deptName(departmentRequest.getDeptName())
                .deptCode(departmentRequest.getDeptCode())
                .deptLocation(departmentRequest.getDeptLocation())
                .build();
    }

    public static List<DepartmentResponse> TransformDepartmentEntityToDepartmentResponse(List<DepartmentEntity> departmentEntityList) {
        if (departmentEntityList == null) {
            return new ArrayList<>();
        }
        return  departmentEntityList.stream().map(departmentEntity -> DepartmentResponse.builder()
                .id(departmentEntity.getId())
                .deptName(departmentEntity.getDeptName())
                .deptCode(departmentEntity.getDeptCode())
                .deptLocation(departmentEntity.getDeptLocation())
                .build()).toList();

    }
}
