package com.recanto.recanto.services;

import com.recanto.recanto.domain.Adress;
import com.recanto.recanto.domain.Annoucements;
import com.recanto.recanto.domain.Person;
import com.recanto.recanto.domain.dtos.AdressDTO;
import com.recanto.recanto.domain.dtos.AnnoucementsDTO;
import com.recanto.recanto.repository.AdressRepository;
import com.recanto.recanto.repository.AnnoucementsRepository;
import com.recanto.recanto.repository.PersonRepository;
import com.recanto.recanto.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdressService {
    @Autowired
    private AdressRepository repository;
    @Autowired
    private PersonRepository personRepository;


    public Adress findById(Integer id) {
        Optional<Adress> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Obeject not found: " + id));
    }

    public List<Adress> findAll() {
        return repository.findAll();
    }

    public Adress create(AdressDTO objDto) {
        Optional<Person> person = personRepository.findById(objDto.getPerson());
        person.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado."));
        objDto.setId(null);

        Adress obj = new Adress();
        obj.setAdress(objDto.getAdress());
        obj.setPerson(person.get());
        return repository.save(obj);
    }

    public Adress update(Integer id, AdressDTO objDto) {
        objDto.setId(id);
        Adress oldObj = findById(id);
        oldObj.setAdress(objDto.getAdress());
        return repository.save(oldObj);

    }


    public void delete(Integer id) {
        Adress obj = findById(id);
        repository.deleteById(id);
    }




}
