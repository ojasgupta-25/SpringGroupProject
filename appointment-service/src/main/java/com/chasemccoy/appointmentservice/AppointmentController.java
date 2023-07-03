package com.chasemccoy.appointmentservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<List<Appointment>> listAppts() {
		return ResponseEntity.ok(repository.findAll());
	}
	
	@PostMapping("/create")
	public ResponseEntity<Appointment> createAppt(@RequestBody Appointment appointment) {
		return ResponseEntity.ok(repository.save(appointment));
	}
	
	@PutMapping("/update")
	public ResponseEntity<Appointment> updateAppt(@RequestBody Appointment appointment) {
		return ResponseEntity.ok(repository.save(appointment));
	}
	
	@GetMapping("/getByName/{name}")
	public ResponseEntity<Appointment> getAppt(@PathVariable String name) {
		List<Appointment> appointments = repository.findAll();
		for (Appointment appointment : appointments) {
			if (appointment.getApptName().equals(name)) {
				return ResponseEntity.ok(appointment);
			}
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	@DeleteMapping("/deleteByName/{name}")
	public ResponseEntity<Appointment> deleteAppt(@PathVariable String name) {
		List<Appointment> appointments = repository.findAll();
		for (Appointment appointment : appointments) {
			if (appointment.getApptName().equals(name)) {
				repository.delete(appointment);
				return ResponseEntity.ok(appointment);
			}
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
}
