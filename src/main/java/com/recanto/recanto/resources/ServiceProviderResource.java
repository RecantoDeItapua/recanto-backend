package com.recanto.recanto.resources;

import com.recanto.recanto.domain.ServiceProvider;
import com.recanto.recanto.domain.dtos.ServiceProviderDTO;
import com.recanto.recanto.services.ServiceServiceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @GetMapping(value = "/{id}")
    public ResponseEntity<ServiceProviderDTO> findById(@PathVariable Integer id) {
       return ResponseEntity.ok().body(new ServiceProviderDTO(service.findById(id)));
    }

    @GetMapping
    public ResponseEntity<List<ServiceProviderDTO>> findAll() {
      return   ResponseEntity.ok().body(service.findAll()
              .stream().map(ServiceProviderDTO::new).collect(Collectors.toList()));
    }
    @PostMapping
    public ResponseEntity<ServiceProviderDTO> create(@Valid @RequestBody ServiceProviderDTO objDto) {
            ServiceProvider obj = service.create(objDto);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ServiceProviderDTO> update(@PathVariable Integer id, @RequestBody ServiceProviderDTO objDto) {
        return ResponseEntity.ok().body(new ServiceProviderDTO(service.update(id, objDto)));
    }
}
