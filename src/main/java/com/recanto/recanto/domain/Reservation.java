package com.recanto.recanto.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.recanto.recanto.enums.LocalReservation;
import com.recanto.recanto.enums.ReservationAuthorite;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime openDate = LocalDateTime.now();

    private LocalDateTime reserveDate;

    private LocalReservation localReservation;
    private ReservationAuthorite reservationAuthorite;


    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

}
