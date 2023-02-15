package com.recanto.recanto.services;

import com.recanto.recanto.domain.Payment;
import com.recanto.recanto.domain.Person;
import com.recanto.recanto.domain.dtos.PaymentDTO;
import com.recanto.recanto.enums.ModePayment;
import com.recanto.recanto.enums.Situation;
import com.recanto.recanto.repository.PaymentRepository;
import com.recanto.recanto.repository.PersonRepository;
import com.recanto.recanto.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {
    @Autowired
    private PaymentRepository repository;
    @Autowired
    private PersonRepository personRepository;

    public Payment findById(Integer id) {
        Optional<Payment> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Object Not Found: " + id));
    }

    public List<Payment> findAll() {
        return repository.findAll();
    }

    public Payment create(PaymentDTO objDto) {

        return repository.save(newPayment(objDto));
    }

    public Payment update(Integer id, @Valid PaymentDTO objDto) {
        objDto.setId(id);
        Payment oldObj = findById(id);
        return repository.save(newPayment(objDto));
    }

    private Payment newPayment(PaymentDTO objDto) {
        Optional<Person> person = personRepository.findById(objDto.getPerson());
        person.orElseThrow(() -> new ObjectNotFoundException(
                "Não existe uma pessoa cadastrada com esse id:" + objDto.getPerson()));


        Payment payment = new Payment();
        if (objDto.getId() != null) {
            payment.setId(objDto.getId());
        }

        if (objDto.getSituation().equals(1)){
            payment.setFinishPayment(LocalDate.now());
        }
        payment.setTitle(objDto.getTitle());
        payment.setCash(objDto.getCash());
        payment.setDatePayment(LocalDate.now());
        payment.setSituation(Situation.toEnum(objDto.getSituation()));
        payment.setModePayment(ModePayment.toEnum(objDto.getModePayment()));
        payment.setPerson(person.get());

        return payment;
    }

    public void delete(Integer id) {
        findById(id);
        repository.deleteById(id);
    }
}
