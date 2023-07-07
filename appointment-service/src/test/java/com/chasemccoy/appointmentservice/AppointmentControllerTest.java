package com.chasemccoy.appointmentservice;

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

import static org.junit.jupiter.api.Assertions.*;


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
		assertEquals(a_put.getId(), appointmentRepository.findAll().get(0).getId());
	}
	
	@Test
	void testUpdateAppt() {
		Appointment a_original = new Appointment("original", "type", "description", LocalDateTime.now(), LocalDateTime.now(), "metadata", 0L);
		Appointment a_updated = new Appointment("updated", "type", "description", LocalDateTime.now(), LocalDateTime.now(), "metadata", 0L);
		appointmentRepository.save(a_original);
		appointmentController.updateAppt(a_original.getId(), a_updated);
		
		assertEquals(1, appointmentRepository.count());
		assertEquals("updated", appointmentRepository.findAll().get(0).getApptName());
	}
	
	@Test
	void testGetAppt() {
		Appointment a_put = new Appointment("name", "type", "description", LocalDateTime.now(), LocalDateTime.now(), "metadata", 0L);
		appointmentRepository.save(a_put);
		Appointment a_get = appointmentController.getAppt(a_put.getId()).getBody();
		HttpStatusCode status = appointmentController.getAppt(0L).getStatusCode();
		
		assertEquals(a_put.getId(), a_get.getId());
		assertEquals(status, HttpStatus.NOT_FOUND);
	}
	
	@Test
	void testDeleteAppt() {
		Appointment a_put = new Appointment("name", "type", "description", LocalDateTime.now(), LocalDateTime.now(), "metadata", 0L);
		appointmentRepository.save(a_put);
		Appointment a_delete = appointmentController.deleteAppt(a_put.getId()).getBody();
		HttpStatusCode status = appointmentController.getAppt(0L).getStatusCode();
		
		assertEquals(a_put.getApptName(), a_delete.getApptName());	
		assertEquals(status, HttpStatus.NOT_FOUND);
	}

	@Test
	public void testAppointment() {
		Appointment appointment = new Appointment("name", "type", "description", LocalDateTime.now(), LocalDateTime.now(), "metadata", 0L);
		assertNotNull(appointment);
		assertEquals("name", appointment.getApptName());
		assertEquals("type", appointment.getApptType());
		assertEquals("description", appointment.getDescription());
		assertEquals(LocalDateTime.now().getMinute(), appointment.getStartTime().getMinute());
		assertEquals(LocalDateTime.now().getMinute(), appointment.getEndTime().getMinute());
		assertEquals("metadata", appointment.getMetadata());
		assertEquals(0L, appointment.getUserId());
	}

	@Test
	public void testSetApptName() {
		Appointment appointment = new Appointment();
		appointment.setApptName("My Appointment");
		assertEquals("My Appointment", appointment.getApptName());
	}

	@Test
	public void testSetApptType() {
		Appointment appointment = new Appointment();
		appointment.setApptType("Meeting");
		assertEquals("Meeting", appointment.getApptType());
	}

	@Test
	public void testSetDescription() {
		Appointment appointment = new Appointment();
		appointment.setDescription("This is a meeting.");
		assertEquals("This is a meeting.", appointment.getDescription());
	}

	@Test
	public void testSetStartTime() {
		Appointment appointment = new Appointment();
		appointment.setStartTime(LocalDateTime.now());
		assertEquals(LocalDateTime.now().getMinute(), appointment.getStartTime().getMinute());
	}

	@Test
	public void testSetEndTime() {
		Appointment appointment = new Appointment();
		appointment.setEndTime(LocalDateTime.now().plusHours(1));
		assertEquals(LocalDateTime.now().plusHours(1).getMinute(), appointment.getEndTime().getMinute());
	}

	@Test
	public void testSetMetadata() {
		Appointment appointment = new Appointment();
		appointment.setMetadata("Some metadata");
		assertEquals("Some metadata", appointment.getMetadata());
	}

	@Test
	public void testSetUserId() {
		Appointment appointment = new Appointment();
		appointment.setUserId(1L);
		assertEquals(1L, appointment.getUserId());
	}

}