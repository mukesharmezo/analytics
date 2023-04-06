package com.armezo.analytics.task.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.armezo.analytics.demographic.entity.ClientDetails;
import com.armezo.analytics.demographic.service.ClientDetailsService;
import com.armezo.analytics.payload.MessagePayload;
import com.armezo.analytics.payload.TaskwiseRequestPayload;
import com.armezo.analytics.task.entity.Taskwise;
import com.armezo.analytics.task.service.TaskwiseService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Taskwise")
@RequestMapping("/task")
public class TaskwiseController {

	@Autowired
	private TaskwiseService taskwiseService;
	@Autowired
	private ClientDetailsService clientService;

	// update Taskwise analytics data
	@PostMapping("/saveTaskwise")
	public ResponseEntity<?> saveTaskwiseData(@RequestBody TaskwiseRequestPayload payload) {
		Taskwise task = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		MessagePayload message = new MessagePayload();
		ResponseEntity<MessagePayload> responseEntity = null;
		// Save Client Details
		Optional<ClientDetails> clientOptional = clientService.getClientByApplicationName(payload.getApplicationName());
		ClientDetails client = new ClientDetails();
		if (clientOptional.isPresent()) {
			client = clientOptional.get();
		}
		client.setApplicationName(payload.getApplicationName());
		client.setClientId(payload.getClientId());
		clientService.saveClientDetails(client);
		Optional<Taskwise> optional = taskwiseService.getTakwiseByAppNameClientIdAccesskey(payload.getApplicationName(),
				payload.getClientId(), payload.getAccesskey());
		if (optional.isPresent()) {
			task = optional.get();
		} else {
			task = new Taskwise();
		}
		// Setting data
		task.setAccesskey(payload.getAccesskey());
		if (payload.getApplicationName() != null && payload.getApplicationName() != "")
			task.setApplicationName(payload.getApplicationName());
		if (payload.getClientId() != null && payload.getClientId() != "")
			task.setClientId(payload.getClientId());
		if (payload.getRecruiterId() != null && payload.getRecruiterId() != "")
			task.setRecruiterId(payload.getRecruiterId());
		if (payload.getStateId() != null && payload.getStateId() != "")
			task.setStateId(payload.getStateId());
		if (payload.getCityId() != null && payload.getCityId() != "")
			task.setCityId(payload.getCityId());
		if (payload.getAssessmentStatus() != null && payload.getAssessmentStatus() != "")
			task.setAssessmentStatus(payload.getAssessmentStatus());
		if (payload.getPassFailStatus() != null && payload.getPassFailStatus() != "")
			task.setPassFailStatus(payload.getPassFailStatus());
		if (payload.getPercentage() != null && payload.getPercentage() != 0)
			task.setPercentage(Double.valueOf(payload.getPercentage()));
		if (payload.getInterviewStatus() != null && payload.getInterviewStatus() != "")
			task.setInterviewStatus(payload.getInterviewStatus());
		if (payload.getOfferStatus() != null && payload.getOfferStatus() != "")
			task.setOfferStatus(payload.getOfferStatus());
		if (payload.getDocumentUploadStatus() != null && payload.getDocumentUploadStatus() != "")
			task.setDocumentUploadStatus(payload.getDocumentUploadStatus());
		if (payload.getApprovedStatus() != null && payload.getApprovedStatus() != "")
			task.setApprovedStatus(payload.getApprovedStatus());
		try {
			if (payload.getRegistrationDate() != null && payload.getRegistrationDate() != "")
				task.setRegistrationDate(sdf.parse(payload.getRegistrationDate()));
			if (payload.getAssessmentDate() != null && payload.getAssessmentDate() != "")
				task.setAssessmentDate(sdf.parse(payload.getAssessmentDate()));
			if (payload.getInterviewDate() != null && payload.getInterviewDate() != "")
				task.setInterviewDate(sdf.parse(payload.getInterviewDate()));
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// Saving in DB
		taskwiseService.saveTaskwise(task);
		message.setMessage("Task-Wise Data Saved");
		responseEntity = new ResponseEntity<MessagePayload>(message, HttpStatus.CREATED);

		return responseEntity;
	}

	// get Taskwise analytics data

}
