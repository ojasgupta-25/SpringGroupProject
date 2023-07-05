package com.chasemccoy.appointmentservice;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import jakarta.transaction.Transactional;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureTestDatabase
@Transactional
class AppointmentControllerTest {
	
	@Autowired
	private AppointmentRepository appointmentRepository;
	
	@Autowired
	private AppointmentController appointmentController;
	
	@Test
	void testListAppts() {
		
		Appointment a1 = new Appointment("name", "type", "description", LocalDateTime.now(), LocalDateTime.now(), "metadata", 0L);
		Appointment a2 = new Appointment("name2", "type", "description", LocalDateTime.now(), LocalDateTime.now(), "metadata", 0L);
		appointmentRepository.save(a1);
		appointmentRepository.save(a2);
		List<Appointment> appointments = appointmentController.listAppts().getBody();
		
		assertEquals(2, appointments.size());
	}
	
	@Test
	void testListApptsByUser() {
		
		Appointment a1 = new Appointment("name", "type", "description", LocalDateTime.now(), LocalDateTime.now(), "metadata", 0L);
		Appointment a2 = new Appointment("name2", "type", "description", LocalDateTime.now(), LocalDateTime.now(), "metadata", 1L);
		appointmentRepository.save(a1);
		appointmentRepository.save(a2);
		List<Appointment> appointments = appointmentController.listApptsByUser(1L).getBody();
		assertEquals(1, appointments.size());
	}
	
	@Test
	void testCreateAppt() {
		Appointment a_put = new Appointment("name", "type", "description", LocalDateTime.now(), LocalDateTime.now(), "metadata", 0L);
		appointmentController.createAppt(a_put);
		
		assertEquals(1, appointmentRepository.count());
		assertEquals(a_put.getApptName(), appointmentRepository.findAll().get(0).getApptName());
	}
	
	@Test
	void testUpdateAppt() {
		Appointment a_original = new Appointment("name", "type", "description", LocalDateTime.now(), LocalDateTime.now(), "metadata", 0L);
		Appointment a_updated = new Appointment("name", "updated_type", "description", LocalDateTime.now(), LocalDateTime.now(), "metadata", 0L);
		appointmentRepository.save(a_original);
		appointmentController.updateAppt(a_updated);
		
		assertEquals(1, appointmentRepository.count());
		assertEquals(a_updated.getApptType(), appointmentRepository.findAll().get(0).getApptType());
	}
	
	@Test
	void testGetApptByName() {
		Appointment a_put = new Appointment("name", "type", "description", LocalDateTime.now(), LocalDateTime.now(), "metadata", 0L);
		appointmentRepository.save(a_put);
		Appointment a_get = appointmentController.getAppt("name").getBody();
		HttpStatusCode status = appointmentController.getAppt("not_found").getStatusCode();
		
		assertEquals(a_put.getApptName(), a_get.getApptName());
		assertEquals(status, HttpStatus.NOT_FOUND);
	}
	
	@Test
	void testDeleteApptByName() {
		Appointment a_put = new Appointment("name", "type", "description", LocalDateTime.now(), LocalDateTime.now(), "metadata", 0L);
		appointmentRepository.save(a_put);
		Appointment a_delete = appointmentController.deleteAppt("name").getBody();
		HttpStatusCode status = appointmentController.getAppt("not_found").getStatusCode();
		
		assertEquals(a_put.getApptName(), a_delete.getApptName());	
		assertEquals(status, HttpStatus.NOT_FOUND);
	}

}