/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vehicles.repository.interfaces;

import com.vehicles.domain.entities.Order;
import com.vehicles.domain.interfaces.Vehicle;
import java.util.List;

/**
 *
 * @author kavan
 */
public interface OrderRepository extends GenericRepository<Order> {

    public List<Order> findByVehicle(Vehicle vehicle);

    public Order findOneByVehicle(Vehicle vehicle);

}
