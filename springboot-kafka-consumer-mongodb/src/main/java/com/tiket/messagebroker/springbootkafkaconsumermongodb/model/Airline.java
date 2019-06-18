package com.tiket.messagebroker.springbootkafkaconsumermongodb.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

@Document(collection = "Air_Fields")
public class Airline {
	@Id
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
		final StringBuffer sb = new StringBuffer("Airline{");
		sb.append("id='").append(id).append('\'');
		sb.append(", code='").append(code).append('\'');
		sb.append(", name='").append(name).append('\'');
		sb.append(", status='").append(status).append('\'');
		sb.append('}');
		return sb.toString();
	}
	
	
	
	
	
	
}