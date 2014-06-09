/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vehicles.domain.entities;

import com.vehicles.domain.interfaces.Vehicle;
import com.vehicles.domain.enums.BicycleType;
import com.vehicles.domain.enums.VehicleType;
import java.util.UUID;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author kavan
 */
@Document
public class Bicycle implements Vehicle{

    @Id
    private String id;
    private Color color;
    private final VehicleType vehicleType;
    private BicycleType bicycleType;

    public Bicycle() {
       this.vehicleType = VehicleType.BICYCLE;
       init(null,null);
    }

    public Bicycle(BicycleType bicycleType, Color color) {
        this();
        init(bicycleType,color);
    }
    private void init(BicycleType bicycleType, Color color){
        this.id = UUID.randomUUID().toString();
        this.bicycleType = bicycleType;
        this.color = color;
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
    
    public BicycleType getBicycleType() {
        return bicycleType;
    }

    public void setBicycleType(BicycleType bicycleType) {
        this.bicycleType = bicycleType;
    }
    @Override
    public String toString() {
        return super.toString() + "Bicycle{" + "bicycleType=" + bicycleType + '}';
    }

    

    
    
}
