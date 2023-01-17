package com.recanto.recanto.domain;

import com.recanto.recanto.enums.Profile;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode
@AllArgsConstructor
@Entity
@Getter
@Setter
public class Employee extends Person{

    @OneToMany(mappedBy = "employee")
    private List<ServiceProvider> providers = new ArrayList<>();

    @OneToMany(mappedBy = "employee")
    private List<Annoucements> annoucements = new ArrayList<>();

    @OneToMany(mappedBy = "employee")
    private List<Reservation> reservations = new ArrayList<>();

    @OneToMany(mappedBy = "employee")
    private List<Occurrences> occurrences = new ArrayList<>();


    public Employee(Integer id, String name, String cpf, String email, String password) {
        super(id, name, cpf, email, password);
        addProfiles(Profile.CONCIERGE);
    }

    public Employee() {
        super();
        addProfiles(Profile.CONCIERGE);
    }

}
