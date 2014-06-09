/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vehicles.domain.entities;

import com.vehicles.domain.enums.ColorName;
import com.vehicles.domain.interfaces.Vehicle;
import com.vehicles.domain.enums.VehicleType;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author kavan
 */
@Document
public class Ferrari extends Car implements Vehicle{
    
    public Ferrari() {
       super(new Color(ColorName.RED), 4, 1, VehicleType.FERRARI);
    }

    @Override
    public void setSteeringWheels(int steeringWheels) {
        super.setSteeringWheels(1);
    }

    @Override
    public void setWheels(int wheels) {
        super.setWheels(4); 
    }

    @Override
    public void setColor(Color color) {
        super.setColor(new Color(ColorName.RED)); 
    }
    
    
    
}
