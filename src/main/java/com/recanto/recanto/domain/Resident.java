package com.recanto.recanto.domain;


import com.recanto.recanto.domain.dtos.ResidentDTO;
import com.recanto.recanto.enums.Profile;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import java.util.stream.Collectors;


@EqualsAndHashCode
@Getter
@Setter
@Entity
public class Resident extends Person {

    private String phoneNumber;
    private String adress;

    public Resident(Integer id, String name, String cpf, String email, String password) {
        super(id, name, cpf, email, password);

    }

    public Resident(ResidentDTO obj) {
        this.id = obj.getId();
        this.name = obj.getName();
        this.cpf = obj.getCpf();
        this.email = obj.getEmail();
        this.phoneNumber = obj.getPhoneNumber();
        this.adress = obj.getAdress();
        this.password= obj.getPassword();
        this.profiles = obj.getProfiles().stream().map(Profile::getCode).collect(Collectors.toSet());
        this.dateCriation = obj.getDateCriation();
        addProfiles(Profile.RESIDENT);
    }

    public Resident() {
        super();
        addProfiles(Profile.RESIDENT);
    }
}
