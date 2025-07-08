package com.avijava.department.service.impl;

import com.avijava.department.entity.DepartmentEntity;
import com.avijava.department.model.request.DepartmentRequest;
import com.avijava.department.model.response.DepartmentResponse;
import com.avijava.department.repository.DepartmentRepository;
import com.avijava.department.service.DepartmentService;
import com.avijava.department.util.DepartmentUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class DepartmentServiceImpl implements DepartmentService {
    private static  final Logger LOGGER = LoggerFactory.getLogger(DepartmentServiceImpl.class);
    @Autowired
    DepartmentRepository departmentRepository;
    @Override
    public String addDepartment(DepartmentRequest departmentRequest) {
        LOGGER.info("Start Adding a new department: {}", departmentRequest);
        // Convert EmployeeRequest to EmployeeEntity and save it
          DepartmentEntity departmentResponse = departmentRepository.save(DepartmentUtil.transformDepartmentRequestToDepartmentEntity(departmentRequest));
        if (departmentResponse != null) {
            // Return a success message or the saved employee's ID
            return "Department added successfully with ID: " + departmentResponse.getId();
        }else {
            return "Failed to add department";
        }

    }

    @Override
    public List<DepartmentResponse> getAllDepartments() {
        // Retrieve all employees from the repository and convert to EmployeeResponse
        List<DepartmentEntity> employeeEntityList  = departmentRepository.findAll();
        if (employeeEntityList.isEmpty()) {
            return new ArrayList<>();
        }
         return  DepartmentUtil.TransformDepartmentEntityToDepartmentResponse(employeeEntityList);
    }

    @Override
    public List<DepartmentResponse> getDepartmentById(Long id) {
        return DepartmentUtil.TransformDepartmentEntityToDepartmentResponse(departmentRepository.findById(id).stream().toList());
    }

    @Override
    public List<DepartmentResponse> updateDepartment(DepartmentRequest departmentRequest, Long id) {
        // Check if the employee exists
        if (departmentRepository.existsById(id)) {
            // If exists, update the employee entity
            DepartmentEntity existingDepartment = departmentRepository.findById(id).orElseThrow(() -> new RuntimeException("Department not found"));
            existingDepartment.setDeptName(departmentRequest.getDeptName());
            existingDepartment.setDeptCode(departmentRequest.getDeptCode());
            existingDepartment.setDeptLocation(departmentRequest.getDeptLocation());

            // Save the updated employee entity
            departmentRepository.save(existingDepartment);
            return DepartmentUtil.TransformDepartmentEntityToDepartmentResponse(List.of(existingDepartment));
        } else {
            // If not exists, return an empty list or throw an exception
            return new ArrayList<>();
        }
    }

    @Override
    public String deleteDepartment(Long id) {
        // Check if the employee exists
        if (departmentRepository.existsById(id)) {
                // If exists, delete the employee entity
            departmentRepository.deleteById(id);
                return "Department deleted successfully";
            } else {
                // If not exists, return an error message
                return "Department not found";
            }
    }
}
