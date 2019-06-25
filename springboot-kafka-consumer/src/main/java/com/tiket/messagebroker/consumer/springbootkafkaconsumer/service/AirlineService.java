package com.tiket.messagebroker.consumer.springbootkafkaconsumer.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiket.messagebroker.consumer.springbootkafkaconsumer.model.Airline;
import com.tiket.messagebroker.consumer.springbootkafkaconsumer.model.AirlineUpdatePayLoad;
import com.tiket.messagebroker.consumer.springbootkafkaconsumer.repository.AirlineDao;

@Service
public class AirlineService {
	@Autowired
	private AirlineDao airlineDao;
	
	
	private List<Airline> airlineFromTopic = new ArrayList<Airline>();
	
	public Collection<Airline> getAirlines(){
		   return airlineDao.getAirlines();
	   }

	public Airline createAirline(List<Airline> airlineFromTopic) {
		this.airlineFromTopic = airlineFromTopic;
		return airlineDao.createAirline(airlineFromTopic);
		
	}

	public Optional<Airline> getAirlineById(int id) {
		return airlineDao.getAirlineById(id);
	}

	public Optional<Airline> deleteAirlineById(int id) {
		return airlineDao.deleteAirlineById(id);
	}

	public Optional<Airline> updateAirlineById(int id, AirlineUpdatePayLoad airlineUpdatePayLoad) {
		return airlineDao.updateAirlineById(id, airlineUpdatePayLoad);
	}
}
