package com.recanto.recanto.services;

import com.recanto.recanto.domain.Annoucements;
import com.recanto.recanto.domain.Employee;
import com.recanto.recanto.domain.Resident;
import com.recanto.recanto.domain.ServiceProvider;
import com.recanto.recanto.enums.Profile;
import com.recanto.recanto.enums.Situation;
import com.recanto.recanto.repository.AnnoucementsRepository;
import com.recanto.recanto.repository.EmployeeRepository;
import com.recanto.recanto.repository.ResidentRepository;
import com.recanto.recanto.repository.ServiceProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

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
                null, "Jonas Oliveira","894.688.570-00", "jonas@email.com", encoder.encode("123"));
        r1.addProfiles(Profile.ADMIN);

        Annoucements a1 = new Annoucements(null,"Assembleia Geral", "No dia 15 de novembro não teremos assembleia");

        Employee e1 = new Employee(
                null,"Jaasiel Oliveira","894.688.570-22", "jaasiel@email.com", encoder.encode("123") );
        e1.addProfiles(Profile.EMPLOYEE);

        a1.setPerson(e1);


        ServiceProvider s1 = new ServiceProvider(
                null,"Encanamento","Luis Carlos","878.872.280-50","Realizar serviço solicitado pela moradora", Situation.ABERTO.getCode());

        s1.setPerson(r1);
        s1.setCar("JQS-7322");



        residentRepository.saveAll(Arrays.asList(r1));
        employeeRepository.saveAll(Arrays.asList(e1));
        serviceProviderRepository.saveAll(Arrays.asList(s1));
        annoucementsRepository.saveAll(Arrays.asList(a1));
    }
}
