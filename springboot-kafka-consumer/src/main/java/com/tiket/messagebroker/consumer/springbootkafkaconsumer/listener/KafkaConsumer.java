package com.tiket.messagebroker.consumer.springbootkafkaconsumer.listener;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tiket.messagebroker.consumer.springbootkafkaconsumer.model.Airline;
import com.tiket.messagebroker.consumer.springbootkafkaconsumer.service.AirlineService;

@Service
@RestController  
@Component
public class KafkaConsumer {
	
	@Autowired
	private AirlineService airlineService;
	
	//Airline airlineFromTopic = null;
	List<Airline> airlineFromTopic = new ArrayList<>();
	
	
	@GetMapping("/jsonMessage")
//	public Airline consumeJsonMsg(){
//		return airlineFromTopic;
//	}
	public List<Airline> consumeMsg(){
		return airlineFromTopic;
	}
	
	
	@KafkaListener(topics = "Airline_Topic", groupId = "group_json", containerFactory = "airlineConcurrentKafkaListenerContainerFactory")
	public List<Airline> getJsonMessageTopic(Airline airline) {
		//airlineFromTopic = airline;
		airlineFromTopic.add(airline);
		System.out.println("Consumed Json message : "+ airlineFromTopic);
		return airlineFromTopic;
		
	}
	
	
	@PostMapping("/addAirline")
	public String saveAirline(List<Airline> airlineFromTopic) {
		try {
			airlineService.createAirline(airlineFromTopic);
        } catch (Exception e) {
        	System.out.println(airlineFromTopic.get(0)+" Has Been Exists");
            e.getMessage();
        }
		return "Added airline with id : "+ airlineFromTopic.get(0);
	}
	
	
}
