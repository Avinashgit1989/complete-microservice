package com.avijava.department.rest.api;

import com.avijava.department.model.request.DepartmentRequest;
import com.avijava.department.model.response.DepartmentResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Tag(name = "Department", description = "Department Service Api")
public interface DepartmentApi {

    @Operation(
            summary = "Fetch all Department",
            description = "fetches all department entities and their data from data source")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    ResponseEntity<List<DepartmentResponse>> getAllDepartment();

    @Operation(
            summary = "adds a department",
            description = "Adds a department to the list of department")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "successfully added a department"),
            @ApiResponse(responseCode = "409", description = "duplicate department")
    })
    ResponseEntity<String> addDepartment(@RequestBody DepartmentRequest employeeRequest);


    @Operation(
            summary = "Fetch Department by ID",
            description = "fetches department entitie By ID and their data from data source")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    ResponseEntity<List<DepartmentResponse>> getDepartmentById(@PathVariable("id") Long id);

    @Operation(
            summary = "Update Department",
            description = "Update department entity and their data from data source")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    ResponseEntity<List<DepartmentResponse>> UpdateDepartment(@RequestBody DepartmentRequest employeeRequest, @PathVariable("id") Long id);

    @Operation(
            summary = "Delete Department",
            description = "Delete department entity and their data from data source")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    ResponseEntity<String> deleteDepartment(@PathVariable ("id") Long id);


}
