package com.recanto.recanto.resources;

import com.recanto.recanto.domain.Employee;
import com.recanto.recanto.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/employees")
public class EmployeeResource {
    @Autowired
    private EmployeeService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Employee> findById(@PathVariable Integer id) {
           return ResponseEntity.ok().body(service.findById(id));
        }

}
