package com.queempanadas.services;

import com.queempanadas.dao.jpa.implementations.UserJPADAO;
import com.queempanadas.exceptions.AbstractException;
import com.queempanadas.exceptions.FieldValidationException;
import com.queempanadas.model.User;

import org.springframework.stereotype.Service;

@Service
public class UserService extends AbstractService<User>{
    public UserService() {
        super(new UserJPADAO());
    }

    @Override
    protected void validate(User user) throws FieldValidationException {

    }


    public User getUserbyUsername(String username) throws AbstractException {
        return ((UserJPADAO) this.dao).findByUsername(username);
    }
}
