package com.recanto.recanto.resources;

import com.recanto.recanto.services.ResidentService;
import com.recanto.recanto.domain.Resident;
import com.recanto.recanto.domain.dtos.ResidentDTO;
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
@RequestMapping(value = "/residents")
public class ResidentResource {
    @Autowired
    private ResidentService service;
    @Autowired
    private ModelMapper mapper;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_EMPLOYEE')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<ResidentDTO> findById(@PathVariable Integer id) {
        return ResponseEntity.ok().body(new ResidentDTO(service.findById(id)));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_EMPLOYEE', 'ROLE_RESIDENT')")
    @GetMapping
    public ResponseEntity<List<ResidentDTO>> findAll() {
        return ResponseEntity.ok().body(service.findAll().stream().map(ResidentDTO::new).collect(Collectors.toList()));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_EMPLOYEE')")
    @PostMapping
    public ResponseEntity<ResidentDTO> create(@Valid @RequestBody ResidentDTO objDto) {

        Resident newObj = service.create(objDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();

        return ResponseEntity.created(uri).build();

    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_EMPLOYEE')")
    @PutMapping(value = "/{id}")
    public ResponseEntity<ResidentDTO> update(@PathVariable Integer id, @Valid @RequestBody ResidentDTO objDto) {
       return ResponseEntity.ok().body( new ResidentDTO(service.update(id, objDto)));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ResidentDTO> delete(@PathVariable Integer id) {
        service.delete(id);
       return ResponseEntity.noContent().build();
    }
}
