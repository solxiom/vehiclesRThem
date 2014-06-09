/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vehicles.domain.entities;

import com.vehicles.domain.interfaces.Vehicle;
import com.vehicles.domain.enums.RiderGender;
import com.vehicles.domain.enums.VehicleType;
import java.util.UUID;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author kavan
 */
@Document
public class Motorcycle implements Vehicle {

    @Id
    private String id;
    private Color color;
    private final VehicleType vehicleType;

    private RiderGender riderGender;

    public Motorcycle() {
        this.vehicleType = VehicleType.MOTORCYCLE;
        init(null, null);
    }

    public Motorcycle(Color color, RiderGender riderGender) {
        this();
        init(color, riderGender);
    }

    private void init(Color color, RiderGender riderGender) {
        this.id = UUID.randomUUID().toString();
        this.color = color;
        this.riderGender = riderGender;
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

    public RiderGender getRiderGender() {
        return riderGender;
    }

    public void setRiderGender(RiderGender riderGender) {
        this.riderGender = riderGender;
    }

    @Override
    public String toString() {
        return super.toString() + "Motorcycle{" + "riderGender=" + riderGender + '}';
    }

}
