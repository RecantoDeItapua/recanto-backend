package com.recanto.recanto.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.recanto.recanto.domain.dtos.ResidentDTO;
import com.recanto.recanto.enums.Profile;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@EqualsAndHashCode
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Resident extends Person {


    @JsonIgnore
    @OneToMany(mappedBy = "resident")
    private List<Annoucements> annoucements = new ArrayList<>();
    @JsonIgnore
    @OneToMany(mappedBy = "resident")
    private List<Reservation> reservations = new ArrayList<>();
    @JsonIgnore
    @OneToMany(mappedBy = "resident")
    private List<Occurrences> occurrences = new ArrayList<>();

    public Resident(Integer id, String name, String cpf, String email, String password) {
        super(id, name, cpf, email, password);

    }

    public Resident(ResidentDTO obj) {
        this.id = obj.getId();
        this.name = obj.getName();
        this.cpf = obj.getCpf();
        this.email = obj.getEmail();
        this.profiles = obj.getProfiles().stream().map(Profile::getCode).collect(Collectors.toSet());
        this.dateCriation = obj.getDateCriation();
    }

    public Resident() {
        super();
        addProfiles(Profile.RESIDENT);
    }
}
