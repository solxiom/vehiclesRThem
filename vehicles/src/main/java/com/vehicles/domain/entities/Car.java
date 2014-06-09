/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vehicles.domain.entities;

import com.vehicles.domain.interfaces.Vehicle;
import com.vehicles.domain.enums.VehicleType;
import java.util.UUID;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author kavan
 */
@Document
public class Car implements Vehicle {

    @Id
    private String id;
    private Color color;
    private final VehicleType vehicleType;

    private int wheels;
    private int steeringWheels;

    public Car() {
        init(null,0,0);
        this.vehicleType = VehicleType.CAR;
    }
    public Car(Color color, int wheels, int steeringWheels) {
        init(color,wheels,steeringWheels);
        this.vehicleType = VehicleType.CAR;

    }
    public Car(Color color, int wheels, int steeringWheels, VehicleType type) {
        init(color,wheels,steeringWheels);
        this.vehicleType = type;

    }
    private void init(Color color, int wheels, int steeringWheels) {
        this.id = UUID.randomUUID().toString();
        this.color = color;
        this.wheels = wheels;
        this.steeringWheels = steeringWheels;
    }
    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public int getWheels() {
        return wheels;
    }

    public void setWheels(int wheels) {
        this.wheels = wheels;
    }

    public int getSteeringWheels() {
        return steeringWheels;
    }

    public void setSteeringWheels(int steeringWheels) {
        this.steeringWheels = steeringWheels;
    }

    @Override
    public String toString() {
        return "Car{"+ color + ", wheels=" + wheels + ", steeringWheels=" + steeringWheels + '}';
    }

}
