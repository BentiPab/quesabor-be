package com.queempanadas.dao.jpa.implementations;

import com.queempanadas.dao.jpa.AbstractJPADAO;
import com.queempanadas.model.Empanada;
import jakarta.persistence.PersistenceContext;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmpanadaJPADAO extends AbstractJPADAO<Empanada> {

    public EmpanadaJPADAO() {
        super(Empanada.class);
    }

    public List<Empanada> getAll() {
        Query<Empanada> query = getSession().createNamedQuery("GetAllEmpanadas", Empanada.class);
        return query.getResultList();
    }

    public List<Empanada> getMultipleByIds(List<Long> ids) {
        Query<Empanada> query = getSession().createNamedQuery("GetMultipleByIds", Empanada.class);
        query.setParameter("ids", ids);
        return query.getResultList();
    }

}
