/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vehicles.exceptions;

/**
 *
 * @author kavan
 */
public class StoarageOutOfColorException extends Exception {

    public StoarageOutOfColorException() {
        super("Stoarage out of color Error");
    }

    public StoarageOutOfColorException(String message) {
        super("Stoarage out of color Exception" + message);
    }

}
