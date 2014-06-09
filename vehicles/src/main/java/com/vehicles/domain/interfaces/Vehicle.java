/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vehicles.domain.interfaces;

import com.vehicles.domain.entities.Color;
import com.vehicles.domain.enums.VehicleType;

/**
 *
 * @author kavan
 */
public interface Vehicle {
    
    public String getId();

    public void setId(String id);

    public Color getColor();

    public void setColor(Color color);

    public VehicleType getVehicleType();
}
