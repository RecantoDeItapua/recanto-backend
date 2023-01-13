package com.recanto.recanto.domain;


import com.recanto.recanto.enums.Profile;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class Resident extends Person {
    @Getter @Setter
    @OneToMany(mappedBy = "resident")
    private List<ServiceProvider> providers = new ArrayList<>();

    public Resident(UUID id, String name, String cpf, String email, String password) {
        super(id, name, cpf, email, password);
    }

    public Resident() {
        super();
        addProfiles(Profile.RESIDENT);
    }
}
