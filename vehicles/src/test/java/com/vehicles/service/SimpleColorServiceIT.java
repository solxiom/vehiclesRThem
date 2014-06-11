/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vehicles.service;

import com.vehicles.config.SpringMongoTestConfig;
import com.vehicles.domain.entities.Color;
import com.vehicles.domain.enums.ColorName;
import com.vehicles.repository.ColorMongoRepository;
import com.vehicles.repository.LastUpdateMongoRepository;
import com.vehicles.repository.interfaces.ColorRepository;
import com.vehicles.service.interfaces.ColorService;
import com.vehicles.service.interfaces.LastUpdateService;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author kavan
 */
public class SimpleColorServiceIT {

    ColorRepository repo;
    ColorService service;
    LastUpdateService updateService;
    private final String dbUpdateKey = "test_db_updateKey";

    /**
     * Here I Use a specific TestDB and running these tests does not affect the
     * actual DB data
     */
    public SimpleColorServiceIT() {
        repo = new ColorMongoRepository(SpringMongoTestConfig.class);
        updateService = new SimpleLastUpdateService(new LastUpdateMongoRepository());
        service = new SimpleColorService(repo,updateService);
        service.setUpdateKey(dbUpdateKey);
    }

    /**
     * I make sure TestDB is empty before each test
     */
    @Before
    public void setUp() {
        List<Color> cols = repo.findAll();
        for (Color cl : cols) {
            repo.remove(cl);
        }
    }

    @After
    public void tearDown() {
    }

    /**
     *
     */
    @Test
    public void testSave() {
        Color color;
        color = repo.findOneByField("name", ColorName.BLUE.toString());
        assertNull("Color Object should be null before saving", color);
        color = new Color(ColorName.BLUE);
        try {
            service.save(color);
        } catch (Exception e) {
            fail("Exception in testSave method " + e.getMessage());
        }
        color = repo.findOneByField("name", ColorName.BLUE.toString());
        assertNotNull("Color Object should not be null after saving", color);
        color = new Color(ColorName.BLUE);
        try {
            service.save(color);
        } catch (Exception e) {
            fail("Exception in testSave method " + e.getMessage());
        }
        assertTrue("same color should not be added two time in database", repo.findByField("name", ColorName.BLUE.toString()).size() == 1);
    }

    /**
     *
     */
    @Test
    public void testRemove() {
        Color color;
        color = repo.findOneByField("name", ColorName.BLUE.toString());
        assertNull("Color Object should be null before saving", color);
        color = new Color(ColorName.BLUE);
        try {
            service.save(color);
        } catch (Exception e) {
            fail("Exception in testSave method " + e.getMessage());
        }
        color = repo.findOneByField("name", ColorName.BLUE.toString());
        assertNotNull("Color Object should not be null after saving", color);
        service.remove(color);
        assertNull("Color Object should be null after remove operation ", repo.findOneByField("name", ColorName.BLUE.toString()));

    }
    /**
     *
     */
    @Test
    public void testFindAll() {
        int size = service.findAll().size();
        assertTrue("Size of test DB should be 0 in this stage", size == 0);
        for (ColorName cn : ColorName.values()) {
            repo.save(new Color(cn));
        }
        size = service.findAll().size();
        assertTrue("DB size now should be equal to " + ColorName.values().length
                + " but it's " + size, size == ColorName.values().length);
    }

    /**
     *
     */
    @Test
    public void testFindOneByField() {
        Color color;
        color = repo.findOneByField("name", ColorName.BLUE.toString());
        assertNull("Color Object should be null before saving", color);
        repo.save(new Color(ColorName.BLUE));
        color = service.findOneByField("name", ColorName.BLUE.toString());
        assertNotNull("Color object should not be null in this stage", color);
    }

    /**
     * Test of popColor method, of class SimpleColorService.
     */
    @Test
    public void testPopColor() {
        Color color;
        color = repo.findOneByField("name", ColorName.BLUE.toString());
        assertNull("Color Object should be null before saving", color);
        color = new Color(ColorName.BLUE);
        repo.save(color);
        color = repo.findOneByField("name", ColorName.BLUE.toString());
        assertNotNull("Color Object should not be null after saving", color);
        Color popColor = service.popColor(ColorName.BLUE);
        assertNull("Color Object should be removed from DB after popColor operation", repo.findOneByField("name", ColorName.BLUE.toString()));
        assertTrue("popColor should return correct color object", popColor.getName() == ColorName.BLUE);
    }

    /**
     * Test of isColorExists method, of class SimpleColorService.
     */
    @Test
    public void testIsColorExists() {
        Color color;
        color = repo.findOneByField("name", ColorName.BLUE.toString());
        assertNull("Color Object should be null before saving", color);
        assertFalse("operation must return false in this stage", service.isColorExists(ColorName.BLUE));
        color = new Color(ColorName.BLUE);
        repo.save(color);
        assertTrue("operation must return true in this stage", service.isColorExists(ColorName.BLUE));
        repo.remove(color);
        assertFalse("operation must return false in this stage", service.isColorExists(ColorName.BLUE));
    }

    /**
     * Test of refillMissedColors method, of class SimpleColorService.
     */
    @Test
    public void testRefillMissedColors() {
        int size = repo.findAll().size();
        assertTrue("items size must be 0 in this stage", size == 0);
        service.refillMissedColors();
        size = repo.findAll().size();
        assertTrue("Colors size after refill must be "
                + ColorName.values().length + " but it was " + size, size == ColorName.values().length);
        service.refillMissedColors();
        service.refillMissedColors();
        service.refillMissedColors();
        service.refillMissedColors();
        assertTrue("Colors maximum size must be always "
                + ColorName.values().length + " but after multiple refill operation it become "
                + size, size == ColorName.values().length);
    }

}
