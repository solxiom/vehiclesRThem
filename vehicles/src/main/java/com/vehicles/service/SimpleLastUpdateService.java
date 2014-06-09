/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vehicles.service;

import com.vehicles.domain.enums.LastUpdate;
import com.vehicles.repository.interfaces.LastUpdateRepository;
import com.vehicles.service.interfaces.LastUpdateService;

/**
 *
 * @author kavan
 */
public class SimpleLastUpdateService extends GenericServiceImpl<LastUpdate> implements LastUpdateService{

    public SimpleLastUpdateService(LastUpdateRepository repository) {
        super(repository);
    }
    
}
