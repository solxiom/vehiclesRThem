/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vehicles.service;

import com.vehicles.service.interfaces.GenericService;
import com.vehicles.repository.interfaces.GenericRepository;
import java.util.List;
import org.springframework.core.GenericTypeResolver;

/**
 *
 * @author kavan
 */
public class GenericServiceImpl<T> implements GenericService<T> {

    Class<T> clazz;
    GenericRepository<T> repository;
    private String updateKey;
    public GenericServiceImpl(GenericRepository<T> repository) {
        init(repository);
    }

    private void init(GenericRepository<T> repository) {
        this.repository = repository;
        this.clazz = (Class<T>) GenericTypeResolver
                .resolveTypeArgument(getClass(), GenericServiceImpl.class);
    }

    @Override
    public void setUpdateKey(String updateKey) {
        this.updateKey = updateKey;
    }

    @Override
    public String getUpdateKey() {
       return this.updateKey;
    }
    
    
    @Override
    public void save(T entity) throws Exception {
        if (entity == null) {
            throw new NullPointerException("Can't save null entity!");
        } else {
            this.repository.save(entity);
        }
    }

    @Override
    public void remove(T entity) {
        this.repository.remove(entity);
    }

    @Override
    public T findOneByField(String key, String value) {
        return this.repository.findOneByField(key, value);
    }

    @Override
    public List<T> findByField(String key, String value) {
        return this.repository.findByField(key, value);
    }

    @Override
    public List<T> findAll() {
        return this.repository.findAll();
    }
}
