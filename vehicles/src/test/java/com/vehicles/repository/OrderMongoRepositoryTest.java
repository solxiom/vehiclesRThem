/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vehicles.repository;

import com.vehicles.config.SpringMongoTestConfig;
import com.vehicles.domain.entities.Ferrari;
import com.vehicles.domain.entities.Order;
import com.vehicles.domain.interfaces.Vehicle;
import com.vehicles.repository.interfaces.OrderRepository;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author kavan
 */
public class OrderMongoRepositoryTest {
    OrderRepository repo;
    /**
     * Here I Use a specific TestDB and running these tests does not affect the
     * actual DB data
     */
    public OrderMongoRepositoryTest() {
        repo = new OrderMongoRepository(SpringMongoTestConfig.class);
    }

    /**
     * I make sure TestDB is empty before each test
     */
    @Before
    public void setUp() {
        List<Order> orders = repo.findAll();
        for (Order or : orders) {
            repo.remove(or);
        }
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testSave() {
        Order order = new Order();
        assertNull("Order Object should be null before saving", repo.findOneByField("id", order.getId()));
        Vehicle vehicle = new Ferrari();
        order.setVehicle(vehicle);
        repo.save(order);
        assertNotNull("Order Object should not be null after saving", repo.findOneByField("id", order.getId()));
    }

    @Test
    public void testRemove() {
        Order order = new Order();
        assertNull("Order Object should be null before saving", repo.findOneByField("id", order.getId()));
        repo.save(order);
        assertNotNull("Order Object should not be null after saving", repo.findOneByField("id", order.getId()));
        repo.remove(order);
        assertNull("Order should be null after removing", repo.findOneByField("id", order.getId()));

    }

    @Test
    public void testFindAll() {
        int size = repo.findAll().size(), upTo = 10;
        assertTrue("Size of test DB should be 0 in this stage", size == 0);
        for (int i = 0; i < upTo; i++) {
            repo.save(new Order());
        }
        size = repo.findAll().size();
        assertTrue("DB size now should be equal to " + upTo
                + " but it's " + size, size == upTo);
    }

    @Test
    public void testFindOneByVehicle() {
        Order order = new Order();
        Vehicle vehicle = new Ferrari();
        order.setVehicle(vehicle);
        assertNull("Order Object should be null before saving", repo.findOneByVehicle(vehicle));
        repo.save(order);
        assertNotNull("Order object should not be null in this stage " + vehicle.getId(), repo.findOneByVehicle(vehicle));
    }
    @Test
    public void testFindByVehicle() {
        Order order = new Order();
        Order order2 = new Order();
        Vehicle vehicle = new Ferrari();
        order.setVehicle(vehicle);
        order2.setVehicle(vehicle);
        assertTrue("List<Order> size should be 0 before saving", repo.findByVehicle(vehicle).size() == 0);
        repo.save(order);
        repo.save(order2);
        assertTrue("result size should be 2 at this stage, but it was " + repo.findByVehicle(vehicle).size(), repo.findByVehicle(vehicle).size() == 2);
    }
}
