package com.recanto.recanto.services;

import com.recanto.recanto.repository.PersonRepository;
import com.recanto.recanto.repository.ResidentRepository;
import com.recanto.recanto.services.exceptions.ObjectNotFoundException;
import com.recanto.recanto.domain.Person;
import com.recanto.recanto.domain.Resident;
import com.recanto.recanto.domain.dtos.ResidentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResidentService {
    @Autowired
    private ResidentRepository repository;
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private BCryptPasswordEncoder encoder;

    public Resident findById(Integer id) {
        Optional<Resident> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Obeject not found: " + id));
    }

    public List<Resident> findAll() {
        return repository.findAll();
    }

    public Resident create(ResidentDTO obj) {
        obj.setId(null);
        obj.setPassword(encoder.encode(obj.getPassword()));
        validateByCpfAndEmail(obj);
        Resident newResident = new Resident(obj);
        return repository.save(newResident);
    }

    public Resident update(Integer id, ResidentDTO objDto) {
        objDto.setId(id);
        objDto.setPassword(encoder.encode(objDto.getPassword()));
        Resident oldObj = findById(id);
        validateByCpfAndEmail(objDto);
        oldObj = new Resident(objDto);
        return repository.save(oldObj);

    }


    public void delete(Integer id) {
        Resident obj = findById(id);
        if (
                obj.getProviders().size() > 0 ||
                obj.getAnnoucements().size() > 0 ||
                obj.getOccurrences().size() > 0 ||
                obj.getReservations().size() > 0
        ) {
            throw new DataIntegrityViolationException("Resident cannot be deleted because there are schedule for him");
        }
        repository.deleteById(id);
    }

    private void validateByCpfAndEmail(ResidentDTO objDto) {
        Optional<Person> obj = personRepository.findByCpf(objDto.getCpf());
        if (obj.isPresent() && obj.get().getId() != objDto.getId()) {
            throw new DataIntegrityViolationException("Cpf Already Registered");
        }
        obj = personRepository.findByEmail(objDto.getEmail());
        if (obj.isPresent() && obj.get().getId() != objDto.getId()) {
            throw new DataIntegrityViolationException("Email Already Registered");
        }
    }


}
