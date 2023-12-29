package com.queempanadas.dao.jpa.implementations;

import com.queempanadas.dao.jpa.AbstractJPADAO;
import com.queempanadas.model.Empanada;
import com.queempanadas.model.Quality;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class QualityJPADAO extends AbstractJPADAO<Quality> {


    public QualityJPADAO() {
        super(Quality.class);
    }


    public List<Quality> getAll() {
        Query<Quality> query = getSession().createNamedQuery("GetAllQualities", Quality.class);
        return query.getResultList();
    }

}
