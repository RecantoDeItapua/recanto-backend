package com.recanto.recanto.resources;

import com.recanto.recanto.domain.dtos.EmployeeDTO;
import com.recanto.recanto.services.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/employees")
public class EmployeeResource {
    @Autowired
    private EmployeeService service;
    @Autowired
    private ModelMapper mapper;

    @GetMapping(value = "/{id}")
    public ResponseEntity<EmployeeDTO> findById(@PathVariable Integer id) {
           return ResponseEntity.ok().body(new EmployeeDTO(service.findById(id)));
        }

    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> findAll() {
        return ResponseEntity.ok().body(service.findAll().stream().map(EmployeeDTO::new).collect(Collectors.toList()));
    }
}
