package com.springDemo.managementservice;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.chasemccoy.appointmentservice.Appointment;
import com.springDemo.Users.User;

@RestController
@RequestMapping("/management-service")
public class ManagementServiceController {

    private static RestTemplate restTemplate = new RestTemplate();


    @GetMapping("/users")
    public List<User> listUsers() {
        return restTemplate.getForObject("http://localhost:8100/users", List.class);
    }

    @GetMapping("/users/{id}")
    public User getUser(@PathVariable Long id) {
        return restTemplate.getForObject("http://localhost:8100/users/{id}", User.class, id);
    }

    @PostMapping("/users")
    public User createUser(@RequestBody User user) {
        return restTemplate.postForObject("http://localhost:8100/users", user, User.class);
    }

    @PutMapping("/users/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User updatedUser) throws URISyntaxException {
        URI uri = new URI("http://localhost:8100/users/"+id);

        // Create a HttpEntity object for the updated user
        HttpEntity<User> request = new HttpEntity<>(updatedUser);

        // Make the exchange request
        ResponseEntity<User> response = restTemplate.exchange(uri, HttpMethod.PUT, request, User.class);

        // Get the updated user from the response
        return response.getBody();
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable Long id) {
        restTemplate.delete("http://localhost:8100/users/{id}", id);
    }
    
    
    // Appointment service calls
	@GetMapping("/appointments")
	public ResponseEntity<List<Appointment>> listAppts() {
        return restTemplate.exchange("http://localhost:8000/appointments", HttpMethod.GET, null, new ParameterizedTypeReference<List<Appointment>>() {});
	}
	
	@GetMapping("/appointments/user/{userid}")
	public ResponseEntity<List<Appointment>> listApptsByUser(@PathVariable Long userid) {
        return restTemplate.exchange("http://localhost:8000/appointments" + "/user/" + String.valueOf(userid), HttpMethod.GET, null, new ParameterizedTypeReference<List<Appointment>>() {});
	}
	
	@PostMapping("/appointments")
	public ResponseEntity<Appointment> createAppt(@RequestBody Appointment appointment) {
        return restTemplate.exchange("http://localhost:8000/appointments", HttpMethod.POST, new HttpEntity<>(appointment), new ParameterizedTypeReference<Appointment>() {});
	}
	
	@PutMapping("/appointments/{id}")
	public ResponseEntity<Appointment> updateAppt(@PathVariable Long id, @RequestBody Appointment appointment) {
        return restTemplate.exchange("http://localhost:8000/appointments" + "/" + String.valueOf(id), HttpMethod.PUT, new HttpEntity<>(appointment), new ParameterizedTypeReference<Appointment>() {});
	}
	
	@GetMapping("/appointments/{id}")
	public ResponseEntity<Appointment> getAppt(@PathVariable Long id) {
        return restTemplate.exchange("http://localhost:8000/appointments" + "/" + String.valueOf(id), HttpMethod.GET, null, new ParameterizedTypeReference<Appointment>() {});
	}
	
	@DeleteMapping("/appointments/{id}")
	public ResponseEntity<Appointment> deleteAppt(@PathVariable Long id) {
        return restTemplate.exchange("http://localhost:8000/appointments" + "/" + String.valueOf(id), HttpMethod.DELETE, null, new ParameterizedTypeReference<Appointment>() {});
	}
    
}
