package com.queempanadas.dao.jpa.implementations;

import com.queempanadas.dao.jpa.AbstractJPADAO;
import com.queempanadas.model.User;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class UserJPADAO extends AbstractJPADAO<User> {
    public UserJPADAO() {
        super(User.class);
    }


    public User findByUsername(String username) {
        Query<User> query = getSession().createNamedQuery("FindByUser", User.class);
        query.setParameter("username", username);
        return query.getSingleResult();
    }

}
