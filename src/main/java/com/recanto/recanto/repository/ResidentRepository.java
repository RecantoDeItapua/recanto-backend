package com.recanto.recanto.repository;

import com.recanto.recanto.domain.Resident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ResidentRepository extends JpaRepository<Resident, Integer> {
}
