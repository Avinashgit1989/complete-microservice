package com.avijava.employee.service;

import com.avijava.employee.model.request.EmployeeRequest;
import com.avijava.employee.model.response.EmployeeResponse;

import java.util.List;

public interface EmployeeService {
    String addEmployee(EmployeeRequest employeeRequest);

    List<EmployeeResponse> getAllEmployees();

    List<EmployeeResponse> getEmployeeById(Long id);

    List<EmployeeResponse> updateEmployee(EmployeeRequest employeeRequest, Long id);

    String deleteEmployee(Long id);
}
