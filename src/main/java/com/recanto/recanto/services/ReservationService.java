package com.recanto.recanto.services;

import com.recanto.recanto.domain.Person;
import com.recanto.recanto.domain.Reservation;
import com.recanto.recanto.domain.dtos.ReservationDTO;
import com.recanto.recanto.enums.LocalReservation;
import com.recanto.recanto.enums.ReservationAuthorite;
import com.recanto.recanto.repository.PersonRepository;
import com.recanto.recanto.repository.ReservationRepository;
import com.recanto.recanto.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {
    @Autowired
    private ReservationRepository repository;
    @Autowired
    private PersonRepository personRepository;

    public Reservation findById(Integer id) {
        Optional<Reservation> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Object Not Found: " + id));
    }

    public List<Reservation> findAll() {
        return repository.findAll();
    }

    public Reservation create(ReservationDTO objDto) {

        return repository.save(newReservation(objDto));
    }

    public Reservation update(Integer id, @Valid ReservationDTO objDto) {
        objDto.setId(id);
        Reservation oldObj = findById(id);
        oldObj = newReservation(objDto);
        return repository.save(oldObj);
    }

    private Reservation newReservation(ReservationDTO objDto) {
        Optional<Person> person = personRepository.findById(objDto.getPerson());
        person.orElseThrow(() -> new ObjectNotFoundException(
                "Não existe uma pessoa cadastrada com esse id:" + objDto.getPerson()));


        Reservation reservation = new Reservation();
        if (objDto.getId() != null) {
            reservation.setId(objDto.getId());
        }

        reservation.setReserveDate(objDto.getReserveDate());
        reservation.setPerson(person.get());
        reservation.setLocalReservation(LocalReservation.toEnum(objDto.getLocalReservation()));
        reservation.setReservationAuthorite(ReservationAuthorite.toEnum(objDto.getReservationAuthorite()));

        return reservation;
    }

    public void delete(Integer id) {
        findById(id);
        repository.deleteById(id);
    }
}
