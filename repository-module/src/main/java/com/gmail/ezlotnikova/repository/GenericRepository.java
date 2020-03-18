package com.gmail.ezlotnikova.repository;

import java.util.List;

public interface GenericRepository<I, T> {

    T add(T entity);

    void merge(T entity);

    void remove(T entity);

    T findById(I id);

    List<T> findAll();

}