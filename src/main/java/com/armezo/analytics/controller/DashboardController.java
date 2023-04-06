package com.armezo.analytics.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.armezo.analytics.demographic.entity.ClientDetails;
import com.armezo.analytics.demographic.entity.Demographic;
import com.armezo.analytics.demographic.service.ClientDetailsService;
import com.armezo.analytics.demographic.service.DemograpicService;
import com.armezo.analytics.payload.AgeWisePayload;
import com.armezo.analytics.payload.AssessmentReportPayload;
import com.armezo.analytics.payload.CandidateExperiencePayload;
import com.armezo.analytics.payload.ClientWiseDataPayload;
import com.armezo.analytics.payload.DashboardRequestPayload;
import com.armezo.analytics.payload.DashboardResponsePayload;
import com.armezo.analytics.payload.GenderDiversityPayload;
import com.armezo.analytics.payload.OverviewPayload;
import com.armezo.analytics.task.entity.Taskwise;
import com.armezo.analytics.task.service.TaskwiseService;
import com.armezo.analytics.utils.AnalyticsUtility;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Dashboard")
@RequestMapping("/dashboard")
public class DashboardController {

	@Autowired
	private DemograpicService demograpicService;
	@Autowired
	private TaskwiseService taskwiseService;
	@Autowired
	private ClientDetailsService clientService;

	@PostMapping("/analytics")
	public ResponseEntity<DashboardResponsePayload> getDashboardData(@RequestBody DashboardRequestPayload request) {
		ResponseEntity<DashboardResponsePayload> responseEntity = new ResponseEntity<DashboardResponsePayload>(
				HttpStatus.OK);
		Date dateFrom = AnalyticsUtility.getYTD();
		Date dateTo = new Date();
		CandidateExperiencePayload experience = demograpicService.getExperienceDemographic(request.getApplicationName(),
				request.getClientId(), dateFrom, dateTo);
		AgeWisePayload age = demograpicService.getAgeWiseDemographic(request.getApplicationName(),
				request.getClientId(), dateFrom, dateTo);
		GenderDiversityPayload gender = demograpicService.getGenderDemographic(request.getApplicationName(),
				request.getClientId(), dateFrom, dateTo);
		OverviewPayload overview = taskwiseService.getOverviewReport(request.getApplicationName(),
				request.getClientId(), dateFrom, dateTo);
		AssessmentReportPayload assessment = taskwiseService.getAssessmentReport(request.getApplicationName(),
				request.getClientId(), dateFrom, dateTo);
		DashboardResponsePayload responsePayload = new DashboardResponsePayload();
		responsePayload.setOverview(overview);
		responsePayload.setAssessment(assessment);
		responsePayload.setExperience(experience);
		responsePayload.setGender(gender);
		responsePayload.setAge(age);
		responseEntity = new ResponseEntity<DashboardResponsePayload>(responsePayload, HttpStatus.OK);
		return responseEntity;

	}

	// get Client Wise Data Count
	@PostMapping("/clientData")
	public ResponseEntity<List<ClientWiseDataPayload>> getClientWiseDataCount() {
		// Count Var
		// Search Date
		Date dateFrom = AnalyticsUtility.getYTD();
		Date dateTo = new Date();
		// Get All Client From Client table
		List<ClientWiseDataPayload> payloadList = new ArrayList<ClientWiseDataPayload>();
		List<ClientDetails> clients = clientService.getAllClients();
		for (ClientDetails cd : clients) {
			Integer registred = 0, assessment = 0, pass = 0, offer = 0, recruited = 0;
			ClientWiseDataPayload payload = new ClientWiseDataPayload();
			payload.setApplicationname(cd.getApplicationName());
			payload.setClientId(cd.getClientId());
			List<Demographic> demoData = demograpicService.getAllDemographic(cd.getApplicationName(), cd.getClientId(),
					dateFrom, dateTo);
			for (Demographic dm : demoData) {
				if ((dm.getRegistrationStatus() != null && dm.getRegistrationStatus().equalsIgnoreCase("Y")) || dm.getRegistrationDate()!=null) {
					registred += 1;
				}
			}
			// Getting task wise data
			List<Taskwise> taskwises = taskwiseService.getTaskwiseByAppnameClientIdDate(cd.getApplicationName(),
					cd.getClientId(), dateFrom, dateTo);
			for (Taskwise task : taskwises) {
				if ((task.getAssessmentStatus() != null && task.getAssessmentStatus().equalsIgnoreCase("Y"))
						&& (task.getAssessmentDate() != null))
					assessment += 1;
				if (task.getPassFailStatus() != null && task.getPassFailStatus().equalsIgnoreCase("P"))
					pass += 1;
				if (task.getOfferStatus() != null && task.getOfferStatus().equalsIgnoreCase("Y"))
					offer += 1;
				if (task.getApprovedStatus() != null && task.getApprovedStatus().equalsIgnoreCase("A"))
					recruited += 1;
			}
			payload.setRegistered(registred);
			payload.setAssessment(assessment);
			payload.setPassed(pass);
			payload.setOffered(offer);
			payload.setRecruited(recruited);
			// Add this payload in List
			payloadList.add(payload);
		}
		// Add this list in response entity
		ResponseEntity<List<ClientWiseDataPayload>> responseEntity = new ResponseEntity<List<ClientWiseDataPayload>>(payloadList, HttpStatus.OK);
		return responseEntity;
	}

}
