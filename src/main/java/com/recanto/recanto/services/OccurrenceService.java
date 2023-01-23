package com.recanto.recanto.services;

import com.recanto.recanto.domain.Occurrences;
import com.recanto.recanto.domain.Person;
import com.recanto.recanto.domain.dtos.OccurrencesDTO;
import com.recanto.recanto.enums.Situation;
import com.recanto.recanto.repository.OccurrenceRepository;
import com.recanto.recanto.repository.PersonRepository;
import com.recanto.recanto.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class OccurrenceService {
    @Autowired
    private OccurrenceRepository repository;
    @Autowired
    PersonRepository personRepository;

    public Occurrences findById(Integer id) {
        Optional<Occurrences> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Object Not Found: " + id));
    }

    public List<Occurrences> findAll() {
        return repository.findAll();
    }

    public Occurrences create(OccurrencesDTO objDto) {
        return repository.save(newOccurrences(objDto));
    }

    public Occurrences update(Integer id, @Valid OccurrencesDTO objDto) {
        objDto.setId(id);
        Occurrences oldObj = findById(id);
        return oldObj = newOccurrences(objDto);
    }

    private Occurrences newOccurrences(OccurrencesDTO objDto) {
        Optional<Person> person = personRepository.findById(objDto.getPerson());
        person.orElseThrow(() -> new ObjectNotFoundException(
                "Não existe uma pessoa cadastrada com esse id:" + objDto.getPerson()));

        Occurrences occurrences = new Occurrences();
        if (objDto.getId() != null) {
            occurrences.setId(objDto.getId());
        }
        if(objDto.getSituation().equals(2)) {
            occurrences.setFinishDate(LocalDate.now());
        }

        occurrences.setTitle(objDto.getTitle());
        occurrences.setDescription(objDto.getDescription());
        occurrences.setSituation(Situation.toEnum(objDto.getSituation()));
        occurrences.setPerson(person.get());

        return occurrences;
    }



}
