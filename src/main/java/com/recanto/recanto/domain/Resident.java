package com.recanto.recanto.domain;


import com.recanto.recanto.enums.Profile;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@Getter
@Setter
public class Resident extends Person {

    @OneToMany(mappedBy = "resident")
    private List<ServiceProvider> providers = new ArrayList<>();

    @OneToMany(mappedBy = "resident")
    private List<Annoucements> annoucements = new ArrayList<>();

    @OneToMany(mappedBy = "resident")
    private List<Reservation> reservations = new ArrayList<>();

    @OneToMany(mappedBy = "resident")
    private List<Occurrences> occurrences = new ArrayList<>();

    public Resident(Integer id, String name, String cpf, String email, String password) {
        super(id, name, cpf, email, password);

    }

    public Resident() {
        super();
        addProfiles(Profile.RESIDENT);
    }
}
