package com.recanto.recanto.repository;

import com.recanto.recanto.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {
      Optional<Person>findByCpf(String cpf);
      Optional<Person>findByEmail(String email);
}
