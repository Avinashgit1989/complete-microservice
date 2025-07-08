package com.avijava.department.service;

import com.avijava.department.model.request.DepartmentRequest;
import com.avijava.department.model.response.DepartmentResponse;

import java.util.List;

public interface DepartmentService {
    /**
     * Adds a new department.
     *
     * @param employeeRequest the request containing department details
     * @return a message indicating the result of the operation
     */
    String addDepartment(DepartmentRequest employeeRequest);
    /**
     * Retrieves all departments.
     *
     * @return a list of all departments
     */

    List<DepartmentResponse> getAllDepartments();
    /**
     * Retrieves a department by its ID.
     *
     * @param id the ID of the department
     * @return a list containing the department details
     */

    List<DepartmentResponse> getDepartmentById(Long id);
    /**
     * Updates an existing department.
     *
     * @param employeeRequest the request containing updated department details
     * @param id the ID of the department to update
     * @return a list containing the updated department details
     */

    List<DepartmentResponse> updateDepartment(DepartmentRequest employeeRequest, Long id);
    /**
     * Deletes a department by its ID.
     *
     * @param id the ID of the department to delete
     * @return a message indicating the result of the operation
     */

    String deleteDepartment(Long id);
}
