package com.recanto.recanto.services;

import com.recanto.recanto.domain.Employee;
import com.recanto.recanto.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository repository;

    public Employee findById(Integer id) {
        Optional<Employee> obj = repository.findById(id);
        return obj.orElse(null);
    }
}
