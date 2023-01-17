package com.recanto.recanto.repository;

import com.recanto.recanto.domain.Annoucements;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnnoucementsRepository extends JpaRepository<Annoucements, Integer> {
}
