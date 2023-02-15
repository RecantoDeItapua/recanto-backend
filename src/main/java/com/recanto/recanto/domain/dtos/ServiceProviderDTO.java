package com.recanto.recanto.domain.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.recanto.recanto.domain.ServiceProvider;
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
public class ServiceProviderDTO {
    private Integer id;
    @NotNull(message = "O campo TITLE é requerido")
    private String title;
    @NotNull(message = "O campo NAME é requerido")
    private String name;
    private String document;
    private String car;
    private String description;
    @NotNull(message = "O campo SITUATION é requerido")
    private Integer situation ;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateOpen = LocalDate.now();
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateFinish;
    @NotNull(message = "O campo PERSON é requerido")
    private Integer person;
    private String personName;


    public ServiceProviderDTO(ServiceProvider obj) {
        this.id = obj.getId();
        this.title = obj.getTitle();
        this.name = obj.getName();
        this.document = obj.getDocument();
        this.car = obj.getCar();
        this.description = obj.getDescription();
        this.situation = obj.getSituation().getCode();
        this.dateOpen = obj.getDateOpen();
        this.dateFinish = obj.getDateFinish();
        this.person = obj.getPerson().getId();
        this.personName = obj.getPerson().getName();
    }

}
