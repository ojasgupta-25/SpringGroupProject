package com.springDemo.Users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserDAO userDAO;

    @GetMapping
    public List<User> listUsers() {
        return userDAO.findAll();
    }
    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        return userDAO.findById(id).orElse(null);
    }
    @PostMapping
    public User createUser(@RequestBody User user) {
        return userDAO.save(user);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        User user = userDAO.findById(id).orElse(null);
        if (user != null) {
            user.setFirstName(updatedUser.getFirstName());
            user.setLastName(updatedUser.getLastName());
            user.setGender(updatedUser.getGender());
            user.setAge(updatedUser.getAge());
            user.setEmailAddresses(updatedUser.getEmailAddresses());
            user.setPhoneNumbers(updatedUser.getPhoneNumbers());
            return userDAO.save(user);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userDAO.deleteById(id);
    }
}
