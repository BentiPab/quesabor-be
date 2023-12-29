package com.queempanadas.dao.jpa;

import com.queempanadas.dao.IDAO;
import com.queempanadas.exceptions.AbstractException;
import com.queempanadas.utils.PersistenceUtils;
import jakarta.persistence.PersistenceException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public abstract class AbstractJPADAO<T> implements IDAO<T> {

    protected Class<T> clazz;
    private static Session session;

    public AbstractJPADAO(Class<T> clazz) {
        this.clazz = clazz;
    }

    protected Session getSession() {
        if (session == null || !session.isOpen()) {
            session = getSessionFactory().openSession();
        }
        return session;
    }

    protected SessionFactory getSessionFactory() {
        Configuration configuration = new Configuration().configure();
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties())
                .configure();
        return configuration.buildSessionFactory(builder.build());
    }

    @Override
    public T getByKey(int key) {
        Session s = getSession();
        return s.byId(this.clazz)
                .load(key);
    }

    @Override
    public T persist(T entity) throws AbstractException {
        Session s = getSession();
        Transaction t = s.beginTransaction();
        try {
            s.persist(entity);
            t.commit();
        } catch (PersistenceException e) {
            PersistenceUtils.parsePersistenceException(e);
        }
        return entity;
    }

    @Override
    public T update(T entity) throws AbstractException {
        Session s = getSession();
        Transaction t = s.beginTransaction();
        try {
            s.merge(entity);
            t.commit();
        } catch (PersistenceException e) {
            PersistenceUtils.parsePersistenceException(e);
        }
        return entity;
    }

    @Override
    public T delete(T entity) throws AbstractException {
        Session s = getSession();
        Transaction t = s.beginTransaction();
        try {
            s.remove(entity);
            t.commit();
        } catch (PersistenceException e) {
            PersistenceUtils.parsePersistenceException(e);
        }
        return entity;
    }

    @Override
    public T deleteByKey(int key) throws AbstractException {
        Session s = getSession();
        try {
            T entity = s.byId(this.clazz).load(key);
            return delete(entity);
        } catch (PersistenceException e) {
            PersistenceUtils.parsePersistenceException(e);
        }
        return null;
    }

    @Override
    public void warmup() {
        try (Session s = getSession()) {
        }
    }
}

