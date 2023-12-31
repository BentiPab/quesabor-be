package com.queempanadas.services;

import com.queempanadas.dao.jpa.implementations.UserJPADAO;
import com.queempanadas.exceptions.FieldValidationException;
import com.queempanadas.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailsService extends AbstractService<User> implements UserDetailsService {

    public CustomUserDetailsService() {
        super(new UserJPADAO());
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = ((UserJPADAO) this.dao).findByUsername(username);
        List<String> roles = new ArrayList<>();
        roles.add("USER");
        UserDetails userDetails =
                org.springframework.security.core.userdetails.User.builder()
                        .username(user.getUsername())
                        .password(user.getPassword())
                        .roles(roles.toArray(new String[0]))
                        .build();
        return userDetails;
    }

    @Override
    protected void validate(User user) throws FieldValidationException {

    }
}
