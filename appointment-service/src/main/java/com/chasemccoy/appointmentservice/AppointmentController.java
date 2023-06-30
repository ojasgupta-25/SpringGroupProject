package com.chasemccoy.appointmentservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppointmentController {

	/*
	ListAppts
	CreateAppt
	UpdateAppt
	GetAppt
	DeleteAppt
	 */
	
	@Autowired
	private AppointmentRepository repository;

	@GetMapping("/list")
	public List<Appointment> listAppts() {
		return repository.findAll();
	}
	
	@PostMapping("/create")
	public void createAppt(@RequestBody Appointment appointment) {
		repository.save(appointment);
	}
	
	@PutMapping("/update")
	public void updateAppt(@RequestBody Appointment appointment) {
		repository.save(appointment);
	}
	
	@GetMapping("/getByName/{name}")
	public Appointment getAppt(@PathVariable String name) {
		List<Appointment> appointments = repository.findAll();
		for (Appointment appointment : appointments) {
			if (appointment.getApptName().equals(name)) {
				return appointment;
			}
		}
		throw new RuntimeException();
	}
	
	@DeleteMapping("/deleteByName/{name}")
	public void deleteAppt(@PathVariable String name) {
		List<Appointment> appointments = repository.findAll();
		for (Appointment appointment : appointments) {
			if (appointment.getApptName().equals(name)) {
				repository.delete(appointment);
			}
		}
		throw new RuntimeException();
	}
	
}
