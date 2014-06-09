/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vehicles.repository;

import com.vehicles.config.SpringMongoTestConfig;
import com.vehicles.domain.entities.Color;
import com.vehicles.domain.enums.ColorName;
import com.vehicles.repository.interfaces.ColorRepository;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author kavan
 */
public class ColorMongoRepositoryTest {

    ColorRepository repo;

    /**
     * Here I Use a specific TestDB and running these tests does not affect the
     * actual DB data
     */
    public ColorMongoRepositoryTest() {
        repo = new ColorMongoRepository(SpringMongoTestConfig.class);
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

    @Test
    public void testSave() {
        Color color;
        color = repo.findOneByField("name", ColorName.BLUE.toString());
        assertNull("Color Object should be null before saving", color);
        color = new Color(ColorName.BLUE);
        repo.save(color);
        color = repo.findOneByField("name", ColorName.BLUE.toString());
        assertNotNull("Color Object should not be null after saving", color);
    }

    @Test
    public void testRemove() {
        Color color;
        color = repo.findOneByField("name", ColorName.BLUE.toString());
        assertNull("Color Object should be null before saving", color);
        color = new Color(ColorName.BLUE);
        repo.save(color);
        color = repo.findOneByField("name", ColorName.BLUE.toString());
        assertNotNull("Color Object should not be null after saving", color);
        repo.remove(color);
        color = repo.findOneByField("name", ColorName.BLUE.toString());
        assertNull("Color Object should be null after removing", color);

    }

    @Test
    public void testFindAll() {
        int size = repo.findAll().size();
        assertTrue("Size of test DB should be 0 in this stage", size == 0);
        for (ColorName cn : ColorName.values()) {
            repo.save(new Color(cn));
        }
        size = repo.findAll().size();
        assertTrue("DB size now should be equal to " + ColorName.values().length
                + " but it's " + size, size == ColorName.values().length);

    }

    @Test
    public void testFindOneByField() {
        Color color;
        color = repo.findOneByField("name", ColorName.BLUE.toString());
        assertNull("Color Object should be null before saving", color);
        repo.save(new Color(ColorName.BLUE));
        color = repo.findOneByField("name", ColorName.BLUE.toString());
        assertNotNull("Color object should not be null in this stage",color);
    }
}
