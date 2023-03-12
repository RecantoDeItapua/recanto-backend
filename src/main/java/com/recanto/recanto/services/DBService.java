package com.recanto.recanto.services;

import com.recanto.recanto.domain.Adress;
import com.recanto.recanto.domain.Annoucements;
import com.recanto.recanto.domain.Employee;
import com.recanto.recanto.domain.Resident;
import com.recanto.recanto.domain.ServiceProvider;
import com.recanto.recanto.enums.Profile;
import com.recanto.recanto.enums.Situation;
import com.recanto.recanto.repository.AdressRepository;
import com.recanto.recanto.repository.AnnoucementsRepository;
import com.recanto.recanto.repository.EmployeeRepository;
import com.recanto.recanto.repository.ResidentRepository;
import com.recanto.recanto.repository.ServiceProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;

@Service
public class DBService {

    @Autowired
    private ResidentRepository residentRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private ServiceProviderRepository serviceProviderRepository;
    @Autowired
    private AnnoucementsRepository annoucementsRepository;
    @Autowired
    private BCryptPasswordEncoder encoder;



    public void instenceDB() {
        Resident r1 = new Resident(
                null, "Administrador","894.688.570-00", "recanto@email.com", encoder.encode("123"));
        r1.addProfiles(Profile.ADMIN);

        Resident r2 = new Resident(
                null, "Administrador","894.688.570-11", "recanto2@email.com", encoder.encode("123"));
        r1.addProfiles(Profile.ADMIN);

        residentRepository.saveAll(Collections.singletonList(r1));
        residentRepository.saveAll(Collections.singletonList(r2));
    }
}
