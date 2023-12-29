package com.queempanadas.services;

import com.queempanadas.dao.IDAO;
import com.queempanadas.exceptions.FieldValidationException;

public abstract class AbstractService<T> {

    protected final IDAO<T> dao;

    public AbstractService(IDAO<T> dao) {
        this.dao = dao;
    }

    protected abstract void validate(T t) throws FieldValidationException;
    public T create(T t) throws Exception {
            this.validate(t);
            dao.persist(t);
        return t;
    }

    public T update(T t) throws Exception {
        this.validate(t);
        dao.update(t);
        return t;
    }

    public T delete(T t) throws Exception {
        this.validate(t);
        dao.delete(t);
        return t;
    }

    public T deleteByKey(int id) throws Exception {
        return dao.deleteByKey(id);
    }

    public T getById(int id) throws FieldValidationException {
        return dao.getByKey(id);
    }
}
