package com.avijava.employee.service.impl;

import com.avijava.employee.entity.EmployeeEntity;
import com.avijava.employee.exception.common.ErrorCode;
import com.avijava.employee.exception.type.BadRequestException;
import com.avijava.employee.exception.type.RemoteServiceException;
import com.avijava.employee.model.request.EmployeeRequest;
import com.avijava.employee.model.response.EmployeeResponse;
import com.avijava.employee.remote.DepartmentClient;
import com.avijava.employee.remote.response.DepartmentResponse;
import com.avijava.employee.repository.EmployeeRepository;
import com.avijava.employee.service.EmployeeService;
import com.avijava.employee.util.EmployeeUtil;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.List;
@Service
public class EmployeeServiceImpl implements EmployeeService {
    private static  final Logger LOGGER = LoggerFactory.getLogger(EmployeeServiceImpl.class);
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    DepartmentClient departmentClient;
    @Override
    public String addEmployee(EmployeeRequest employeeRequest) {
        LOGGER.info("Start Adding a new employee: {}", employeeRequest);
        // Convert EmployeeRequest to EmployeeEntity and save it
          EmployeeEntity employeeResponse = employeeRepository.save(EmployeeUtil.transformEmployeeRequestToEmployeeEntity(employeeRequest));
        if (employeeResponse != null) {
            try {
                departmentClient.addDepartment(employeeRequest.getDepartmentRequest());
                // Log the saved employee's ID
                LOGGER.info("Department added successfully");
            } catch (Exception e) {
                if (e instanceof RemoteServiceException) {
                    LOGGER.error("Remote service exception while adding department: {}", e.getMessage());
                    throw new RemoteServiceException(((RemoteServiceException) e).getErrorCode(), "Error while adding department", e);
                } else if (e instanceof HttpClientErrorException.BadRequest) {
                    LOGGER.error("Internal server error while connecting with client ",e.getMessage());
                    throw  new BadRequestException(ErrorCode.INVALID_REQUEST,"Some required field missing");
                } else {
                    LOGGER.error("Unexpected error while adding department: {}", e.getMessage());
                }

            }
            // Return a success message or the saved employee's ID
            return "Employee added successfully with ID: " + employeeResponse.getId();
        }else {
            return "Failed to add employee";
        }

    }

    @Override
    public List<EmployeeResponse> getAllEmployees() {
        // Retrieve all employees from the repository and convert to EmployeeResponse
        List<EmployeeEntity> employeeEntityList  = employeeRepository.findAll();
        if (employeeEntityList.isEmpty()) {
            return new ArrayList<>();
        }
        List<DepartmentResponse> departmentResponseList = departmentClient.getAllDepartments();
         return  EmployeeUtil.TransformEmployeeEntityToEmployeeResponse(employeeEntityList,departmentResponseList);
    }

    @Override
    public List<EmployeeResponse> getEmployeeById(Long id) {
        List<DepartmentResponse> departmentResponse = departmentClient.getDepartmentById(id);
        LOGGER.info("Fetching department with ID: {} - Response: {}", id, departmentResponse);
        return EmployeeUtil.TransformEmployeeEntityToEmployeeResponse(employeeRepository.findById(id).stream().toList(), departmentResponse);
    }

    @Override
    public List<EmployeeResponse> updateEmployee(EmployeeRequest employeeRequest, Long id) {
        // Check if the employee exists
        if (employeeRepository.existsById(id)) {
            // If exists, update the employee entity
            EmployeeEntity existingEmployee = employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("Employee not found"));
            existingEmployee.setFirstName(employeeRequest.getFirstName());
            existingEmployee.setLastName(employeeRequest.getLastName());
            existingEmployee.setPrimaryEmail(employeeRequest.getPrimaryEmail());
            existingEmployee.setSecondaryEmail(employeeRequest.getSecondaryEmail());
            existingEmployee.setPrimaryPhone(employeeRequest.getPrimaryPhone());
            existingEmployee.setSecondaryPhone(employeeRequest.getSecondaryPhone());

            // Save the updated employee entity
            employeeRepository.save(existingEmployee);
            return EmployeeUtil.TransformEmployeeEntityToEmployeeResponse(List.of(existingEmployee), new ArrayList<>());
        } else {
            // If not exists, return an empty list or throw an exception
            return new ArrayList<>();
        }
    }

    @Override
    public String deleteEmployee(Long id) {
        // Check if the employee exists
        if (employeeRepository.existsById(id)) {
                // If exists, delete the employee entity
                employeeRepository.deleteById(id);
                return "Employee deleted successfully";
            } else {
                // If not exists, return an error message
                return "Employee not found";
            }
    }
}
