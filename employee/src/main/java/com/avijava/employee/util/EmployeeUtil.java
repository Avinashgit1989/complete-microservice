package com.avijava.employee.util;

import com.avijava.employee.entity.EmployeeEntity;
import com.avijava.employee.model.request.EmployeeRequest;
import com.avijava.employee.model.response.EmployeeResponse;
import com.avijava.employee.remote.response.DepartmentResponse;

import java.util.ArrayList;
import java.util.List;

public class EmployeeUtil {

    public static EmployeeEntity transformEmployeeRequestToEmployeeEntity(EmployeeRequest employeeRequest) {
        if (employeeRequest == null) {
            return null;
        }
        return EmployeeEntity.builder()
                .firstName(employeeRequest.getFirstName())
                .lastName(employeeRequest.getLastName())
                .primaryEmail(employeeRequest.getPrimaryEmail())
                .secondaryEmail(employeeRequest.getSecondaryEmail())
                .primaryPhone(employeeRequest.getPrimaryPhone())
                .secondaryPhone(employeeRequest.getSecondaryPhone())
                .build();
    }

    public static List<EmployeeResponse> TransformEmployeeEntityToEmployeeResponse(List<EmployeeEntity> employeeEntityList,
                                                                                     List<DepartmentResponse> departmentResponse ) {
        if (employeeEntityList == null) {
            return new ArrayList<>();
        }

        return  employeeEntityList.stream().map(employeeEntity -> EmployeeResponse.builder()
                .id(employeeEntity.getId())
                .firstName(employeeEntity.getFirstName())
                .lastName(employeeEntity.getLastName())
                .primaryEmail(employeeEntity.getPrimaryEmail())
                .secondaryEmail(employeeEntity.getSecondaryEmail())
                .primaryPhone(employeeEntity.getPrimaryPhone())
                .secondaryPhone(employeeEntity.getSecondaryPhone())
                .departmentResponse(departmentResponse.stream()
                        .filter(department -> department.getId().equals(employeeEntity.getId()))
                        .findFirst()
                        .get())
                .build()).toList();

    }
}
