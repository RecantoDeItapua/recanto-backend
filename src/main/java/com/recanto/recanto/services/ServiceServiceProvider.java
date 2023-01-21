package com.recanto.recanto.services;

import com.recanto.recanto.domain.Person;
import com.recanto.recanto.domain.ServiceProvider;
import com.recanto.recanto.domain.dtos.ServiceProviderDTO;
import com.recanto.recanto.enums.Situation;
import com.recanto.recanto.repository.PersonRepository;
import com.recanto.recanto.repository.ServiceProviderRepository;
import com.recanto.recanto.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceServiceProvider {
    @Autowired
    private ServiceProviderRepository repository;
    @Autowired
    PersonRepository personRepository;

    public ServiceProvider findById(Integer id) {
        Optional<ServiceProvider> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Object Not Found: " + id));
    }

    public List<ServiceProvider> findAll() {
        return repository.findAll();
    }

    public ServiceProvider create(ServiceProviderDTO objDto) {
        return repository.save(newServiceProvider(objDto));
    }

    private ServiceProvider newServiceProvider(ServiceProviderDTO objDto) {
        Optional<Person> person = personRepository.findById(objDto.getPerson());
        person.orElseThrow(() -> new ObjectNotFoundException(
                "Não existe uma pessoa cadastrada com esse id:" + objDto.getPerson()));

        ServiceProvider serviceProvider = new ServiceProvider();
        if (objDto.getId() != null) {
            serviceProvider.setId(objDto.getId());
        }
        serviceProvider.setTitle(objDto.getTitle());
        serviceProvider.setName(objDto.getName());
        serviceProvider.setDocument(objDto.getDocument());
        serviceProvider.setCar(objDto.getCar());
        serviceProvider.setDescription(objDto.getDescription());
        serviceProvider.setSituation(Situation.toEnum(objDto.getSituation()));
        serviceProvider.setPerson(person.get());

        return serviceProvider;
    }


}
