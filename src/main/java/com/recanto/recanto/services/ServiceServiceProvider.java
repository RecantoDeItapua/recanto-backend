package com.recanto.recanto.services;

import com.recanto.recanto.domain.ServiceProvider;
import com.recanto.recanto.repository.ServiceProviderRepository;
import com.recanto.recanto.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ServiceServiceProvider {
    @Autowired
    private ServiceProviderRepository repository;

    public ServiceProvider findById(Integer id) {
        Optional<ServiceProvider> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Object Not Found: " + id) );
    }
}
