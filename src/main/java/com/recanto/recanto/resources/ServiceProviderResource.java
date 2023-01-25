package com.recanto.recanto.resources;

import com.recanto.recanto.domain.ServiceProvider;
import com.recanto.recanto.domain.dtos.ServiceProviderDTO;
import com.recanto.recanto.services.ServiceServiceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
@RequestMapping(value = "/providers")
public class ServiceProviderResource {
    @Autowired
    private ServiceServiceProvider service;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_EMPLOYEE', 'ROLE_RESIDENT')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<ServiceProviderDTO> findById(@PathVariable Integer id) {
       return ResponseEntity.ok().body(new ServiceProviderDTO(service.findById(id)));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_EMPLOYEE', 'ROLE_RESIDENT')")
    @GetMapping
    public ResponseEntity<List<ServiceProviderDTO>> findAll() {
      return   ResponseEntity.ok().body(service.findAll()
              .stream().map(ServiceProviderDTO::new).collect(Collectors.toList()));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_EMPLOYEE', 'ROLE_RESIDENT')")
    @PostMapping
    public ResponseEntity<ServiceProviderDTO> create(@Valid @RequestBody ServiceProviderDTO objDto) {
            ServiceProvider obj = service.create(objDto);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_EMPLOYEE', 'ROLE_RESIDENT')")
    @PutMapping(value = "/{id}")
    public ResponseEntity<ServiceProviderDTO> update(@PathVariable Integer id, @RequestBody ServiceProviderDTO objDto) {
        return ResponseEntity.ok().body(new ServiceProviderDTO(service.update(id, objDto)));
    }
}
