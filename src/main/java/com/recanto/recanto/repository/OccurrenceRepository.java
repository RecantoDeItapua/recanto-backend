package com.recanto.recanto.repository;

import com.recanto.recanto.domain.Occurrences;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OccurrenceRepository extends JpaRepository<Occurrences, Integer> {
}
