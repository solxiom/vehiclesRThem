/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vehicles.service.interfaces;

import com.vehicles.domain.entities.Color;
import com.vehicles.domain.enums.ColorName;

/**
 *
 * @author kavan
 */
public interface ColorService extends GenericService<Color> {

    public Color popColor(ColorName colorName);
    
    public boolean isColorExists(ColorName colorName);

    public void refillMissedColors();
}
