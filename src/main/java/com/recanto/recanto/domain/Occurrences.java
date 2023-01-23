package com.recanto.recanto.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.recanto.recanto.enums.Situation;
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
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate openDate = LocalDate.now();
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate finishDate;
    private Situation situation;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;


    public Occurrences(Integer id, String title, String description, Integer situation) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.situation = Situation.toEnum(situation);

    }
}
