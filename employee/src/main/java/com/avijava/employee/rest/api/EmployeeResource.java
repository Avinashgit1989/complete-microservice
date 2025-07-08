package com.avijava.employee.rest.api;

import com.avijava.employee.exception.common.ErrorCode;
import com.avijava.employee.exception.type.BadRequestException;
import com.avijava.employee.model.request.EmployeeRequest;
import com.avijava.employee.model.response.EmployeeResponse;
import com.avijava.employee.service.EmployeeService;
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
@RequestMapping("/employee")
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeResource implements EmployeeApi{
    private static  final Logger LOGGER = LoggerFactory.getLogger(EmployeeResource.class);


    @Autowired
    EmployeeService employeeService;
    // Define endpoints here, e.g.:

    @Override
    @GetMapping(path = "/getAll")
    public ResponseEntity<List<EmployeeResponse>> getAllEmployee() {
        LOGGER.info("Start Fetching all employees");
        // Logic to retrieve all employees
        return new ResponseEntity<>(employeeService.getAllEmployees(), HttpStatus.OK);
    }

    @Override
    @PostMapping(path = "/add")
    public ResponseEntity<String> addEmployee(@RequestBody EmployeeRequest employeeRequest) {
        LOGGER.info("Start Adding a new employee: {}", employeeRequest);
        // Validate the request
        if (employeeRequest == null || employeeRequest.getFirstName() == null || employeeRequest.getLastName().isEmpty()) {
            LOGGER.error("Invalid employee request: {}", employeeRequest);
            throw new BadRequestException(ErrorCode.BAD_REQUEST,"Bad request or First name and last name are missing.");
        }
        // Logic to add an employee
        return new ResponseEntity<>(employeeService.addEmployee(employeeRequest), HttpStatus.CREATED);
    }

    @Override
    @GetMapping(path = "/getEmployeeById/{id}")
    public ResponseEntity<List<EmployeeResponse>> getEmployeeById(@PathVariable Long id) {
        LOGGER.info("Fetching employee with ID: {}", id);
        return new ResponseEntity<>(employeeService.getEmployeeById(id), HttpStatus.OK);
    }

    @Override
    @PutMapping(path = "/update/{id}")
    public ResponseEntity<List<EmployeeResponse>> UpdateEmployee(@RequestBody EmployeeRequest employeeRequest, @PathVariable Long id) {
        LOGGER.info("Updating employee with ID: {} with data: {}", id, employeeRequest);
        return new ResponseEntity<>(employeeService.updateEmployee(employeeRequest, id), HttpStatus.OK);
    }

    @Override
    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
        LOGGER.info("Deleting employee with ID: {}", id);
        return new ResponseEntity<>(employeeService.deleteEmployee(id), HttpStatus.OK);
    }
}
