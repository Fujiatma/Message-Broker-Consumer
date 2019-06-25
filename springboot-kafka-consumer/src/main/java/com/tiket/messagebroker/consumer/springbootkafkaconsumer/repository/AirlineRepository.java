package com.tiket.messagebroker.consumer.springbootkafkaconsumer.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.tiket.messagebroker.consumer.springbootkafkaconsumer.model.Airline;

public interface AirlineRepository extends MongoRepository<Airline, Integer>{

}
