package com.recanto.recanto.resources;

import com.recanto.recanto.domain.Employee;
import com.recanto.recanto.domain.dtos.EmployeeDTO;
import com.recanto.recanto.services.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/employees")
public class EmployeeResource {
    @Autowired
    private EmployeeService service;
    @Autowired
    private ModelMapper mapper;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_EMPLOYEE')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<EmployeeDTO> findById(@PathVariable Integer id) {
        return ResponseEntity.ok().body(new EmployeeDTO(service.findById(id)));
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> findAll() {
        return ResponseEntity.ok().body(service.findAll().stream().map(EmployeeDTO::new).collect(Collectors.toList()));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<EmployeeDTO> create(@Valid @RequestBody EmployeeDTO objDto) {

        Employee newObj = service.create(objDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();

        return ResponseEntity.created(null).build();

    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PutMapping(value = "/{id}")
    public ResponseEntity<EmployeeDTO> update(@PathVariable Integer id, @Valid @RequestBody EmployeeDTO objDto) {
       return ResponseEntity.ok().body( new EmployeeDTO(service.update(id, objDto)));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<EmployeeDTO> delete(@PathVariable Integer id) {
        service.delete(id);
       return ResponseEntity.noContent().build();
    }
}
