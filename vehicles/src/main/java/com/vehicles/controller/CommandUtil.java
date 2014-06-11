/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vehicles.controller;

import com.vehicles.domain.entities.Bicycle;
import com.vehicles.domain.entities.Boat;
import com.vehicles.domain.entities.Car;
import com.vehicles.domain.entities.Color;
import com.vehicles.domain.entities.Ferrari;
import com.vehicles.domain.entities.Motorcycle;
import com.vehicles.domain.entities.Order;
import com.vehicles.domain.enums.BicycleType;
import com.vehicles.domain.enums.ColorName;
import com.vehicles.domain.enums.RiderGender;
import com.vehicles.domain.interfaces.Vehicle;
import com.vehicles.exceptions.BadCommandException;
import org.springframework.stereotype.Component;

/**
 *
 * @author kavan
 */
@Component
public class CommandUtil {

    public Order convertCommandToOrder(String command) throws BadCommandException {
        Vehicle vehicle = null;
        String[] ar = getCommandAsStringArray(command);
        if (ar[0].equalsIgnoreCase("car")) {
            vehicle = buildCarFromStringArray(ar);
        } else if (ar[0].equalsIgnoreCase("motorcycle")) {
            vehicle = buildMotorcycleFromStringArray(ar);
        } else if (ar[0].equalsIgnoreCase("boat")) {
              vehicle = buildBoatFromStringArray(ar);
        } else if (ar[0].equalsIgnoreCase("ferrari")) {
            vehicle = new Ferrari();
        } else if (ar[0].equalsIgnoreCase("bicycle")) {
             vehicle = buildBicycleFromStringArray(ar);
        } else {
            throw new BadCommandException(" [Bad vehicle argument!] ");
        }
        return new Order(vehicle);
    }

    private String[] getCommandAsStringArray(String command) {
        command = command.trim();
        String[] ar = command.split("\\s+");
        for (int i = 0; i < ar.length; i++) {
            ar[i] = ar[i].trim();
        }
        return ar;
    }

    private Car buildCarFromStringArray(String[] ar) throws BadCommandException {
        Color color = getColorFromArray(ar);
        int wheels = -1, steeringWheels = -1;
        try {
            wheels = Integer.parseInt(ar[2]);
            steeringWheels = Integer.parseInt(ar[3]);
        } catch (Exception e) {
            throw new BadCommandException(" [Bad wheel / steeringWheel argument!] ");
        }
        return new Car(color, wheels, steeringWheels);
    }

    private Boat buildBoatFromStringArray(String[] ar) throws BadCommandException {
        Color color = getColorFromArray(ar);
        boolean floats = boatFloats(ar);
        int periscope = -1;
        if (!floats) {
            try {
                periscope = Integer.parseInt(ar[3]);

            } catch (Exception e) {
                throw new BadCommandException(" [Bad periscope argument!] ");
            }
        }
        return new Boat(color, floats, periscope);
    }
    private boolean boatFloats(String[] ar) throws BadCommandException {
        if (ar[2].equalsIgnoreCase("yes")) {
            return true;
        } else if (ar[2].equalsIgnoreCase("no")) {
            return false;
        } else {
            throw new BadCommandException(" [Bad floats argument! ] ");
        }
    }

    private Motorcycle buildMotorcycleFromStringArray(String[] ar) throws BadCommandException {
        RiderGender sex = null;
        Color color = getColorFromArray(ar);
        if (ar[2].equalsIgnoreCase("f")) {
            sex = RiderGender.FEMALE;
        } else if (ar[2].equalsIgnoreCase("m")) {
            sex = RiderGender.MALE;
        } else {
            throw new BadCommandException(" [Bad gender argument!] ");
        }
        return new Motorcycle(color, sex);

    }
    private Bicycle buildBicycleFromStringArray(String[] ar) throws BadCommandException {
        Color color = getColorFromArray(ar);
        BicycleType type = null;
        if(ar[2].equalsIgnoreCase("road")){
            type = BicycleType.ROAD;
        }else if(ar[2].equalsIgnoreCase("mtb")){
            type = BicycleType.MTB;
        }else if(ar[2].equalsIgnoreCase("dirt")){
            type = BicycleType.DIRT;
        }else{
            throw new BadCommandException(" [Bad BicycleType argument!] ");
        }     
        return new Bicycle(type, color);

    }

    private Color getColorFromArray(String[] ar) throws BadCommandException {
        Color color = null;
        for (ColorName cn : ColorName.values()) {
            if (cn.toString().equalsIgnoreCase(ar[1])) {
                color = new Color(cn);
            }
        }
        if (color == null) {
            throw new BadCommandException(" [Bad color argument!] ");
        }
        return color;
    }

}
