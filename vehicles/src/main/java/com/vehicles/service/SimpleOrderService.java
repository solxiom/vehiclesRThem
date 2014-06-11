/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vehicles.service;

import com.vehicles.domain.entities.Order;
import com.vehicles.domain.enums.LastUpdate;
import com.vehicles.domain.interfaces.Vehicle;
import com.vehicles.exceptions.StoarageOutOfColorException;
import com.vehicles.repository.interfaces.OrderRepository;
import com.vehicles.service.interfaces.ColorService;
import com.vehicles.service.interfaces.LastUpdateService;
import com.vehicles.service.interfaces.OrderService;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author kavan
 */
@Service
public class SimpleOrderService extends GenericServiceImpl<Order> implements OrderService {

    private ColorService colorService;
    private LastUpdateService updateService;
//    private final String updateKey;
    
    @Autowired
    public SimpleOrderService(OrderRepository repository, 
            ColorService colorService, 
            LastUpdateService updateService) {

        super(repository);
        this.colorService = colorService;
        this.updateService = updateService;
//        this.updateKey = updateKey;
    }

    @Override
    public void save(Order order) throws Exception, NullPointerException, StoarageOutOfColorException {
        if (order == null || order.getVehicle() == null || order.getVehicle().getColor() == null) {
            throw new NullPointerException("Order and Order's Vehicle and Vehicle's Color should not be null!");
        }
        if (!colorService.isColorExists(order.getVehicle().getColor().getName())) {
            throw new StoarageOutOfColorException(", for color " + order.getVehicle().getColor().getName());
        }
        super.save(order);
        colorService.popColor(order.getVehicle().getColor().getName());
        updateService.save(new LastUpdate(getUpdateKey(), new Date()));
    }

    @Override
    public List<Order> findByVehicle(Vehicle vehicle) {
        if (vehicle == null) {
            return new LinkedList<Order>();
        }
        OrderRepository repo = (OrderRepository) repository;
        return repo.findByVehicle(vehicle);
    }

    @Override
    public Order findOneByVehicle(Vehicle vehicle) {
        if (vehicle == null) {
            return null;
        }
        OrderRepository repo = (OrderRepository) repository;
        return repo.findOneByVehicle(vehicle);

    }

}
