package com.armezo.analytics.security.controller;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException.Unauthorized;

import com.armezo.analytics.exception.UnauthorizedRequestException;
import com.armezo.analytics.payload.MessagePayload;
import com.armezo.analytics.payload.UserRequestPayload;
import com.armezo.analytics.payload.UserResponsePayload;
import com.armezo.analytics.security.entity.AnalyticsUser;
import com.armezo.analytics.security.service.UserService;
import com.armezo.analytics.security.util.TokenUtil;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "User Controller")
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private TokenUtil tokenUtil;
	@Autowired
	private UserDetailsService userDetailsService;
	

	// Save User Details
	@Hidden
	@PostMapping("/saveUser")
	public ResponseEntity<MessagePayload> saveAnalyticsUser(@RequestBody UserRequestPayload payload) {
		MessagePayload message = new MessagePayload();
		ResponseEntity<MessagePayload> responseEntity = null;
		Optional<AnalyticsUser> optional = userService.getUserByUsername(payload.getUsername());
		if (optional.isEmpty()) {
			AnalyticsUser user = new AnalyticsUser();
			user.setUsername(payload.getUsername());
			user.setPassword(payload.getPassword());
			userService.saveUser(user);
			message.setMessage("User details are saved");
			responseEntity = new ResponseEntity<MessagePayload>(message, HttpStatus.CREATED);
		} else {
			message.setMessage("Username is alerady exist");
			responseEntity = new ResponseEntity<MessagePayload>(message, HttpStatus.CONFLICT);
		}
		return responseEntity;
	}

	// Login Process

	@PostMapping("/login")
	public ResponseEntity<?> loginAndGetAccessToken(@RequestBody UserRequestPayload payload) throws Exception {
		ResponseEntity<?> responseEntity = null;
		MessagePayload msg = new MessagePayload();
		// Authenticate User
		String auth = authenticateUser(payload.getUsername(), payload.getPassword());
		// Load User
		if (auth.equalsIgnoreCase("Yes")) {
			final UserDetails userDetails = userDetailsService.loadUserByUsername(payload.getUsername());
			// Generate Token
			final String token = tokenUtil.generateToken(userDetails);
			System.out.println("Token Generated : "+token);
			responseEntity = ResponseEntity.ok(new UserResponsePayload(token));
		}else if (auth.equalsIgnoreCase("Bad") || auth.equalsIgnoreCase("No")) {
			msg.setMessage("Username/Password Not Matched");
			responseEntity= new ResponseEntity<MessagePayload>(msg, HttpStatus.BAD_REQUEST);
		}else if (auth.equalsIgnoreCase("Disable")) {
			msg.setMessage("User is disabled");
			responseEntity= new ResponseEntity<MessagePayload>(msg, HttpStatus.BAD_REQUEST);
		}
		return responseEntity;
	}


	private String authenticateUser(String username, String password) throws Exception {
		Objects.requireNonNull(username);
		Objects.requireNonNull(password);
		String authenticated = "";
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
			authenticated = "Yes";
		} catch (DisabledException e) {
			authenticated = "Diable";
			e.printStackTrace();
		} catch (BadCredentialsException e) {
			throw new UnauthorizedRequestException("Bad Username/Password");
//			authenticated = "Bad";
//			logger.error("Bad Credential");
		} catch (Unauthorized e) {
				throw new UnauthorizedRequestException("Username/Password is incorrect");
			//			authenticated = "No";
//			e.printStackTrace();
		} catch (Exception e) {
			authenticated = "No";
			e.printStackTrace();
		}
		return authenticated;

	}

}
