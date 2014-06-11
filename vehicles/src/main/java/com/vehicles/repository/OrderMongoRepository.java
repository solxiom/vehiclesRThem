/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vehicles.repository;

import com.vehicles.repository.interfaces.OrderRepository;
import com.vehicles.domain.entities.Order;
import com.vehicles.domain.interfaces.Vehicle;
import java.util.LinkedList;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author kavan
 */
@Repository
public class OrderMongoRepository extends GenericMongoRepository<Order> implements OrderRepository {

    public OrderMongoRepository() {
    }

    public OrderMongoRepository(Class mongoConfigClass) {
        super(mongoConfigClass);
    }

    @Override
    public List<Order> findByVehicle(Vehicle vehicle) {
        List<Order> list = mongoTemplate.findAll(clazz);
        List<Order> result_list = new LinkedList<>();
        for (Order od : list) {
            if (od.getVehicle().getId().equals(vehicle.getId())) {
                result_list.add(od);
            }
        }
        return result_list;
    }

    @Override
    public Order findOneByVehicle(Vehicle vehicle) {
        List<Order> list = mongoTemplate.findAll(clazz);
        for (Order od : list) {
            if (od.getVehicle().getId().equals(vehicle.getId())) {
                return od;
            }
        }
        return null;
    }

}
