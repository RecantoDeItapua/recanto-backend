package com.recanto.recanto.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;
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
    private String title;
    @Column(unique = true)
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime dateTime;

    @ManyToOne
    @JoinColumn(name = "resident_id")
    private Resident resident;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
}
