package com.recanto.recanto.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.recanto.recanto.enums.Profile;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;


@AllArgsConstructor
@EqualsAndHashCode
@Entity
public abstract class Person {

    @Getter @Setter
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    protected UUID id;

    @Getter @Setter
    protected String name;

    @Getter @Setter
    @Column(unique = true)
    protected String cpf;

    @Getter @Setter
    @Column(unique = true)
    protected String email;

    @Getter @Setter
    protected String password;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "PROFILES")
    protected Set<Integer> profiles = new HashSet<>();

    @Getter @Setter
    @JsonFormat(pattern = "dd/MM/yyyy")
    protected LocalDate dateCriation = LocalDate.now();

    public Person() {
        super();
        addProfiles(Profile.RESIDENT);
    }

    public Person(UUID id, String name, String cpf, String email, String password) {
    }


    public Set<Profile> getProfiles() {
        return profiles.stream().map(x -> Profile.toEnum(x)).collect(Collectors.toSet());
    }

    public void addProfiles(Profile profiles) {
        this.profiles.add(profiles.getCode());
    }
}
