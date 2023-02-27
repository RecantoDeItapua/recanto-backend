package com.recanto.recanto.domain.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.recanto.recanto.domain.Resident;
import com.recanto.recanto.enums.Profile;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
public class ResidentDTO {
    protected Integer id;
    protected String name;
    protected String cpf;
    protected String email;
    private String phoneNumber;
    private String adress;
    protected String password;
    protected Set<Integer> profiles = new HashSet<>();


    @JsonFormat(pattern = "dd/MM/yyyy")
    protected LocalDate dateCriation = LocalDate.now();

    public ResidentDTO() {
        super();
        addProfile(Profile.RESIDENT);
    }

    public ResidentDTO(Resident obj) {
        this.id = obj.getId();
        this.name = obj.getName();
        this.cpf = obj.getCpf();
        this.email = obj.getEmail();
        this.phoneNumber = obj.getPhoneNumber();
        this.adress = obj.getAdress();
        this.password = obj.getPassword();
        this.profiles = obj.getProfiles().stream().map(Profile::getCode).collect(Collectors.toSet());
        this.dateCriation = obj.getDateCriation();
        addProfile(Profile.RESIDENT);
    }

    public Set<Profile> getProfiles() {
        return profiles.stream().map(Profile::toEnum).collect(Collectors.toSet());
    }

    public void addProfile(Profile profiles) {
        this.profiles.add(profiles.getCode());
    }
}
