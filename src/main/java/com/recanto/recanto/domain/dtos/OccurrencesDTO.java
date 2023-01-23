package com.recanto.recanto.domain.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.recanto.recanto.domain.Occurrences;
import com.recanto.recanto.domain.Person;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OccurrencesDTO {
    private Integer id;
    private String title;
    private String description;
    private Integer situation;
    @JsonFormat(pattern = "dd/MM/yyy")
    private LocalDate openDate = LocalDate.now();
    @JsonFormat(pattern = "dd/MM/yyy")
    private LocalDate finishDate;
    private Integer person;
    private String personName;

    public OccurrencesDTO(Occurrences obj) {
        this.id = obj.getId();
        this.title = obj.getTitle();
        this.description = obj.getDescription();
        this.openDate = obj.getOpenDate();
        this.person = obj.getPerson().getId();
        this.personName = obj.getPerson().getName();
        this.situation = obj.getSituation().getCode();
        this.finishDate = obj.getFinishDate();
    }
}
