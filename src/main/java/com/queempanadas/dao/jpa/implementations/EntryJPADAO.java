package com.queempanadas.dao.jpa.implementations;

import com.queempanadas.dao.jpa.AbstractJPADAO;
import com.queempanadas.model.Entry;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EntryJPADAO extends AbstractJPADAO<Entry> {


    public EntryJPADAO() {
        super(Entry.class);
    }

    public List<Entry> getAll() {
        Query<Entry> query = getSession().createNamedQuery("GetAllEntries", Entry.class);
        return query.getResultList();
    }


}
