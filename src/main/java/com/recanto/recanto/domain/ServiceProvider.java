package com.recanto.recanto.domain;

import com.recanto.recanto.enums.Situation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
@Getter
@Setter
@Entity
public class ServiceProvider  {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String titulo;
    private String name;
    private String document;
    private String car;
    private String description;

    @Enumerated(EnumType.STRING)
    private Situation situation ;

    public ServiceProvider(Integer id, String titulo, String name, String document, String description) {
        this.id = id;
        this.titulo = titulo;
        this.name = name;
        this.document = document;
        this.description = description;
    }

    private LocalDate dateOpen = LocalDate.now();
    private LocalDate dateFinish;

    @ManyToOne
    @JoinColumn(name = "resident_id")
    private Resident resident;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;


}
