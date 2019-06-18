package com.tiket.messagebroker.springbootkafkaconsumermongodb.listener;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.tiket.messagebroker.springbootkafkaconsumermongodb.model.Airline;

@Service
public class KafkaConsumer {

//	@KafkaListener(topics = "Airline_Topic", groupId= "group_id")
//	public void consume(String message) {
//		System.out.println("Consumed message : " + message);
//	}
	
//	@KafkaListener(topics = "Airline_Topic_Example", groupId = "group_json",
//			containerFactory = "airlineKafkaListenerFactory")
//	public void consumeJson(Airline airline) {
//		System.out.println("Consumed JSON Message : "+airline);
//	}
}
