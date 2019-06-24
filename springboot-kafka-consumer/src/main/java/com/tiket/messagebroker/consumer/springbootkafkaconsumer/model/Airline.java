package com.tiket.messagebroker.consumer.springbootkafkaconsumer.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Airline{
	private int id;
	private String code;
	private String name;
	private String status;
	
	public Airline(int id, String code, String name, String status) {
		this.id = id;
		this.code = code;
		this.name = name;
		this.status = status;
	}
	


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Airline() {

	}

	@Override
	public String toString() {
		return "AirlineConsumer [id=" + id + ", code=" + code + ", name=" + name + ", status=" + status + "]";
	}

	
	
	
	
	

}
