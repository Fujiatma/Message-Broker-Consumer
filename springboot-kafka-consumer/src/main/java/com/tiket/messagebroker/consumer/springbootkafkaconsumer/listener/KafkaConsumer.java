package com.tiket.messagebroker.consumer.springbootkafkaconsumer.listener;


import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tiket.messagebroker.consumer.springbootkafkaconsumer.model.Airline;

//@Service
@RestController  
public class KafkaConsumer {
	
//	List<String> messages = new ArrayList<>();
	
	Airline airlineFromTopic = null;
	
//	@GetMapping("/stringMessage")
//	public List<String> consumeMsg(){
//		return messages;
//	}
//	
//	@KafkaListener(topics = "Airline_Topic_Example", groupId = "group_id", containerFactory = "kafkaListenerContainerFactory")
//	public List<String> getMessageTopic(String data) {
//		messages.add(data);
//		System.out.println("Consumed message: "+ messages);
//		return messages;	
//	}
	
	
	@GetMapping("/jsonMessage")
	public Airline consumeJsonMsg(){
		return airlineFromTopic;
	}
	
	@KafkaListener(topics = "Airline_Topic_Example", groupId = "group_json", containerFactory = "airlineConcurrentKafkaListenerContainerFactory")
	public Airline getJsonMessageTopic(Airline airline) {
		airlineFromTopic = airline;
		System.out.println("Consumed Json message : "+ airlineFromTopic);
		return airlineFromTopic;
		
	}
	
	
}
