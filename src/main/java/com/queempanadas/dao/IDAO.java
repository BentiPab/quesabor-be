package com.queempanadas.dao;

public interface IDAO<T> {

    public void warmup();
    public T getByKey(int key);
    public T persist(T entity) throws Exception;
    public T update(T entity) throws Exception;
    public T delete(T entity) throws Exception;

    public T deleteByKey(int key) throws Exception;
}
