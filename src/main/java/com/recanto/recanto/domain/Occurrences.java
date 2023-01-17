package com.recanto.recanto.domain;

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
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Occurrences {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String description;
    private LocalDate openDate = LocalDate.now();

    @ManyToOne
    @JoinColumn(name = "resident_id")
    private Resident resident;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    public Occurrences(Integer id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }
}
