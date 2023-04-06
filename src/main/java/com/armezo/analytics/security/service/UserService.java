package com.armezo.analytics.security.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.armezo.analytics.security.entity.AnalyticsUser;
import com.armezo.analytics.security.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private BCryptPasswordEncoder encoder ;

	public Optional<AnalyticsUser> getUserByUsername(String username) {
		return userRepository.getUserByUsername(username);
	}

	public void saveUser(AnalyticsUser user) {
		user.setPassword(encoder.encode(user.getPassword()));
		userRepository.save(user);
	}

}
