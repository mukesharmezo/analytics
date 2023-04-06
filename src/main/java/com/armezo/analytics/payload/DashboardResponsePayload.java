package com.armezo.analytics.payload;

public class DashboardResponsePayload {
	
	private OverviewPayload overview;
	private AssessmentReportPayload assessment;
	private CandidateExperiencePayload experience;
	private GenderDiversityPayload gender;
	private AgeWisePayload age;
	public OverviewPayload getOverview() {
		return overview;
	}
	public void setOverview(OverviewPayload overview) {
		this.overview = overview;
	}
	public AssessmentReportPayload getAssessment() {
		return assessment;
	}
	public void setAssessment(AssessmentReportPayload assessment) {
		this.assessment = assessment;
	}
	public CandidateExperiencePayload getExperience() {
		return experience;
	}
	public void setExperience(CandidateExperiencePayload experience) {
		this.experience = experience;
	}
	public GenderDiversityPayload getGender() {
		return gender;
	}
	public void setGender(GenderDiversityPayload gender) {
		this.gender = gender;
	}
	public AgeWisePayload getAge() {
		return age;
	}
	public void setAge(AgeWisePayload age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return "DashboardResponsePayload [overview=" + overview + ", assessment=" + assessment + ", experience="
				+ experience + ", gender=" + gender + ", age=" + age + "]";
	}

	
}
