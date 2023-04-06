package com.armezo.analytics.task.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.armezo.analytics.payload.AssessmentReportPayload;
import com.armezo.analytics.payload.OverviewPayload;
import com.armezo.analytics.task.entity.Taskwise;
import com.armezo.analytics.task.repository.TaskWiseRepository;

@Service
public class TaskwiseService {
	
	@Autowired
	private TaskWiseRepository taskWiseRepository;

	public void saveTaskwise(Taskwise task) {
		taskWiseRepository.save(task);
	}
	public Optional<Taskwise> getTakwiseByAppNameClientIdAccesskey(String applicationName, String clientId,
			String accesskey) {
		return taskWiseRepository.getTakwiseByAppNameClientIdAccesskey(applicationName,clientId,accesskey);
	}
	
	public OverviewPayload getOverviewReport(String applicationName, String clientId,Date dateFrom, Date dateTo) {
		List<Taskwise> tasks = taskWiseRepository.getTaskwiseByAppNameClientId(applicationName,clientId,dateFrom,dateTo);
		return overviewReport(tasks);
	}
	
	public AssessmentReportPayload getAssessmentReport(String applicationName, String clientId,Date dateFrom, Date dateTo) {
		List<Taskwise> tasks = taskWiseRepository.getTaskwiseByAppNameClientId(applicationName,clientId,dateFrom,dateTo);
		return assessmentTaskReport(tasks);
	}
	
	public List<Taskwise> getTaskwiseByAppnameClientIdDate(String applicationName, String clientId, Date dateFrom,
			Date dateTo) {
		return taskWiseRepository.getTaskwiseByAppNameClientId(applicationName, clientId, dateFrom, dateTo);
	}
	
	//Overview Dashboard Data
	private static OverviewPayload overviewReport(List<Taskwise> tasks) {
		List<String> registered=new ArrayList<String>(),
				assessmet=new ArrayList<String>(),
				pass =new ArrayList<String>(),
				offer=new ArrayList<String>(),
				recruited=new ArrayList<String>();
		for(Taskwise task : tasks) {
			if(task.getRegistrationDate()!=null)
				registered.add(task.getAccesskey());
			if(task.getAssessmentStatus()!=null && task.getAssessmentStatus().equalsIgnoreCase("Y"))
				assessmet.add(task.getAccesskey());
			if(task.getPassFailStatus()!=null && task.getPassFailStatus().equalsIgnoreCase("P"))
				pass.add(task.getAccesskey());
			if(task.getOfferStatus()!=null && task.getOfferStatus().equalsIgnoreCase("Y"))
				offer.add(task.getAccesskey());
			if(task.getApprovedStatus()!=null && task.getApprovedStatus().equalsIgnoreCase("A"))
				recruited.add(task.getAccesskey());
		}
		OverviewPayload overview= new OverviewPayload();
		overview.setRegistered(registered);
		overview.setAssessments(assessmet);
		overview.setPass(pass);
		overview.setOffer(offer);
		overview.setRecruited(recruited);
		return overview;
		
	}
	//Assessment Report
	private static AssessmentReportPayload assessmentTaskReport(List<Taskwise> tasks) {
		List<String>  less40=new ArrayList<String>();
		List<String>  between40To60=new ArrayList<String>();
		List<String>  between60To80=new ArrayList<String>();
		List<String>  more80=new ArrayList<String>();
		for(Taskwise task : tasks) {
			if(task.getPercentage()!=null) {
				if(task.getPercentage()<40)
					less40.add(task.getAccesskey());
				if(task.getPercentage()>=40 && task.getPercentage()<60)
					between40To60.add(task.getAccesskey());
				if(task.getPercentage()>=60 && task.getPercentage()<80)
					between60To80.add(task.getAccesskey());
				if(task.getPercentage()>=80)
					more80.add(task.getAccesskey());
			}
		}
		AssessmentReportPayload asmt= new AssessmentReportPayload();
		asmt.setLessThan40((less40));
		asmt.setBetween40To60((between40To60));
		asmt.setBetween60To80((between60To80));
		asmt.setMoreThan80((more80));
		return asmt;
	}
	


}
