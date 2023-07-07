package com.chasemccoy.appointmentservice;

import java.util.ArrayList;
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
	
	@Autowired
	private AppointmentRepository repository;

	@GetMapping("/appointments")
	public ResponseEntity<List<Appointment>> listAppts() {
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/appointments/user/{userid}")
	public ResponseEntity<List<Appointment>> listApptsByUser(@PathVariable Long userid) {
		
		List<Appointment> userAppts = new ArrayList<Appointment>();
		for (Appointment appointment : repository.findAll()) {
			if (appointment.getUserId() == userid) {
				userAppts.add(appointment);
			}
		}
		return ResponseEntity.ok(userAppts);
	}
	
	@PostMapping("/appointments")
	public ResponseEntity<Appointment> createAppt(@RequestBody Appointment appointment) {
		return ResponseEntity.ok(repository.save(appointment));
	}
	
	@PutMapping("/appointments/{id}")
	public ResponseEntity<Appointment> updateAppt(@PathVariable Long id, @RequestBody Appointment appointment) {
		appointment.setId(id);
		return ResponseEntity.ok(repository.save(appointment));
	}
	
	@GetMapping("/appointments/{id}")
	public ResponseEntity<Appointment> getAppt(@PathVariable Long id) {
		List<Appointment> appointments = repository.findAll();
		for (Appointment appointment : appointments) {
			if (appointment.getId().equals(id)) {
				return ResponseEntity.ok(appointment);
			}
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	@DeleteMapping("/appointments/{id}")
	public ResponseEntity<Appointment> deleteAppt(@PathVariable Long id) {
		List<Appointment> appointments = repository.findAll();
		for (Appointment appointment : appointments) {
			if (appointment.getId().equals(id)) {
				repository.delete(appointment);
				return ResponseEntity.ok(appointment);
			}
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
}
