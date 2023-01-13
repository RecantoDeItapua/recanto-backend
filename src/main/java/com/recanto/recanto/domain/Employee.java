package com.recanto.recanto.domain;

import com.recanto.recanto.enums.Profile;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@EqualsAndHashCode
@AllArgsConstructor
@Builder
@Entity
public class Employee extends Person{

    @Getter @Setter
    @OneToMany(mappedBy = "employee")
    private List<ServiceProvider> providers = new ArrayList<>();


    public Employee(UUID id, String name, String cpf, String email, String password) {
        super(id, name, cpf, email, password);
        addProfiles(Profile.CONCIERGE);
    }

    public Employee() {
        super();
        addProfiles(Profile.CONCIERGE);
    }

}
