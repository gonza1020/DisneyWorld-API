package com.disney.base;

import java.util.List;

public interface BaseController<T extends BaseEntity<ID>, ID> {

     T save(T entity);

     T findById(ID id);

     void delete(ID id);

     List<T> findAll();

}
