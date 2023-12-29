package com.queempanadas.services;

import com.queempanadas.dao.jpa.implementations.UserJPADAO;
import com.queempanadas.exceptions.AbstractException;
import com.queempanadas.exceptions.FieldValidationException;
import com.queempanadas.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService extends AbstractService<User> implements UserDetailsService {
    public UserService() {
        super(new UserJPADAO());
    }
    protected void validateLogin(String username, String password) throws FieldValidationException {
        if (username != null || password != null) {
         throw new FieldValidationException("password or username","Some fields are null");
        }
    }

    @Override
    protected void validate(User user) throws FieldValidationException {

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = ((UserJPADAO) this.dao).findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }

    public User loginUser(String username, String password) throws AbstractException {
        this.validateLogin(username, password);
        return ((UserJPADAO) this.dao).loginUser(username, password);
    }
}
