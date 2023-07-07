package com.springDemo.managementservice;


import com.springDemo.Users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

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
}
