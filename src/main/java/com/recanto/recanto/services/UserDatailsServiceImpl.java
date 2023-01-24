package com.recanto.recanto.services;

import com.recanto.recanto.domain.Person;
import com.recanto.recanto.repository.PersonRepository;
import com.recanto.recanto.security.UserSS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDatailsServiceImpl implements UserDetailsService {
    @Autowired
    private PersonRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Person> user = repository.findByEmail(email);
        if (user.isPresent()) {
            return new UserSS(user.get().getId(), user.get().getEmail(), user.get().getPassword(), user.get().getProfiles());
        }
        throw new UsernameNotFoundException(email);
    }
}
