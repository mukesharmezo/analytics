package com.armezo.analytics.demographic.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.armezo.analytics.demographic.entity.ClientDetails;
import com.armezo.analytics.demographic.entity.Demographic;
import com.armezo.analytics.demographic.service.ClientDetailsService;
import com.armezo.analytics.demographic.service.DemograpicService;
import com.armezo.analytics.payload.DemographicRequestPayload;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
//@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Demographic")
@RequestMapping("/demographic")
public class DemographicController {
	
	@Autowired
	private DemograpicService demograpicService;
	@Autowired
	private ClientDetailsService clientService;
	
	//Save Demographic data
	
	@PostMapping("/saveDemographic")
	//@Tag(name = "Save One Candidate Data")
	public ResponseEntity<?> saveDemographicData(@RequestBody DemographicRequestPayload payload){
		//Save Client Details
		Optional<ClientDetails> clientOptional = clientService.getClientByApplicationName(payload.getApplicationName());
		ClientDetails client = new ClientDetails();
		if(clientOptional.isPresent()) {
			client = clientOptional.get();
		}
		client.setApplicationName(payload.getApplicationName());
		client.setClientId(payload.getClientId());
		clientService.saveClientDetails(client);
		// Check for existing data
		Demographic demographic = new Demographic();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Optional<Demographic> optional = demograpicService.getDemographicByAccesskey(payload.getAccesskey());
		if(optional.isPresent())
			demographic=optional.get();
		//setting data from request payload to entity 
		demographic.setAccesskey(payload.getAccesskey());
		demographic.setApplicationName(payload.getApplicationName());
		demographic.setClientId(payload.getClientId());
		demographic.setRecruiterId(payload.getRecruiterId());
		demographic.setStateId(payload.getStateId());
		demographic.setCityId(payload.getCityId());
		demographic.setRegistrationStatus(payload.getRegistrationStatus());
		demographic.setFirstName(payload.getFirstName());
		demographic.setMiddleName(payload.getMiddleName());
		demographic.setLastName(payload.getLastName());
		demographic.setRecruitmentSource(payload.getRecruitmentSource());
		demographic.setProfile(payload.getProfile());
		demographic.setDesignation(payload.getDesignation());
		demographic.setAge(payload.getAge());
		demographic.setGender(payload.getGender());
		demographic.setMobile(payload.getMobile());
		demographic.setExperienceInMonths(payload.getExperienceInMonths());
		try {
			demographic.setRegistrationDate(sdf.parse(payload.getRegistrationDate()));
			demographic.setBirthDate(sdf.parse(payload.getBirthDate()));
			demographic.setModifiedDate(sdf.parse(payload.getModifiedDate()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		//saving in db
		demograpicService.saveDemographic(demographic);
		return ResponseEntity.ok("Data Saved");
	}
	
	
	

}
