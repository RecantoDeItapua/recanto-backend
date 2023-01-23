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
import javax.validation.constraints.NotNull;
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
    @NotNull(message= "O campo TITLE é requerido")
    private String title;
    @NotNull(message= "O campo DESCRIPTION é requerido")
    private String description;

    public Annoucements(Integer id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;



    @JsonFormat(pattern = "dd/MM/YYYY")
    private LocalDate openDate = LocalDate.now();
}
