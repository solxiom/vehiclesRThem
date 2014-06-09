/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vehicles.repository;

import com.vehicles.domain.enums.LastUpdate;
import com.vehicles.repository.interfaces.LastUpdateRepository;

/**
 *
 * @author kavan
 */
public class LastUpdateMongoRepository extends
        GenericMongoRepository<LastUpdate> implements LastUpdateRepository {
    
}
