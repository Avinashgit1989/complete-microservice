package com.avijava.employee.remote;

import com.avijava.employee.remote.request.DepartmentRequest;
import com.avijava.employee.remote.response.DepartmentResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(name = "department")
public interface DepartmentClient {

     @GetMapping("/api/v1/department/getDepartmentById/{id}")
     List<DepartmentResponse> getDepartmentById(@PathVariable("id") Long id);

     @PostMapping("/api/v1/department/add")
     String addDepartment(DepartmentRequest departmentRequest);

     @GetMapping("/api/v1/department/getAll")
        List<DepartmentResponse> getAllDepartments();

}
