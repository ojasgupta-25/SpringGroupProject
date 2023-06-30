package com.chasemccoy.appointmentservice;

import java.time.LocalDateTime;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Appointment {
	
	@Id
	private String apptName;
	private String apptType;
	private String description;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime startTime;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime endTime;
	private String metadata;
	
	public Appointment() {
		super();
	}
	
	public Appointment(String apptName, String apptType, String description, LocalDateTime startTime, LocalDateTime endTime, String metadata) {
		super();
		this.apptName = apptName;
		this.apptType = apptType;
		this.description = description;
		this.startTime = startTime;
		this.endTime = endTime;
		this.metadata = metadata;
	}
	
	public String getApptName() {
		return apptName;
	}
	public void setApptName(String apptName) {
		this.apptName = apptName;
	}
	public String getApptType() {
		return apptType;
	}
	public void setApptType(String apptType) {
		this.apptType = apptType;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public LocalDateTime getStartTime() {
		return startTime;
	}
	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}
	public LocalDateTime getEndTime() {
		return endTime;
	}
	public void setEndTime(LocalDateTime endTime) {
		this.endTime = endTime;
	}
	public String getMetadata() {
		return metadata;
	}
	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}

}
