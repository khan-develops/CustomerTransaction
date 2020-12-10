package com.customer.relationship.controller;

import com.customer.relationship.Repository.TransactionRepository;
import com.customer.relationship.Repository.UserRepository;
import com.customer.relationship.exception.RecordNotFoundException;
import com.customer.relationship.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class userController {
    @Autowired
    public UserRepository userRepository;

    @GetMapping("/users")
    public String getUsers(Model model) {
        List<Users> userList = new ArrayList<>();
        model.addAttribute("users", userList);
        return "userList";
    }
    @PostMapping("/users")
    public Users addUser(@RequestBody Users users) {
        return userRepository.save(users);
    }
    @GetMapping("/users/{id}")
    public ResponseEntity<Users> getUser(@PathVariable(value = "id") long id) throws RecordNotFoundException {
        Users users = userRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("ID: " + id + " not found"));
        return ResponseEntity.ok().body(users);
    }
    @DeleteMapping("/users/{id}")
    public Map<String, String> deleteUser(@PathVariable(value = "id") long id) throws RecordNotFoundException {
        Users users = userRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("ID: " + id + " not found"));
    }
}