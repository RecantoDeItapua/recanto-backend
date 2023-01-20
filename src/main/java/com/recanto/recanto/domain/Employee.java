package com.recanto.recanto.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.recanto.recanto.domain.dtos.EmployeeDTO;
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
@Entity
@Getter
@Setter
public class Employee extends Person{

    @JsonIgnore
    @OneToMany(mappedBy = "employee")
    private List<ServiceProvider> providers = new ArrayList<>();
    @JsonIgnore
    @OneToMany(mappedBy = "employee")
    private List<Annoucements> annoucements = new ArrayList<>();
    @JsonIgnore
    @OneToMany(mappedBy = "employee")
    private List<Reservation> reservations = new ArrayList<>();
    @JsonIgnore
    @OneToMany(mappedBy = "employee")
    private List<Occurrences> occurrences = new ArrayList<>();


    public Employee(Integer id, String name, String cpf, String email, String password) {
        super(id, name, cpf, email, password);
        addProfiles(Profile.CONCIERGE);
    }

    public Employee(EmployeeDTO obj) {
        this.id = obj.getId();
        this.name = obj.getName();
        this.cpf = obj.getCpf();
        this.email = obj.getEmail();
        this.profiles = obj.getProfiles().stream().map(Profile::getCode).collect(Collectors.toSet());
        this.dateCriation = obj.getDateCriation();
    }

    public Employee() {
        super();
        addProfiles(Profile.CONCIERGE);
    }

}
