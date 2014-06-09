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
public class BadCommandException extends Exception{

    public BadCommandException() {
        super("Command Not Found!");
    }
    public BadCommandException(String message) {
        super("Command Not Found!" + message);
    }
    
}
