/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vehicles.controller;

import com.vehicles.domain.entities.Order;
import com.vehicles.domain.enums.ColorName;
import com.vehicles.domain.enums.VehicleType;
import com.vehicles.domain.interfaces.Vehicle;
import com.vehicles.exceptions.BadCommandException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author kavan
 */
public class CommandUtilTest {

    public CommandUtilTest() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of convertCommandToOrder method, of class CommandUtil.
     */
    @Test
    public void testConvertCommandToOrder_whenCorrectCarCommand() throws BadCommandException {
        String command = "car red 4 1";
        CommandUtil instance = new CommandUtil();
        Order order = instance.convertCommandToOrder(command);
        Vehicle vehicle = order.getVehicle();
        assertTrue(vehicle.getVehicleType() == VehicleType.CAR);
        assertTrue(vehicle.getColor().getName() == ColorName.RED);
//        assertTrue(vehicle.getColor().getName() == ColorName.RED);

        System.out.println(order.getVehicle());
//        fail("The test case is a prototype.");
    }

    @Test
    public void testConvertCommandToOrder_whenBadCarCommand() throws Exception {
        String command = "car blue 5 oo";
        CommandUtil instance = new CommandUtil();
        try {
            Order order = instance.convertCommandToOrder(command);
            fail("badCommandException expected!");
        } catch (BadCommandException e) {
            assertTrue(true);//ok
        }
    }

    @Test
    public void testConvertCommandToOrder_whenCorrectBicycleCommand() throws Exception {
        String command = "bicycle blue mtb";
        CommandUtil instance = new CommandUtil();
        Order order = instance.convertCommandToOrder(command);
        Vehicle vehicle = order.getVehicle();
        assertTrue(vehicle.getVehicleType() == VehicleType.BICYCLE);
        assertTrue(vehicle.getColor().getName() == ColorName.BLUE);
    }

    @Test
    public void testConvertCommandToOrder_whenBadBicycleCommand() throws Exception {
        String command = "bicycle bluedff mtb";
        CommandUtil instance = new CommandUtil();
        try {
            Order order = instance.convertCommandToOrder(command);
            fail("badCommandException expected!");
        } catch (BadCommandException e) {
            assertTrue(true);//ok
        }

    }

    @Test
    public void testConvertCommandToOrder_whenCorrectMotorcycleCommand() throws Exception {
        String command = "motorcycle black m";
        CommandUtil instance = new CommandUtil();
        Order order = instance.convertCommandToOrder(command);
        Vehicle vehicle = order.getVehicle();
        assertTrue(vehicle.getVehicleType() == VehicleType.MOTORCYCLE);
        assertTrue(vehicle.getColor().getName() == ColorName.BLACK);
    }

    @Test
    public void testConvertCommandToOrder_whenBadMotorcycleCommand() throws Exception {
        String command = "motorcycle me";
        CommandUtil instance = new CommandUtil();
        try {
            Order order = instance.convertCommandToOrder(command);
            fail("badCommandException expected!");
        } catch (BadCommandException e) {
            assertTrue(true);//ok
        }
    }

    @Test
    public void testConvertCommandToOrder_whenCorrectBoatCommand() throws Exception {
        String command = "boat white yes ";
        CommandUtil instance = new CommandUtil();
        Order order = instance.convertCommandToOrder(command);
        Vehicle vehicle = order.getVehicle();
        assertTrue(vehicle.getVehicleType() == VehicleType.BOAT);
        assertTrue(vehicle.getColor().getName() == ColorName.WHITE);
    }

    @Test
    public void testConvertCommandToOrder_whenBadBoatCommand() throws Exception {
        String command = "boat red booo dee";
        CommandUtil instance = new CommandUtil();
        try {
            Order order = instance.convertCommandToOrder(command);
            fail("badCommandException expected!");
        } catch (BadCommandException e) {
            assertTrue(true);//ok
        }
    }

    @Test
    public void testConvertCommandToOrder_whenCorrectFerrariCommand() throws Exception {
        String command = "ferrari";
        CommandUtil instance = new CommandUtil();
        Order order = instance.convertCommandToOrder(command);
        Vehicle vehicle = order.getVehicle();
        assertTrue(vehicle.getVehicleType() == VehicleType.FERRARI);
        assertTrue(vehicle.getColor().getName() == ColorName.RED);
    }

    @Test
    public void testConvertCommandToOrder_whenBadFerrariCommand() throws Exception {
        String command = "ferraridfdf";
        CommandUtil instance = new CommandUtil();
        try {
            Order order = instance.convertCommandToOrder(command);
            fail("badCommandException expected!");
        } catch (BadCommandException e) {
            assertTrue(true);//ok
        }
    }

}
