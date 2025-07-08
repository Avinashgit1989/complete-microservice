package com.avijava.department.rest.api;

import com.avijava.department.model.request.DepartmentRequest;
import com.avijava.department.model.response.DepartmentResponse;
import com.avijava.department.service.DepartmentService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/department")
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentResource implements DepartmentApi {
    private static  final Logger LOGGER = LoggerFactory.getLogger(DepartmentResource.class);


    @Autowired
    DepartmentService departmentService;
    // Define endpoints here, e.g.:

    @Override
    @GetMapping(path = "/getAll")
    public ResponseEntity<List<DepartmentResponse>> getAllDepartment() {
        LOGGER.info("Start Fetching all department");
        // Logic to retrieve all employees
        return new ResponseEntity<>(departmentService.getAllDepartments(), HttpStatus.OK);
    }

    @Override
    @PostMapping(path = "/add")
    public ResponseEntity<String> addDepartment(@RequestBody DepartmentRequest departmentRequest) {
        LOGGER.info("Start Adding a new department: {}", departmentRequest);
        // Validate the request
        if (departmentRequest == null || departmentRequest.getDeptName() == null || departmentRequest.getDeptName().isEmpty()) {
            LOGGER.error("Invalid department request: {}", departmentRequest);

        }
        // Logic to add an employee
        return new ResponseEntity<>(departmentService.addDepartment(departmentRequest), HttpStatus.CREATED);
    }

    @Override
    @GetMapping(path = "/getDepartmentById/{id}")
    public ResponseEntity<List<DepartmentResponse>> getDepartmentById(@PathVariable Long id) {
        LOGGER.info("Fetching department with ID: {}", id);
        return new ResponseEntity<>(departmentService.getDepartmentById(id), HttpStatus.OK);
    }

    @Override
    @PutMapping(path = "/update/{id}")
    public ResponseEntity<List<DepartmentResponse>> UpdateDepartment(@RequestBody DepartmentRequest departmentRequest, @PathVariable Long id) {
        LOGGER.info("Updating department with ID: {} with data: {}", id, departmentRequest);
        return new ResponseEntity<>(departmentService.updateDepartment(departmentRequest, id), HttpStatus.OK);
    }

    @Override
    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<String> deleteDepartment(@PathVariable Long id) {
        LOGGER.info("Deleting department with ID: {}", id);
        return new ResponseEntity<>(departmentService.deleteDepartment(id), HttpStatus.OK);
    }
}
