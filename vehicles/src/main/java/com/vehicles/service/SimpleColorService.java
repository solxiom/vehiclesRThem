/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vehicles.service;

import com.vehicles.service.interfaces.ColorService;
import com.vehicles.domain.entities.Color;
import com.vehicles.domain.enums.ColorName;
import com.vehicles.domain.enums.LastUpdate;
import com.vehicles.repository.interfaces.ColorRepository;
import com.vehicles.service.interfaces.LastUpdateService;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author kavan
 */
@Service
public class SimpleColorService extends GenericServiceImpl<Color> implements ColorService {
    private LastUpdateService updateService;
//    private String updateKey;
    
    @Autowired
    public SimpleColorService(ColorRepository repository,
            LastUpdateService updateService ) {
        super(repository);
        this.updateService = updateService;
//        this.updateKey = updateKey;
    }    

    @Override
    public void save(Color color) throws Exception {
        if (repository.findOneByField("name", color.getName().toString()) == null) {
            super.save(color);
        }
    }

    @Override
    public Color popColor(ColorName colorName) {
        Color color = repository.findOneByField("name", colorName.toString());
        if (color != null) {
            repository.remove(color);
        }
        return color;
    }

    @Override
    public boolean isColorExists(ColorName colorName) {
        return !isColorEmpty(colorName);
    }

    @Override
    public void refillMissedColors() {
        boolean updated = false;
        for (ColorName cn : ColorName.values()) {
            if (isColorEmpty(cn)) {
                repository.save(new Color(cn));
                updated = true;
            }
        }
        if(updated){
            try{
                updateService.save(new LastUpdate(getUpdateKey(), new Date()));
            }catch(Exception e){
                
            }
        }
    }

    private boolean isColorEmpty(ColorName colorName) {
        Color color = repository.findOneByField("name", colorName.toString());
        if (color == null) {
            return true;
        }
        return false;
    }
}
