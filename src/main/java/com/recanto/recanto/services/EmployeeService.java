package com.recanto.recanto.services;

import com.recanto.recanto.domain.Employee;
import com.recanto.recanto.domain.Person;
import com.recanto.recanto.domain.dtos.EmployeeDTO;
import com.recanto.recanto.repository.EmployeeRepository;
import com.recanto.recanto.repository.PersonRepository;
import com.recanto.recanto.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository repository;
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private BCryptPasswordEncoder encoder;

    public Employee findById(Integer id) {
        Optional<Employee> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado!: " + id));
    }

    public List<Employee> findAll() {
        return repository.findAll();
    }

    public Employee create(EmployeeDTO objDto) {
        objDto.setId(null);

        validateByCpfAndEmail(objDto);
        Employee newEmployee = new Employee(objDto);
        newEmployee.setPassword(encoder.encode(objDto.getPassword()));
        return repository.save(newEmployee);
    }

    public Employee update(Integer id,@Valid EmployeeDTO objDto) {
        objDto.setId(id);

        Employee oldObj = findById(id);

        if(!objDto.getPassword().equals(oldObj.getPassword()))
            objDto.setPassword(encoder.encode(objDto.getPassword()));

        validateByCpfAndEmail(objDto);

        oldObj = new Employee(objDto);
        return repository.save(oldObj);

    }


    public void delete(Integer id) {
        Employee obj = findById(id);
        if (
                obj.getProviders().size() > 0 ||
                obj.getAnnoucements().size() > 0 ||
                obj.getOccurrences().size() > 0 ||
                obj.getReservations().size() > 0
        ) {
            throw new DataIntegrityViolationException("Funcionário não pode ser deletado pois existe relação com outros serviços");
        }
        repository.deleteById(id);
    }

    private void validateByCpfAndEmail(EmployeeDTO objDto) {
        Optional<Person> obj = personRepository.findByCpf(objDto.getCpf());
        if (obj.isPresent() && obj.get().getId() != objDto.getId()) {
            throw new DataIntegrityViolationException("CPF já cadastrado!");
        }
        obj = personRepository.findByEmail(objDto.getEmail());
        if (obj.isPresent() && obj.get().getId() != objDto.getId()) {
            throw new DataIntegrityViolationException("E-mail já cadastrado!");
        }
    }


    public Employee findByEmail(String email) {
        Optional<Employee> obj = repository.findByEmail(email);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado!: " + email));
    }
}
