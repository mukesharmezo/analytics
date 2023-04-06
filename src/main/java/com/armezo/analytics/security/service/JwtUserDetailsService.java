package com.armezo.analytics.security.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.armezo.analytics.security.entity.AnalyticsUser;



@Service
public class JwtUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<AnalyticsUser> optional = userService.getUserByUsername(username);
		if (optional.isPresent()) {
			User userSecurity = new User(optional.get().getUsername(), optional.get().getPassword(), new ArrayList<>());
			return userSecurity;
		}else {
			throw new UsernameNotFoundException("User Not Found With Username : "+username);
		}
	}

}
