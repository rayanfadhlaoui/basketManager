package com.basket.manager.services;

import com.basket.manager.entities.GenericEntity;
import com.basket.manager.repositories.GenericRepository;

import javax.transaction.Transactional;
import java.util.List;

public class GenericService<E extends GenericEntity, T extends GenericRepository<E>> {

    protected T repository;

    GenericService(T repository) {
        this.repository = repository;
    }


    @Transactional
    public void save(List<E> entities) {
        repository.saveAll(entities);
    }

    public List<E> getAll() {
        return repository.getAll();
    }
}
