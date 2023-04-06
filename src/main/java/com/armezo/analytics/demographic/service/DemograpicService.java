package com.armezo.analytics.demographic.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.armezo.analytics.demographic.entity.Demographic;
import com.armezo.analytics.demographic.repository.DemographicRepository;
import com.armezo.analytics.payload.AgeWisePayload;
import com.armezo.analytics.payload.CandidateExperiencePayload;
import com.armezo.analytics.payload.GenderDiversityPayload;
import com.armezo.analytics.utils.AnalyticsUtility;

@Service
public class DemograpicService {

	@Autowired
	private DemographicRepository demographicRepository;

	public void saveDemographic(Demographic demographic) {
		demographicRepository.save(demographic);
	}

	public Optional<Demographic> getDemographicByAccesskey(String accesskey) {
		return demographicRepository.getDemographicByAccesskey(accesskey);
	}
	public List<Demographic> getDemographicsByAccesskeyList(List<String> accesskeyList, Date dateFrom, Date dateTo) {
		return demographicRepository.getDemographicsByAccesskeyList(accesskeyList,dateFrom,dateTo);
	}
	
	public GenderDiversityPayload getGenderDemographic(String appName, String clientId, Date dateFrom, Date dateTo) {
		List<Demographic> demographics=demographicRepository.getDemographicByApplicationnameClientId(appName,clientId,dateFrom,dateTo);
		return genderDemographic(demographics);
	}
	public AgeWisePayload getAgeWiseDemographic(String appName, String clientId, Date dateFrom, Date dateTo) {
		List<Demographic> demographics=demographicRepository.getDemographicByApplicationnameClientId(appName,clientId,dateFrom,dateTo);
		return getAgeWiseAnalytics(demographics);
	}
	public CandidateExperiencePayload getExperienceDemographic(String appName, String clientId, Date dateFrom, Date dateTo) {
		List<Demographic> demographics=demographicRepository.getDemographicByApplicationnameClientId(appName,clientId,dateFrom,dateTo);
		return experienceDemographics(demographics);
	}
	
	public List<Demographic> getAllDemographic(String applicationName, String clientId, Date dateFrom, Date dateTo) {
		return demographicRepository.getDemographicByApplicationnameClientId(applicationName, clientId, dateFrom, dateTo);
	}

	// Age Wise Analytics
	private AgeWisePayload getAgeWiseAnalytics(List<Demographic> demographics) {
		List<String> less20 = new ArrayList<String>();
		List<String> between20To25 = new ArrayList<String>();
		List<String> between25To30 = new ArrayList<String>();
		List<String> between30To35 = new ArrayList<String>();
		List<String> between35To40 = new ArrayList<String>();
		List<String> more40 = new ArrayList<String>();
		for (Demographic demo : demographics) {
			if (demo.getBirthDate() != null) {
				int age = AnalyticsUtility.birthDateInYearsConversion(demo.getBirthDate());
				if (age < 20) {
					less20.add(demo.getAccesskey());
				} else if (age >= 20 && age < 25) {
					between20To25.add(demo.getAccesskey());
				} else if (age >= 25 && age < 30) {
					between25To30.add(demo.getAccesskey());
				} else if (age >= 30 && age < 35) {
					between30To35.add(demo.getAccesskey());
				} else if (age >= 35 && age < 40) {
					between35To40.add(demo.getAccesskey());
				} else {
					more40.add(demo.getAccesskey());
				}
			}
		}
		AgeWisePayload age = new AgeWisePayload();
		age.setLessThan20(less20);
		age.setBetween20To25(between20To25);
		age.setBetween25To30(between25To30);
		age.setBetween30To35(between30To35);
		age.setBetween35To40(between35To40);
		age.setMoreThan40(more40);
		return age;
	}

	//  Gender
	private static GenderDiversityPayload genderDemographic(List<Demographic> demographics) {
		List<String> male = new ArrayList<String>();
		List<String> female = new ArrayList<String>();
		List<String> other = new ArrayList<String>();
		for (Demographic demo : demographics) {
			if (demo.getGender() != null && demo.getGender().length() > 0) {
				if (demo.getGender().equalsIgnoreCase("Male") || demo.getGender().equalsIgnoreCase("M"))
					male.add(demo.getAccesskey());
				if (demo.getGender().equalsIgnoreCase("Female") || demo.getGender().equalsIgnoreCase("F"))
					female.add(demo.getAccesskey());
				if (demo.getGender().equalsIgnoreCase("Other") || demo.getGender().equalsIgnoreCase("Transgender")
						|| demo.getGender().equalsIgnoreCase("U"))
					other.add(demo.getAccesskey());
			}
		}
		GenderDiversityPayload gender = new GenderDiversityPayload();
		gender.setMale(male);
		gender.setFemale(female);
		gender.setOther(other);
		return gender;
	}
	//Experience
	private static CandidateExperiencePayload experienceDemographics(List<Demographic> demographics) {
		List<String> less3 = new ArrayList<String>();
		List<String> between3To6 = new ArrayList<String>();
		List<String> between6To12 = new ArrayList<String>();
		List<String> between12To24 = new ArrayList<String>();
		List<String> between24To60 = new ArrayList<String>();
		List<String> between60To120 = new ArrayList<String>();
		List<String> more120 = new ArrayList<String>();
		for(Demographic demo : demographics) {
			if(demo.getExperienceInMonths()!=null  ) {
				if(demo.getExperienceInMonths()<3)
					less3.add(demo.getAccesskey());
				if(demo.getExperienceInMonths()>=3 && demo.getExperienceInMonths()<6)
					between3To6.add(demo.getAccesskey());
				if(demo.getExperienceInMonths()>=6 && demo.getExperienceInMonths()<12)
					between6To12.add(demo.getAccesskey());	
				if(demo.getExperienceInMonths()>=12 && demo.getExperienceInMonths()<24)
					between12To24.add(demo.getAccesskey());
				if(demo.getExperienceInMonths()>=24 && demo.getExperienceInMonths()<60)
					between24To60.add(demo.getAccesskey());
				if(demo.getExperienceInMonths()>=60 && demo.getExperienceInMonths()<120)
					between60To120.add(demo.getAccesskey());
				if(demo.getExperienceInMonths()>=120)
					more120.add(demo.getAccesskey());
			}
		}
		CandidateExperiencePayload can = new CandidateExperiencePayload();
		can.setLessThan3Months((less3));
		can.setMonths3To6((between3To6));
		can.setMonths6To1Year((between6To12));
		can.setYear1To2Year((between12To24));
		can.setYear2To5Year((between24To60));
		can.setYear5To10Year((between60To120));
		can.setMoreThan10Year((more120));
		return can;
	}

	

}
