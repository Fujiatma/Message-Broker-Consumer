package com.tiket.messagebroker.consumer.springbootkafkaconsumer.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.tiket.messagebroker.consumer.springbootkafkaconsumer.model.Airline;



@Configuration
@EnableKafka
public class KafkaConfiguration {
	
//	Consume String Message
	@Bean
	public ConsumerFactory<String, String> consumerFactory(){
		Map<String, Object> config = new HashMap<>();
		config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		config.put(ConsumerConfig.GROUP_ID_CONFIG, "group_id");
		return new DefaultKafkaConsumerFactory<>(config);
	}
	
	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory(){
		ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory());
		return factory;
	}
	
	
//	Consume Json Message
	@Bean
	public ConsumerFactory<String, Airline> airlineConsumerFactory(){
		Map<String, Object> config = new HashMap<>();
		config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
		config.put(ConsumerConfig.GROUP_ID_CONFIG, "group_json");
		
		return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(), new JsonDeserializer<>(Airline.class));
	}
	
	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, Airline> airlineConcurrentKafkaListenerContainerFactory(){
		ConcurrentKafkaListenerContainerFactory<String, Airline> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(airlineConsumerFactory());
		return factory;
	}
	
}


  