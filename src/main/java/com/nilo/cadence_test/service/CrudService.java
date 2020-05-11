package com.nilo.cadence_test.service;

import java.util.Optional;

public interface CrudService<T, I> {
    Optional<T> findById(I id);

    T save(T entity);

    void delete(T entity);

    void deleteById(I id);
}
