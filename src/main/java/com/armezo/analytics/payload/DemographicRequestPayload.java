package com.armezo.analytics.payload;

public class DemographicRequestPayload {
	
	private String applicationName;
	private String clientId;
	private String recruiterId;
	private String stateId;
	private String cityId;
	private String accesskey;
	private String registrationStatus;
	private String registrationDate;  //     31/12/2022
	private String firstName;
	private String middleName;
	private String lastName;
	private String birthDate;
	private String modifiedDate;
	private String recruitmentSource;
	private String profile;
	private String designation;
	private Integer age;
	private String gender;
	private Long mobile;
	private Integer experienceInMonths;
	
	public String getApplicationName() {
		return applicationName;
	}
	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}
	public String getAccesskey() {
		return accesskey;
	}
	public void setAccesskey(String accesskey) {
		this.accesskey = accesskey;
	}
	public String getRegistrationStatus() {
		return registrationStatus;
	}
	public void setRegistrationStatus(String registrationStatus) {
		this.registrationStatus = registrationStatus;
	}
	public String getRegistrationDate() {
		return registrationDate;
	}
	public void setRegistrationDate(String registrationDate) {
		this.registrationDate = registrationDate;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	public String getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(String modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public String getRecruitmentSource() {
		return recruitmentSource;
	}
	public void setRecruitmentSource(String recruitmentSource) {
		this.recruitmentSource = recruitmentSource;
	}
	public String getProfile() {
		return profile;
	}
	public void setProfile(String profile) {
		this.profile = profile;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Long getMobile() {
		return mobile;
	}
	public void setMobile(Long mobile) {
		this.mobile = mobile;
	}
	public Integer getExperienceInMonths() {
		return experienceInMonths;
	}
	public void setExperienceInMonths(Integer experienceInMonths) {
		this.experienceInMonths = experienceInMonths;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getRecruiterId() {
		return recruiterId;
	}
	public void setRecruiterId(String recruiterId) {
		this.recruiterId = recruiterId;
	}
	public String getStateId() {
		return stateId;
	}
	public void setStateId(String stateId) {
		this.stateId = stateId;
	}
	public String getCityId() {
		return cityId;
	}
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	@Override
	public String toString() {
		return "DemographicRequestPayload [applicationName=" + applicationName + ", accesskey=" + accesskey
				+ ", registrationStatus=" + registrationStatus + ", registrationDate=" + registrationDate
				+ ", firstName=" + firstName + ", middleName=" + middleName + ", lastName=" + lastName + ", birthDate="
				+ birthDate + ", modifiedDate=" + modifiedDate + ", recruitmentSource=" + recruitmentSource
				+ ", profile=" + profile + ", designation=" + designation + ", age=" + age + ", gender=" + gender
				+ ", mobile=" + mobile + ", experienceInMonths=" + experienceInMonths + "]";
	}
	
	
	

}
