/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vehicles.service.interfaces;

import com.vehicles.domain.entities.Order;
import com.vehicles.domain.interfaces.Vehicle;
import com.vehicles.exceptions.StoarageOutOfColorException;
import java.util.List;

/**
 *
 * @author kavan
 */
public interface OrderService extends GenericService<Order> {
    
    public List<Order> findByVehicle(Vehicle vehicle);

    public Order findOneByVehicle(Vehicle vehicle);
    
}
