/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vehicles.service;

import com.vehicles.config.SpringMongoTestConfig;
import com.vehicles.domain.entities.Car;
import com.vehicles.domain.entities.Color;
import com.vehicles.domain.entities.Order;
import com.vehicles.domain.enums.ColorName;
import com.vehicles.domain.interfaces.Vehicle;
import com.vehicles.exceptions.StoarageOutOfColorException;
import com.vehicles.repository.ColorMongoRepository;
import com.vehicles.repository.LastUpdateMongoRepository;
import com.vehicles.repository.OrderMongoRepository;
import com.vehicles.repository.interfaces.ColorRepository;
import com.vehicles.repository.interfaces.OrderRepository;
import com.vehicles.service.interfaces.ColorService;
import com.vehicles.service.interfaces.LastUpdateService;
import com.vehicles.service.interfaces.OrderService;
import java.util.List;
import org.junit.After;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author kavan
 */
public class SimpleOrderServiceIT {

    OrderService orderService;
    OrderRepository orderRepo;
    ColorService colorService;
    ColorRepository colorRepo;
    LastUpdateService updateService;
    private final String dbUpdateKey = "test_db_updateKey";

    /**
     * Here I Use a specific TestDB and running these tests does not affect the
     * actual DB data
     */
    public SimpleOrderServiceIT() {
        orderRepo = new OrderMongoRepository(SpringMongoTestConfig.class);
        colorRepo = new ColorMongoRepository(SpringMongoTestConfig.class);
        updateService = new SimpleLastUpdateService(new LastUpdateMongoRepository());

        colorService = new SimpleColorService(colorRepo, updateService);
        colorService.setUpdateKey(dbUpdateKey);
        orderService = new SimpleOrderService(orderRepo, colorService,updateService);
        orderService.setUpdateKey(dbUpdateKey);
    }

    /**
     * I make sure TestDB is empty before each test
     */
    @Before
    public void setUp() {
        List<Color> cols = colorRepo.findAll();
        for (Color cl : cols) {
            colorRepo.remove(cl);
        }
        List<Order> orders = orderRepo.findAll();
        for (Order or : orders) {
            orderRepo.remove(or);
        }
    }

    @After
    public void tearDown() {
    }

    /**
     *
     */
    @Test
    public void testSave_whenVehicleIsNull() {
        Order order = new Order();
        try {
            orderService.save(order);
            fail("Order's vehicle is Null and Save operation should throw an exception in this stage but it continued!");
        } catch (NullPointerException e) {
            assertTrue(true);// everything is ok
        } catch (Exception e) {
            fail("Only NullPointerException is acceptable"
                    + " in this stage but operation throwed following error " + e.getMessage());
        }

    }

    @Test
    public void testSave_whenVehicleColorIsNull() {
        Order order = new Order();
        Vehicle vehicle = new Car(null, 4, 1);
        order.setVehicle(vehicle);
        try {
            orderService.save(order);
            fail("Order's vehicle's Color property is Null and Save operation should throw an exception in this stage but it continued!");
        } catch (NullPointerException e) {
            assertTrue(true);// everything is ok

        } catch (Exception e) {
            fail("Only NullPointerException is acceptable"
                    + " in this stage but operation throwed following error " + e.getMessage());
        }
    }

    @Test
    public void testSave_whenStoarageIsOutOfColor() {
        colorService.refillMissedColors();
        Order order = new Order();
        Vehicle vehicle = new Car(new Color(ColorName.BLUE), 4, 1);
        order.setVehicle(vehicle);
        colorService.popColor(ColorName.BLUE);
        try {
            orderService.save(order);
            fail("Order's vehicle's Color property is Null and Save operation should throw an exception in this stage but it continued!");
        } catch (StoarageOutOfColorException ex) {
            assertTrue(true);// everything is ok

        } catch (Exception e) {
            fail("Only StoarageOutOfColorException is acceptable"
                    + " in this stage but operation throwed following error " + e.getMessage());
        }
    }

    @Test
    public void testSave_whenCorrectOrderDataAndStoarageFullOfColors() {
        colorService.refillMissedColors();
        Order order = new Order();
        Vehicle vehicle = new Car(new Color(ColorName.BLUE), 4, 1);
        order.setVehicle(vehicle);
        try {
            orderService.save(order);
        } catch (Exception e) {
            fail("no Exception should be happend in this stage, " + e.getMessage());
        }
        assertNotNull("Order is not saved properly and it's null!", orderRepo.findByVehicle(vehicle));
        assertFalse("The order color must be empty after save operation", colorService.isColorExists(ColorName.BLUE));
    }

    /**
     *
     */
    @Test
    public void testRemove() {
        colorService.refillMissedColors();
        Order order = new Order();
        Vehicle vehicle = new Car(new Color(ColorName.BLUE), 4, 1);
        order.setVehicle(vehicle);
        try {
            orderService.save(order);
        } catch (Exception e) {
            fail("no Exception should be happend in this stage, " + e.getMessage());
        }
        assertNotNull("Order is not saved properly and it's null!", orderRepo.findByVehicle(vehicle));
        assertFalse("The order color must be empty after save operation", colorService.isColorExists(ColorName.BLUE));
        orderService.remove(order);
        assertNull("Order must be null after remove!", orderRepo.findOneByVehicle(vehicle));
    }

    /**
     *
     */
    @Test
    public void testFindAll() {
        colorService.refillMissedColors();
        int size = orderService.findAll().size();
        assertTrue("Size of test DB should be 0 in this stage", size == 0);
        for (ColorName cn : ColorName.values()) {
            Order order = new Order();
            Vehicle vehicle = new Car(new Color(cn), 4, 1);
            order.setVehicle(vehicle);
            try {
                orderService.save(order);
            } catch (Exception e) {
                fail("save operation failed in the loop: error = " + e.getMessage());
            }
        }
        size = orderService.findAll().size();
        assertTrue("The Orders Size now should be "
                + ColorName.values().length
                + " but it was " + size, size == ColorName.values().length);
    }

    /**
     *
     */
    @Test
    public void testFindOneByVehicle() {
        colorService.refillMissedColors();
        Order order = new Order();
        Vehicle vehicle = new Car(new Color(ColorName.BLUE), 4, 1);
        order.setVehicle(vehicle);
        try {
            orderService.save(order);
        } catch (Exception e) {
            fail("no Exception should be happend in this stage, " + e.getMessage());
        }
        assertNotNull("service returned Null! ", orderService.findOneByVehicle(vehicle));
    }

    @Test
    public void testFindOneByVehicle_whenVehicleIsNull() {

        assertNull("service should return Null! ", orderService.findOneByVehicle(null));
    }

    /**
     *
     */
    @Test
    public void testFindByVehicle() {
        Order order = new Order();
        Order order2 = new Order();
        Order order3 = new Order();
        Vehicle vehicle = new Car(new Color(ColorName.BLUE), 4, 1);
        order.setVehicle(vehicle);
        order2.setVehicle(vehicle);
        order3.setVehicle(vehicle);
        try {
            colorService.refillMissedColors();
            orderService.save(order);
            colorService.refillMissedColors();
            orderService.save(order2);
            colorService.refillMissedColors();
            orderService.save(order3);
        } catch (Exception e) {
            fail("No Exception should be happend in this stage, " + e.getMessage());
        }
        int size = orderService.findByVehicle(vehicle).size();
        assertTrue("the size of returned items should be 3 but it was" + size, size == 3);
    }

    @Test
    public void testFindByVehicle_whenVehicleIsNull() {
        int size = orderService.findByVehicle(null).size();
        assertTrue("the size of returned items should be 0 but it was" + size, size == 0);
    }

}
