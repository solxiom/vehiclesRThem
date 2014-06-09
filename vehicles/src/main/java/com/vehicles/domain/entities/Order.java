/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vehicles.domain.entities;

import com.vehicles.domain.interfaces.Vehicle;
import java.util.UUID;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author kavan
 */
@Document
public class Order {

    @Id
    private String id;
    private Vehicle vehicle;

    public Order() {
        this.id = UUID.randomUUID().toString();
    }

    public Order(Vehicle vehicle) {
        this();
        this.vehicle = vehicle;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    @Override
    public String toString() {
        return "Order{" + "id=" + id + ", vehicle=" + vehicle.toString() + '}';
    }

}
