package com.recanto.recanto.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
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

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Annoucements {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String description;

    public Annoucements(Integer id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    @ManyToOne
    @JoinColumn(name = "resident_id")
    private Resident resident;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @JsonFormat(pattern = "dd/MM/YYYY")
    private LocalDate openDate = LocalDate.now();
}
