package com.armezo.analytics.demographic.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.armezo.analytics.demographic.entity.ClientDetails;
import com.armezo.analytics.demographic.repository.ClientDetailsRepository;

@Service
public class ClientDetailsService {
	
	@Autowired
	private ClientDetailsRepository repository;
	
	//Get Client By Id
	public Optional<ClientDetails> getClientById(Long id) {
		return repository.findById(id);
	}
	//FindBy App Name
	public Optional<ClientDetails> getClientByApplicationName(String appName) {
		return repository.findByApplicationName(appName);
	}
	//Get All Client
	public List<ClientDetails> getAllClients() {
		return repository.findAll();
	}
	//Save Client
	public void saveClientDetails(ClientDetails client) {
		repository.save(client);
	}
	

}
