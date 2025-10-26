package com.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.user.entity.User;
import com.user.repository.UserRepository;
import com.user.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	boolean status;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserService userSerive;

	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody User user) {
		status = userSerive.save(user);
		if (status)
			return ResponseEntity.ok("User Register succussfully");
		else {
			return ResponseEntity.ok("User failed to Register");
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<User> getUser(@PathVariable Long id) {
		User user = userSerive.findById(id);
		if (user != null)
			return ResponseEntity.ok(user);
		else
			return ResponseEntity.notFound().build();
	}

	@GetMapping
	public ResponseEntity<User> getUserbyMail(@RequestParam String email) {
		User user = userSerive.findByMail(email);
		if (user != null)
			return ResponseEntity.ok(user);
		else
			return ResponseEntity.notFound().build();
	}

}
