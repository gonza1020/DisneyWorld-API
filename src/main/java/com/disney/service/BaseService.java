package com.disney.service;

import com.disney.base.BaseEntity;
import com.disney.model.Genre;

import java.util.List;

public interface BaseService<T extends BaseEntity> {

    T save(T entity);

    T findById(Long id);

    void delete(Long id);

    T update(T entity , Long id);

    List<T> findAll();

}
