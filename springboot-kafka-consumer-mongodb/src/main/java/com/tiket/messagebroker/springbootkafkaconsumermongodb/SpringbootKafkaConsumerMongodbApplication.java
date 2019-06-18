package com.tiket.messagebroker.springbootkafkaconsumermongodb;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tiket.messagebroker.springbootkafkaconsumermongodb.model.Airline;

@SpringBootApplication
@RestController
public class SpringbootKafkaConsumerMongodbApplication {
	
	List<String> messages = new ArrayList<>();
	
	Airline airlineFromTopic = null;
	
	@GetMapping("/consumeStringMessage")
	public List<String> consumeMsg(){
		return messages;
	}
	
	@GetMapping("/consumeJsonMessage")
	public Airline consumeJsonMessage() {
		
		return airlineFromTopic;
	}
	
	@KafkaListener(groupId="group_id", topics="Airline_Topic_Example", containerFactory="kafkaListenerContainerFactory")
	public List<String> getMsgFromTopic(String data){
		messages.add(data);
		return messages;
	}
	
	
	@KafkaListener(groupId="group_json", topics="Airline_Topic_Example", containerFactory="airlineKafkaListenerContainerFactory")
	public Airline getJsonFromTopic(Airline airline){
		airlineFromTopic = airline;
		return airlineFromTopic;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(SpringbootKafkaConsumerMongodbApplication.class, args);
	}

}
