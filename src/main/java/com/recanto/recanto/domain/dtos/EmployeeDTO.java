package com.recanto.recanto.domain.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.recanto.recanto.domain.Employee;
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
public class EmployeeDTO {
    protected Integer id;
    @NotNull(message= "name is riquered")
    protected String name;
    @NotNull(message = "Cpf is riquered")
    @CPF
    protected String cpf;
    @NotNull(message = "Email is riquered")
    protected String email;
    protected String password;
    protected Set<Integer> profiles = new HashSet<>();


    @JsonFormat(pattern = "dd/MM/yyyy")
    protected LocalDate dateCriation = LocalDate.now();

    public EmployeeDTO() {
        super();
        addProfile(Profile.EMPLOYEE);
    }

    public EmployeeDTO(Employee obj) {
        this.id = obj.getId();
        this.name = obj.getName();
        this.cpf = obj.getCpf();
        this.email = obj.getEmail();
        this.profiles = obj.getProfiles().stream().map(Profile::getCode).collect(Collectors.toSet());
        this.dateCriation = obj.getDateCriation();
        addProfile(Profile.EMPLOYEE);
    }

    public Set<Profile> getProfiles() {
        return profiles.stream().map(Profile::toEnum).collect(Collectors.toSet());
    }

    public void addProfile(Profile profiles) {
        this.profiles.add(profiles.getCode());
    }
}
