package com.recanto.recanto.repository;

import com.recanto.recanto.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
}
