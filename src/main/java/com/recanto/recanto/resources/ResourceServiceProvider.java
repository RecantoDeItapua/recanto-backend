package com.recanto.recanto.resources;

import com.recanto.recanto.domain.ServiceProvider;
import com.recanto.recanto.domain.dtos.ServiceProviderDTO;
import com.recanto.recanto.services.ServiceServiceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/providers")
public class ResourceServiceProvider {
    @Autowired
    private ServiceServiceProvider service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<ServiceProviderDTO> findById(@PathVariable Integer id) {
        ServiceProvider obj = service.findById(id);
       return ResponseEntity.ok().body(new ServiceProviderDTO(obj));
    }
}
