package com.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.entity.User;
import com.user.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public boolean save(User user) {
		return userRepository.save(user) != null;
	}

	public User findById(Long id) {
		return userRepository.findById(id).orElseThrow();
	}
	public User findByMail(String email) {
		return userRepository.findByEmail(email).orElseThrow();
	}

}
