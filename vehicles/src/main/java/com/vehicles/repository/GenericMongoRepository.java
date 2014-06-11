/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vehicles.repository;

import com.vehicles.repository.interfaces.GenericRepository;
import com.vehicles.config.SpringMongoConfig;
import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.GenericTypeResolver;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author kavan
 */

public class GenericMongoRepository<T> implements GenericRepository<T> {

    ApplicationContext ctx;
//    MongoOperations mongoTemplate;
    MongoTemplate mongoTemplate;
    Class<T> clazz;

    public GenericMongoRepository() {
        init(SpringMongoConfig.class);
    }

    public GenericMongoRepository(Class mongoConfigClass) {
        init(mongoConfigClass);
    }
    private void init(Class config) {
        ctx = new AnnotationConfigApplicationContext(config);
        mongoTemplate = (MongoTemplate) ctx.getBean("mongoTemplate");
        this.clazz = (Class<T>) GenericTypeResolver
                .resolveTypeArgument(getClass(), GenericMongoRepository.class);
    }

    @Override
    public T findOneByField(String field, String value) {
        Query query = new Query(Criteria.where(field).is(value));
        T result = (T) mongoTemplate.findOne(query, clazz);
        return result;
    }

    @Override
    public List<T> findByField(String key, String value) {
        Query query = new Query(Criteria.where(key).is(value));
        List<T> result = (List<T>) mongoTemplate.find(query, clazz);
        return result;
    }

    @Override
    public List<T> findAll() {
        List<T> list = mongoTemplate.findAll(clazz);
        return list;
    }

    @Override
    public void save(T entity) {
        mongoTemplate.save(entity);
    }

    @Override
    public void remove(T entity) {
        mongoTemplate.remove(entity);
    }

}
