/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vehicles.config;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;

/**
 *
 * @author kavan
 */
@Configuration
public class SpringMongoTestConfig extends AbstractMongoConfiguration {

	@Override
	public String getDatabaseName() {
		return "vehiclesTestDB";
	}

	@Override
	@Bean
	public Mongo mongo() throws Exception {
		return new MongoClient("127.0.0.1");
	}
}
