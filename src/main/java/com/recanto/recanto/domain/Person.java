package com.recanto.recanto.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.recanto.recanto.enums.Profile;
import lombok.AllArgsConstructor;
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
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Getter
@Setter

public abstract class Person {


    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;


    protected String name;


    @Column(unique = true)
    protected String cpf;


    @Column(unique = true)
    protected String email;


    protected String password;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "PROFILES")
    protected Set<Integer> profiles = new HashSet<>();


    @JsonFormat(pattern = "dd/MM/yyyy")
    protected LocalDate dateCriation = LocalDate.now();

    @JsonIgnore
    @OneToMany(mappedBy = "person")
    private List<ServiceProvider> providers = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "person")
    private List<Annoucements> annoucements = new ArrayList<>();

    @OneToMany(mappedBy = "person")
    private List<Occurrences> occurrences = new ArrayList<>();

    public Person() {
        super();
        addProfiles(Profile.RESIDENT);
    }

    public Person(Integer id, String name, String cpf, String email, String password) {
        super();
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.email = email;
        this.password = password;
        addProfiles(Profile.RESIDENT);



    }


    public Set<Profile> getProfiles() {
        return profiles.stream().map(Profile::toEnum).collect(Collectors.toSet());
    }

    public void addProfiles(Profile profiles) {
        this.profiles.add(profiles.getCode());
    }
}
