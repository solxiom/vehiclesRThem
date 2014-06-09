

package com.vehicles.domain.entities;

import com.vehicles.domain.enums.ColorName;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author kavan
 */
@Document
public class Color {
    @Id
    private ColorName name;

    public Color(ColorName name) {
        this.name = name;
    }

    public ColorName getName() {
        return name;
    }

    public void setName(ColorName name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Color{" + "name=" + name + '}';
    }   
}
