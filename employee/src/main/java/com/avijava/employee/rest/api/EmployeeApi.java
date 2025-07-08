package com.avijava.employee.rest.api;

import com.avijava.employee.model.request.EmployeeRequest;
import com.avijava.employee.model.response.EmployeeResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Tag(name = "Employee", description = "Employee Service Api")
public interface EmployeeApi {

    @Operation(
            summary = "Fetch all Employee",
            description = "fetches all employee entities and their data from data source")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    ResponseEntity<List<EmployeeResponse>> getAllEmployee();

    @Operation(
            summary = "adds a employee",
            description = "Adds a employee to the list of employee")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "successfully added a employee"),
            @ApiResponse(responseCode = "409", description = "duplicate employee")
    })
    ResponseEntity<String> addEmployee(@RequestBody EmployeeRequest employeeRequest);


    @Operation(
            summary = "Fetch Employee by ID",
            description = "fetches employee entitie By ID and their data from data source")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    ResponseEntity<List<EmployeeResponse>> getEmployeeById(@PathVariable("id") Long id);

    @Operation(
            summary = "Update Employee",
            description = "Update employee entity and their data from data source")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    ResponseEntity<List<EmployeeResponse>> UpdateEmployee(@RequestBody EmployeeRequest employeeRequest, @PathVariable("id") Long id);

    @Operation(
            summary = "Delete Employee",
            description = "Delete employee entity and their data from data source")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    ResponseEntity<String> deleteEmployee(@PathVariable ("id") Long id);


}
