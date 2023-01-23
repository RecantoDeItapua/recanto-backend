package com.recanto.recanto.domain.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.recanto.recanto.domain.Annoucements;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
public class AnnoucementsDTO {
    private Integer id;
    @NotNull(message = "O campo TITLE é requerido")
    private String title;
    @NotNull(message = "O campo DESCRIPTION é requerido")
    private String description;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateOpen = LocalDate.now();
    @NotNull(message = "O campo PERSON é requerido")
    private Integer person;
    private String personName;


    public AnnoucementsDTO(Annoucements obj) {
        this.id = obj.getId();
        this.title = obj.getTitle();
        this.description = obj.getDescription();
        this.dateOpen = obj.getOpenDate();
        this.person = obj.getPerson().getId();
        this.personName = obj.getPerson().getName();
    }

}
