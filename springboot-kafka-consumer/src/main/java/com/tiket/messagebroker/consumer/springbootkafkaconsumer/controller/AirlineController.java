package com.tiket.messagebroker.consumer.springbootkafkaconsumer.controller;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tiket.messagebroker.consumer.springbootkafkaconsumer.model.Airline;
import com.tiket.messagebroker.consumer.springbootkafkaconsumer.model.AirlineUpdatePayLoad;
import com.tiket.messagebroker.consumer.springbootkafkaconsumer.service.AirlineService;

@Component
@RestController
public class AirlineController {
	@Autowired
	private AirlineService airlineService;
	
	//send Object
	@Autowired
	private KafkaTemplate<String, Airline> kafkaTemplate;
	
	private static final String TOPIC = "Airline_Topic";
	
	
	@GetMapping("/findAllAirlines")
	public Collection<Airline> getAirlines(){
		return airlineService.getAirlines();
	}
	
	@GetMapping("/findById/{id}") 
	public Optional<Airline> getAirlineById(@PathVariable("id") int id){
		return airlineService.getAirlineById(id);
	}
	
	@DeleteMapping("/deleteById/{id}") 
	public Optional<Airline> deleteAirlineById(@PathVariable("id") int id){
		return airlineService.deleteAirlineById(id);
	}
	
	
	@PutMapping("/updateById/{id}") 
	public Optional<Airline> updateAirlineById(@PathVariable("id") int id, @RequestBody AirlineUpdatePayLoad airlineUpdatePayLoad){
		return airlineService.updateAirlineById(id, airlineUpdatePayLoad);
	}
}
