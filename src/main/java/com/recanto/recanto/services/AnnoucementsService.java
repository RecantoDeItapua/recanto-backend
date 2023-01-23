package com.recanto.recanto.services;

import com.recanto.recanto.domain.Annoucements;
import com.recanto.recanto.domain.Person;
import com.recanto.recanto.domain.Resident;
import com.recanto.recanto.domain.dtos.AnnoucementsDTO;
import com.recanto.recanto.domain.dtos.ResidentDTO;
import com.recanto.recanto.repository.AnnoucementsRepository;
import com.recanto.recanto.repository.PersonRepository;
import com.recanto.recanto.repository.ResidentRepository;
import com.recanto.recanto.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnnoucementsService {
    @Autowired
    private AnnoucementsRepository repository;
    @Autowired
    private PersonRepository personRepository;


    public Annoucements findById(Integer id) {
        Optional<Annoucements> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Obeject not found: " + id));
    }

    public List<Annoucements> findAll() {
        return repository.findAll();
    }

    public Annoucements create(AnnoucementsDTO objDto) {
        Optional<Person> person = personRepository.findById(objDto.getPerson());
        person.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado."));
        objDto.setId(null);

        Annoucements obj = new Annoucements();
        obj.setTitle(objDto.getTitle());
        obj.setDescription(objDto.getDescription());
        obj.setPerson(person.get());
        return repository.save(obj);
    }

    public Annoucements update(Integer id, AnnoucementsDTO objDto) {
        objDto.setId(id);
        Annoucements oldObj = findById(id);
        oldObj.setTitle(objDto.getTitle());
        oldObj.setDescription(objDto.getDescription());
        return repository.save(oldObj);

    }


    public void delete(Integer id) {
        Annoucements obj = findById(id);
        repository.deleteById(id);
    }



}
