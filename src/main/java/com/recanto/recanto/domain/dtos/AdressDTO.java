package com.recanto.recanto.domain.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.recanto.recanto.domain.Adress;
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
public class AdressDTO {
    private Integer id;
    private String adress;
    private Integer person;
    private String personName;


    public AdressDTO(Adress obj) {
        this.id = obj.getId();
        this.adress = obj.getAdress();
        this.person = obj.getPerson().getId();
        this.personName = obj.getPerson().getName();
    }

}
